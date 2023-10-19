package com.atlassian.fisc.erp.connector.mappers.creditMemo;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.finance.bdm.CreditNoteLineBDM;
import com.atlassian.finance.bdm.TaxItemBDM;
import com.atlassian.finance.enums.CreditMemoSource;
import com.atlassian.finance.enums.TransactionSourceSystem;
import com.atlassian.fisc.erp.connector.mappers.common.TestGenHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CreditNoteBDMTestGen {

  public CreditNoteBDM createDummy() {
    CreditNoteBDM creditNoteBDM = new CreditNoteBDM();
    creditNoteBDM.setSourceSystem(TransactionSourceSystem.HAMS_INTEGRATION);
    creditNoteBDM.setTransactionNumber("123");
    creditNoteBDM.setTransactionDate("2023-01-25T08:32:30+05:30");
    creditNoteBDM.setIssueRefund(true);
    creditNoteBDM.setInvoiceId("101");
    creditNoteBDM.setCurrency("USD");
    creditNoteBDM.setMemo("SampleMemo");
    creditNoteBDM.setStatus("PAID");
    creditNoteBDM.setCustomerId("SampleID");
    creditNoteBDM.setRefundCode("RefundSample");
    creditNoteBDM.setExchangeRate(new BigDecimal(10.0));
    creditNoteBDM.setCreditMemoSource(CreditMemoSource.SLA);
    creditNoteBDM.setPaymentMethod("COD");
    CreditNoteLineBDM creditNoteLineBDM = new CreditNoteLineBDM();
    creditNoteLineBDM.setDescription("SampleDesc");
    creditNoteLineBDM.setTotal(BigDecimal.valueOf(100.0));
    creditNoteLineBDM.setOrderLineId("100");
    creditNoteLineBDM.setRevenueStartDate("2023-01-25T08:32:30+05:30");
    creditNoteLineBDM.setRevenueEndDate("2023-01-25T08:32:30+05:30");
    creditNoteLineBDM.setEntitlementId("SampleEntId");
    creditNoteLineBDM.setNewListPrice(BigDecimal.valueOf(150));
    creditNoteLineBDM.setSaleAction("SampleSaleAction");
    creditNoteLineBDM.setHostingType("Sample");
    creditNoteLineBDM.setTotal(BigDecimal.valueOf(100));
    creditNoteLineBDM.setItemId("Cloud (annual):CFLU");
    TaxItemBDM taxItemBDM = new TaxItemBDM();
    taxItemBDM.setTaxRate(BigDecimal.valueOf(110));
    taxItemBDM.setTaxCode("SampleTaxCode");
    taxItemBDM.setTaxAmount(BigDecimal.valueOf(500));
    List<TaxItemBDM> taxItems = new ArrayList<>();
    taxItems.add(taxItemBDM);
    creditNoteLineBDM.setTaxItems(taxItems);
    List<CreditNoteLineBDM> creditMemoLines = new ArrayList<>();
    creditMemoLines.add(creditNoteLineBDM);
    creditNoteBDM.setCreditMemoLines(creditMemoLines);
    AddressBDM billingAddress = new AddressBDM();
    billingAddress.setCountry("US");
    billingAddress.setAddressee("SampleName");
    creditNoteBDM.setBillTo(TestGenHelper.getDummyAddress(true));
    return creditNoteBDM;
  }
}
