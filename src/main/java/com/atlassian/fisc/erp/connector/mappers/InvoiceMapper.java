package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.InvoiceBDM;
import com.atlassian.finance.bdm.InvoiceLineBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItem;
import com.atlassian.fisc.erp.connector.domain.invoice.InvoiceItemList;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Mapper(uses = {InvoiceLineMapper.class, NetSuiteMapperFn.class, AddressMapper.class})
public interface InvoiceMapper {
  InvoiceMapper INSTANCE = Mappers.getMapper(InvoiceMapper.class);

  default InvoiceBDM convert(Invoice nsInvoice) {
    return updateCustomFields(
        map(nsInvoice), CustomFieldsMapper.INSTANCE.convert(nsInvoice.getCustomFieldList()));
  }

  default Invoice convert(InvoiceBDM invoiceBDM) {
    return updateCustomFields(map(invoiceBDM), extractNsCustomFields(invoiceBDM));
  }

  @Mapping(target = "externalId", source = "id") // TODO  internalId / externalId Invc-ROW : 2
  @Mapping(target = "internalId", source = "invoiceErpId") // TODO Review Invc-ROW : 3
  @Mapping(target = "tranId", source = "transactionNumber") //Invc-ROW : 4
  @Mapping(target = "tranDate", source = "transactionDate", dateFormat = Constant.DATE_TIME_FORMAT) //Invc-ROW : 5
  @Mapping(target = "dueDate", source = "dueDate", dateFormat = Constant.DATE_TIME_FORMAT) //Invc-ROW : 6
  @Mapping(
      target = "currency",
      source = "currency",
      qualifiedBy = NetSuiteMapping.class) //  needs lookup Invc-ROW : 10
  @Mapping(target = "entity", source = "customerId", qualifiedBy = NetSuiteMapping.class) //Invc-ROW : 11
  @Mapping(target = "account", source = "accountId", qualifiedBy = NetSuiteMapping.class) //Invc-ROW : 14
  @Mapping(
      target = "terms",
      source = "terms",
      qualifiedBy = NetSuiteMapping.class) //Invc-ROW : 19
  @Mapping(target = "customForm", source = "formType", qualifiedBy = NetSuiteMapping.class) //Invc-ROW : 9
  @Mapping(target = "itemList", source = "invoiceLines", qualifiedByName = "mapInvoiceLine")
  @Mapping(target = "billingAddress", source = "billTo")
  @Mapping(target = "shippingAddress", source = "shipTo")
  @Mapping(target = "balance", source = "balanceAmount") //Invc-ROW : 55
  Invoice map(InvoiceBDM invoice);

  @InheritInverseConfiguration
  @Mapping(target = "formType", constant = Constant.INVOICE_FORM_TYPE) //Invc-ROW : 9
  @Mapping(target = "sourceSystem", constant = Constant.TRANSACTION_SOURCE_SYSTEM_HAMS) //Invc-ROW : 1
  @Mapping(target = "subsidiaryId", constant = Constant.ATLASSIAN_AU_BU_USD_BUSINESS_UNIT_NAME)
  @Mapping(
      target = "terms",
      constant = Constant.INV_TERM_IMMEDIATE) // need in inverse due to defaultValue //Invc-ROW : 19
  InvoiceBDM map(Invoice invoice);

  @Mapping(target = "endCustomerId", source = "custbody_end_customer") //Invc-ROW : 12
  @Mapping(target = "billingRegion", source = "custbody_atl_common_billregion") //Invc-ROW : 23
  @Mapping(target = "shippingRegion", source = "custbody_atl_common_shipregion") //Invc-ROW : 24
  @Mapping(target = "billingContactId", source = "custbodybillingcontact.internalId") //Invc-ROW : 25
  @Mapping(target = "technicalContactId", source = "custbodytechnicalcontact.internalId") //Invc-ROW : 26
  @Mapping(target = "cmodFlag", source = "custbody_contract_modification") //Invc-ROW : 27
  @Mapping(target = "exchangeRate", source = "custbodycustbody_exchange_rate") //Invc-ROW : 29
  @Mapping(target = "customerType", source = "custbody_atl_cust_type") //Invc-ROW : 20
  @Mapping(target = "paymentMethod", source = "custbody_payment_method") //Invc-ROW : 21
  InvoiceBDM updateCustomFields(
      @MappingTarget InvoiceBDM invoiceBDM, CustomFields invoiceCustomFields);
  @InheritInverseConfiguration
  @Mapping(target = "custbody_source_system", source = "sourceSystem.name")  //Invc-ROW : 1
  CustomFields extractNsCustomFields(InvoiceBDM invoiceBDM);

  @Mapping(
      target = "customFieldList",
      source = "invoiceCustomFields",
      qualifiedBy = NetSuiteMapping.class)
  Invoice updateCustomFields(@MappingTarget Invoice nsInvoice, CustomFields invoiceCustomFields);

  @Named("mapInvoiceLine")
  default InvoiceItemList mapInvoiceLine(List<InvoiceLineBDM> invoiceLines) {
    InvoiceItemList itemList = new InvoiceItemList();

    if (CollectionUtils.isEmpty(invoiceLines)) return itemList;
    List<InvoiceItem> invoiceItemList =
        invoiceLines.stream().map(InvoiceLineMapper.INSTANCE::convert).collect(Collectors.toList());
    itemList.setItem(invoiceItemList.toArray(new InvoiceItem[invoiceItemList.size()]));
    return itemList;
  }

  @Named("mapInvoiceLine")
  default List<InvoiceLineBDM> mapInvoiceLines(InvoiceItemList itemList) {
    if (isEmpty(itemList.getItem())) return Collections.emptyList();

    List<InvoiceItem> invoiceItemList = Arrays.asList(itemList.getItem());
    final var lineNumber = new AtomicInteger(0);
    return invoiceItemList.stream()
        .map(
            invoiceItem -> {
              InvoiceLineBDM invoiceLine = InvoiceLineMapper.INSTANCE.convert(invoiceItem);
              invoiceLine.setLineNumber(String.valueOf(lineNumber.incrementAndGet()));
              return invoiceLine;
            })
        .collect(Collectors.toList());
  }
}
