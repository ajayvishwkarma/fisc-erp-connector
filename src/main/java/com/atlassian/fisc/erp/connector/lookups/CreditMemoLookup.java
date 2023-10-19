package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItem;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItemList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;

import static com.atlassian.fisc.erp.connector.constant.Constant.*;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.allNotNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreditMemoLookup {
  private final LookupHelper helper;

  public CreditMemo prePopulateNsFields(CreditMemo nsCreditMemo) {
    lookupAndPopulateItemId(nsCreditMemo.getItemList());
    return nsCreditMemo;
  }

  private void lookupAndPopulateItemId(CreditMemoItemList creditMemoItemList) {
    if (isNull(creditMemoItemList) || isEmpty(creditMemoItemList.getItem())) return;
    Arrays.stream(creditMemoItemList.getItem())
        .forEach(
            item -> {
              if (nonNull(item.getItem())) item.getItem().setInternalId(computeItemId(item));
            });
  }

  private String computeItemId(CreditMemoItem creditMemoItem) {
    var cls = creditMemoItem.get_class();
    String classPart = "|" + (null != cls ? cls.getInternalId() : "TAX");
    String itemNumberIdentifier = creditMemoItem.getItem().getInternalId() + classPart;

    // Lookup(item) -> Lookup(itemNumber) -> default
    return helper.itemOrDefault(
        creditMemoItem.getItem().getInternalId(),
        helper.itemOrDefault(itemNumberIdentifier, Constant.ITEM_DEFAULT));
  }

  public CreditNoteBDM forBdmFields(CreditNoteBDM creditNoteBDM, CreditMemo nsCreditMemo) {
    // currency
    creditNoteBDM.setCurrency(helper.currency(nsCreditMemo.getCurrency()));

    // country
    if (allNotNull(nsCreditMemo.getBillingAddress(), creditNoteBDM.getBillTo())) {
      creditNoteBDM
          .getBillTo()
          .setCountry(helper.country(nsCreditMemo.getBillingAddress().getCountry()));
    }

    //  TODO handle other lookups

    return creditNoteBDM;
  }

  public CreditMemo forNsFields(CreditMemo nsCreditMemo, CreditNoteBDM creditNoteBDM) {

    if (allNotNull(creditNoteBDM.getBillTo(), nsCreditMemo.getBillingAddress())) {
      nsCreditMemo
          .getBillingAddress()
          .setCountry(helper.nsCountry(creditNoteBDM.getBillTo().getCountry()));
    }

    if(nsCreditMemo.getItemList()!=null && nsCreditMemo.getItemList().getItem()!=null) {
      for (CreditMemoItem item : nsCreditMemo.getItemList().getItem()) {
        String itemString = item.getItem().getInternalId();
        String spokeSystemString = helper.spokeSystemItemOrDefault(
                itemString, helper.spokeSystemItemOrDefault(itemString, SPOKE_SYSTEM_DEFAULT));
        String[] spokeSystemSplit = spokeSystemString.split("\\|");
        item.getItem().setInternalId(spokeSystemSplit.length > 0 ? spokeSystemSplit[0] : itemString);
        if (spokeSystemSplit.length > 1)
          item.get_class().setInternalId(spokeSystemSplit[1]);
        else
          item.set_class(null);
      }
    }

    // status
    String status = STATUS_PAID.equals(nsCreditMemo.getStatus()) ? CREDIT_MEMO_STATUS_FULLY_APPLIED : STATUS_OPEN;
    nsCreditMemo.setStatus(status);
    //  TODO handle other lookups

    return nsCreditMemo;
  }
}
