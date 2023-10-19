package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.finance.bdm.InvoiceBDM;
import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItem;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItemList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.atlassian.fisc.erp.connector.constant.Constant.*;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
@Slf4j
public class InvoiceLookup {
  private final LookupHelper helper;

  public Invoice prePopulateNsFields(Invoice nsInvoice) {
    lookupItemId(nsInvoice.getItemList());
    return nsInvoice;
  }

  private void lookupItemId(InvoiceItemList itemList) {
    if (isEmpty(itemList.getItem())) return;
    for (InvoiceItem invoiceItem : itemList.getItem()) {
      invoiceItem.getItem().setInternalId(computeItemInternalId(invoiceItem));
    }
  }

  private String computeItemInternalId(InvoiceItem invoiceItem) {
    String itemIdentifier = invoiceItem.getItem().getInternalId();
    var cls = invoiceItem.get_class();
    String itemNumberIdentifier =
        itemIdentifier + "|" + (null != cls ? cls.getInternalId() : "TAX");
    // Lookup(item) -> Lookup(itemNumber) -> default
    return helper.itemOrDefault(
        itemIdentifier, helper.itemOrDefault(itemNumberIdentifier, ITEM_DEFAULT));
  }

  public InvoiceBDM forBdmFields(InvoiceBDM invoiceBDM, Invoice nsInvoice) {
    // currency
    invoiceBDM.setCurrency(helper.currency(invoiceBDM.getCurrency()));

    // country
    if (allNotNull(nsInvoice.getBillingAddress(), invoiceBDM.getBillTo())) {
      invoiceBDM.getBillTo().setCountry(helper.country(nsInvoice.getBillingAddress().getCountry()));
    }

    if (allNotNull(nsInvoice.getShippingAddress(), invoiceBDM.getShipTo())) {
      invoiceBDM
          .getShipTo()
          .setCountry(helper.country(nsInvoice.getShippingAddress().getCountry()));
    }

    // TODO: handle other lookups

    return invoiceBDM;
  }

  public Invoice forNsFields(Invoice nsInvoice, InvoiceBDM invoiceBDM) {
    // currency
    helper.nsCurrency(nsInvoice.getCurrency());

    // country
    if (allNotNull(invoiceBDM.getBillTo(), nsInvoice.getBillingAddress())) {
      nsInvoice
          .getBillingAddress()
          .setCountry(helper.nsCountry(invoiceBDM.getBillTo().getCountry()));
    }

    if (allNotNull(invoiceBDM.getShipTo(), nsInvoice.getShippingAddress())) {
      nsInvoice
          .getShippingAddress()
          .setCountry(helper.nsCountry(invoiceBDM.getShipTo().getCountry()));
    }

    //ItemId
    for (InvoiceItem item: nsInvoice.getItemList().getItem()) {
      String itemString = item.getItem().getInternalId();
      String spokeSystemString = helper.spokeSystemItemOrDefault(
              itemString, helper.spokeSystemItemOrDefault(itemString, SPOKE_SYSTEM_DEFAULT));
      String[] spokeSystemSplit = spokeSystemString.split("\\|");
      item.getItem().setInternalId(spokeSystemSplit.length>0?spokeSystemSplit[0]:itemString);
      if(spokeSystemSplit.length>1)
       item.get_class().setInternalId(spokeSystemSplit[1]);
      else
        item.set_class(null);
    }

    // status
    String status = STATUS_PAID.equals(nsInvoice.getStatus()) ? INVOICE_STATUS_PAID_IN_FULL : STATUS_OPEN;
    nsInvoice.setStatus(status);
    // TODO: handle other lookups

    return nsInvoice;
  }
}
