package com.atlassian.fisc.erp.connector.mappers.creditMemo;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItem;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItemList;
import com.atlassian.fisc.erp.connector.mappers.common.TestGenHelper;
import com.atlassian.fisc.erp.connector.util.TestUtil;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.atlassian.fisc.erp.connector.util.TestUtil.recordRef;

public class NsCreditMemoTestGen {
  public CreditMemo createDummy() {
    CreditMemo creditMemo = new CreditMemo();
    creditMemo.setSource("SampleSource");
    creditMemo.setTranId("123");
    creditMemo.setTranDate(TestUtil.catDateTime("2023-01-25T08:32:30+05:30"));
    creditMemo.setCreatedFrom(recordRef("101", "101", null));
    creditMemo.setCurrency(recordRef("USD", null, null));
    creditMemo.setMemo("SampleMemo");
    creditMemo.setEntity(recordRef("SampleID", null, null));
    creditMemo.setCustomFieldList(buildCflCreditMemo());
    creditMemo.setStatus("OPEN");
    creditMemo.setExchangeRate(10.0);
    CreditMemoItemList creditMemoItemList = new CreditMemoItemList();
    CreditMemoItem creditMemoItem = new CreditMemoItem();
    RecordRef item = new RecordRef();
    item.setInternalId("14667");
    RecordRef product = new RecordRef();
    product.setInternalId("45");
    creditMemoItem.setItem(item);
    creditMemoItem.set_class(product);
    creditMemoItem.setDescription("SampleDesc");
    creditMemoItem.setAmount(1000.0);
    creditMemoItem.setRevRecStartDate(TestUtil.catDateTime("2022-12-14T04:35:53+05:30"));
    creditMemoItem.setRevRecEndDate(TestUtil.catDateTime("2022-12-18T04:35:53+05:30"));
    creditMemoItem.setCustomFieldList(buildCflForCreditMemoItem());
    creditMemoItem.setTax1Amt(100.0);
    creditMemoItem.setTaxCode(recordRef("sample", null, null));
    creditMemoItem.setTaxRate1(111.0);

    CreditMemoItem[] creditMemoItems = new CreditMemoItem[] {creditMemoItem};
    creditMemoItemList.setItem(creditMemoItems);
    creditMemo.setItemList(creditMemoItemList);
    creditMemo.setBillingAddress(TestGenHelper.getDummyNsAddress(true));

    return creditMemo;
  }

  public CreditMemo createDummyNoItemList() {
    CreditMemo creditMemo = new CreditMemo();
    creditMemo.setSource("SampleSource");
    creditMemo.setTranId("123");
    creditMemo.setTranDate(TestUtil.catDateTime("2023-01-25T08:32:30+05:30"));
    creditMemo.setCreatedFrom(recordRef("101", "101", null));
    creditMemo.setCurrency(recordRef("USD", null, null));
    creditMemo.setMemo("SampleMemo");
    creditMemo.setEntity(recordRef("SampleID", null, null));
    creditMemo.setCustomFieldList(buildCflCreditMemo());
    creditMemo.setStatus("OPEN");
    creditMemo.setExchangeRate(10.0);
    CreditMemoItemList creditMemoItemList = new CreditMemoItemList();
    RecordRef item = new RecordRef();
    item.setInternalId("14667");
    RecordRef product = new RecordRef();
    product.setInternalId("45");
    creditMemo.setItemList(creditMemoItemList);
    creditMemo.setBillingAddress(TestGenHelper.getDummyNsAddress(true));

    return creditMemo;
  }


  private CustomFieldList buildCflForCreditMemoItem() {
    List<CustomFieldRef> customFields = new LinkedList<>();
    customFields.add(new CustomFieldRef("1", "custcol_sen", "value:custcol_sen"));
    customFields.add(new CustomFieldRef("2", "custcol_new_list_price", "150"));
    customFields.add(new CustomFieldRef("3", "custcol_sale_action", "value:custcol_sale_action"));
    customFields.add(new CustomFieldRef("4", "custcol_hosting_type", "value:custcol_hosting_type"));
    return TestUtil.toCustomFieldList(customFields);
  }

  private CustomFieldList buildCflCreditMemo() {
    List<CustomFieldRef> customFields = new LinkedList<>();
    customFields.add(new CustomFieldRef("1", "custbody_refund_code", "value:custbody_refund_code"));
    customFields.add(new CustomFieldRef("2", "custbody_payment_method", "value:custbody_payment_method"));
    customFields.add(new CustomFieldRef("3", "custbody_atl_cust_type", "Aggregator"));
    customFields.add(new CustomFieldRef("4", "cust_issueRefund", true));
    customFields.add(new CustomFieldRef("5", "custbodyatl_credit_memo_source", "SLA"));
    customFields.add(new CustomFieldRef("6", "custbody_end_customer", "Test-End-Customer-1234"));
    return TestUtil.toCustomFieldList(customFields);
  }

  public static void removeCustomeField(CustomFieldList custFieldList,String fieldName) {
    List<CustomFieldRef> customFieldList =
            new LinkedList<>(Arrays.asList(custFieldList.getCustomField()));
    customFieldList.removeIf(name -> fieldName.equals(name.getScriptId()));
    custFieldList.setCustomField(
            customFieldList.toArray(new CustomFieldRef[customFieldList.size()]));
  }

}
