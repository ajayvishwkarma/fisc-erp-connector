package com.atlassian.fisc.erp.connector.lookups;

import ch.qos.logback.core.rolling.helper.RollingCalendar;
import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.finance.enums.BusinessUnit;
import com.atlassian.finance.enums.CreditMemoSource;
import com.atlassian.finance.enums.CreditNoteFormType;
import com.atlassian.finance.enums.TransactionSourceSystem;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.Country;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoApplyList;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItem;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItemList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CreditMemoLookup.class})
@ExtendWith(SpringExtension.class)
class CreditMemoLookupTest {
    @Autowired
    private CreditMemoLookup creditMemoLookup;

    @MockBean
    private LookupHelper lookupHelper;

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields() {
        CreditMemo creditMemo = new CreditMemo();
        assertSame(creditMemo, creditMemoLookup.prePopulateNsFields(creditMemo));
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields2() {
        CreditMemo creditMemo = new CreditMemo();
        creditMemo.setItemList(new CreditMemoItemList());
        assertSame(creditMemo, creditMemoLookup.prePopulateNsFields(creditMemo));
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields4() {
        CreditMemo creditMemo = mock(CreditMemo.class);
        when(creditMemo.getItemList()).thenReturn(new CreditMemoItemList());
        creditMemoLookup.prePopulateNsFields(creditMemo);
        verify(creditMemo).getItemList();
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields5() {
        CreditMemo creditMemo = mock(CreditMemo.class);
        when(creditMemo.getItemList())
                .thenReturn(new CreditMemoItemList(new CreditMemoItem[]{new CreditMemoItem()}, true));
        creditMemoLookup.prePopulateNsFields(creditMemo);
        verify(creditMemo).getItemList();
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields6() {
        when(lookupHelper.itemOrDefault((String) any(), (String) any())).thenReturn("Item Or Default");
        CreditMemo creditMemo = mock(CreditMemo.class);
        RecordRef job = new RecordRef();
        RecordRef item = new RecordRef();
        RecordRef price = new RecordRef();
        CustomFieldList options = new CustomFieldList();
        RecordRef shipAddress = new RecordRef();
        RecordRef shipMethod = new RecordRef();
        RecordRef taxCode = new RecordRef();
        RecordRef department = new RecordRef();
        RecordRef _class = new RecordRef();
        RecordRef location = new RecordRef();
        RecordRef revRecSchedule = new RecordRef();
        RollingCalendar revRecStartDate = new RollingCalendar("2020-03-01");
        RollingCalendar revRecEndDate = new RollingCalendar("2020-03-01");
        RecordRef units = new RecordRef();
        RecordRef catchUpPeriod = new RecordRef();
        RecordRef chargeType = new RecordRef();
        RecordRef subscriptionLine = new RecordRef();
        when(creditMemo.getItemList()).thenReturn(new CreditMemoItemList(new CreditMemoItem[]{new CreditMemoItem(job,
                item, 1L, 1L, 10.0d, "The characteristics of someone or something", "42", price, "Rate", 10.0d, true, options,
                shipAddress, shipMethod, taxCode, 10.0d, 10.0d, 10.0d, 10.0d, department, _class, location, 10.0d,
                "Tax Details Reference", revRecSchedule, revRecStartDate, 1L, revRecEndDate, units, "42", true,
                "jane.doe@example.org", "Gift Cert Recipient Name", "jane.doe@example.org", "Gift Cert Message", 10.0d, "42",
                true, 10.0d, 10.0d, 10.0d, true, catchUpPeriod, chargeType, subscriptionLine, new CustomFieldList())}, true));
        creditMemoLookup.prePopulateNsFields(creditMemo);
        verify(lookupHelper, atLeast(1)).itemOrDefault((String) any(), (String) any());
        verify(creditMemo).getItemList();
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields7() {
        when(lookupHelper.itemOrDefault((String) any(), (String) any())).thenReturn("Item Or Default");
        CreditMemo creditMemo = mock(CreditMemo.class);
        RecordRef job = new RecordRef();
        RecordRef item = new RecordRef();
        RecordRef price = new RecordRef();
        CustomFieldList options = new CustomFieldList();
        RecordRef shipAddress = new RecordRef();
        RecordRef shipMethod = new RecordRef();
        RecordRef taxCode = new RecordRef();
        RecordRef department = new RecordRef();
        RecordRef location = new RecordRef();
        RecordRef revRecSchedule = new RecordRef();
        RollingCalendar revRecStartDate = new RollingCalendar("2020-03-01");
        RollingCalendar revRecEndDate = new RollingCalendar("2020-03-01");
        RecordRef units = new RecordRef();
        RecordRef catchUpPeriod = new RecordRef();
        RecordRef chargeType = new RecordRef();
        RecordRef subscriptionLine = new RecordRef();
        when(creditMemo.getItemList()).thenReturn(new CreditMemoItemList(new CreditMemoItem[]{new CreditMemoItem(job,
                item, 1L, 1L, 10.0d, "The characteristics of someone or something", "42", price, "Rate", 10.0d, true, options,
                shipAddress, shipMethod, taxCode, 10.0d, 10.0d, 10.0d, 10.0d, department, null, location, 10.0d,
                "Tax Details Reference", revRecSchedule, revRecStartDate, 1L, revRecEndDate, units, "42", true,
                "jane.doe@example.org", "Gift Cert Recipient Name", "jane.doe@example.org", "Gift Cert Message", 10.0d, "42",
                true, 10.0d, 10.0d, 10.0d, true, catchUpPeriod, chargeType, subscriptionLine, new CustomFieldList())}, true));
        creditMemoLookup.prePopulateNsFields(creditMemo);
        verify(lookupHelper, atLeast(1)).itemOrDefault((String) any(), (String) any());
        verify(creditMemo).getItemList();
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields9() {
        when(lookupHelper.itemOrDefault((String) any(), (String) any())).thenReturn("Item Or Default");
        CreditMemoItem creditMemoItem = mock(CreditMemoItem.class);
        when(creditMemoItem.get_class()).thenReturn(new RecordRef());
        when(creditMemoItem.getItem()).thenReturn(new RecordRef());
        CreditMemo creditMemo = mock(CreditMemo.class);
        when(creditMemo.getItemList()).thenReturn(new CreditMemoItemList(new CreditMemoItem[]{creditMemoItem}, true));
        creditMemoLookup.prePopulateNsFields(creditMemo);
        verify(lookupHelper, atLeast(1)).itemOrDefault((String) any(), (String) any());
        verify(creditMemo).getItemList();
        verify(creditMemoItem, atLeast(1)).getItem();
        verify(creditMemoItem).get_class();
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields10() {
        when(lookupHelper.itemOrDefault((String) any(), (String) any())).thenReturn("Item Or Default");
        CreditMemo creditMemo = mock(CreditMemo.class);
        when(creditMemo.getItemList()).thenReturn(new CreditMemoItemList(new CreditMemoItem[]{}, true));
        creditMemoLookup.prePopulateNsFields(creditMemo);
        verify(creditMemo).getItemList();
    }

    /**
     * Method under test: {@link CreditMemoLookup#prePopulateNsFields(CreditMemo)}
     */
    @Test
    void testPrePopulateNsFields11() {
        when(lookupHelper.itemOrDefault((String) any(), (String) any())).thenReturn("Item Or Default");
        CreditMemo creditMemo = mock(CreditMemo.class);
        CreditMemoItem creditMemoItem = new CreditMemoItem();
        when(creditMemo.getItemList())
                .thenReturn(new CreditMemoItemList(new CreditMemoItem[]{creditMemoItem, new CreditMemoItem()}, true));
        creditMemoLookup.prePopulateNsFields(creditMemo);
        verify(creditMemo).getItemList();
    }

    /**
     * Method under test: {@link CreditMemoLookup#forBdmFields(CreditNoteBDM, CreditMemo)}
     */
    @Test
    void testForBdmFields() {
        when(lookupHelper.currency((RecordRef) any())).thenReturn("GBP");

        AddressBDM addressBDM = new AddressBDM();
        addressBDM.setAddressErpId("42 Main St");
        addressBDM.setAddressee("42 Main St");
        addressBDM.setCity("Oxford");
        addressBDM.setCountry("GB");
        addressBDM.setLine1("Line1");
        addressBDM.setLine2("Line2");
        addressBDM.setLine3("Line3");
        addressBDM.setPhone("4105551212");
        addressBDM.setPostalCode("Postal Code");
        addressBDM.setState("MD");

        CreditNoteBDM creditNoteBDM = new CreditNoteBDM();
        creditNoteBDM.setAccountId("42");
        creditNoteBDM.setAppliedAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setApplyCreditMemoToInvoice(true);
        creditNoteBDM.setBillTo(addressBDM);
        creditNoteBDM.setBillingContactEmail("jane.doe@example.org");
        creditNoteBDM.setBillingContactId("42");
        creditNoteBDM.setCcpOnAccountCredit(true);
        creditNoteBDM.setCreditMemoLines(new ArrayList<>());
        creditNoteBDM.setCreditMemoSource(CreditMemoSource.SLA);
        creditNoteBDM.setCreditNoteApplyLines(new ArrayList<>());
        creditNoteBDM.setCreditNoteCreationReasonId("42");
        creditNoteBDM.setCreditnoteErpId("42");
        creditNoteBDM.setCurrency("GBP");
        creditNoteBDM.setCustomerId("42");
        creditNoteBDM.setCustomerType("Aggregator");
        creditNoteBDM.setExchangeRate(BigDecimal.valueOf(42L));
        creditNoteBDM.setFormType(CreditNoteFormType.OPSGENIE_CM);
        creditNoteBDM.setId("42");
        creditNoteBDM.setInvoiceId("42");
        creditNoteBDM.setIssueRefund(true);
        creditNoteBDM.setMemo("Memo");
        creditNoteBDM.setPaymentMethod("Payment Method");
        creditNoteBDM.setPurchaseOrderNumber("42");
        creditNoteBDM.setRefundCode("Refund Code");
        creditNoteBDM.setRefundReasonDescription("Just cause");
        creditNoteBDM.setSlaCreditAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setSlaCreditCheck(true);
        creditNoteBDM.setSourceSystem(TransactionSourceSystem.INTELLUM_INTEGRATION);
        creditNoteBDM.setSubsidiaryId(BusinessUnit.ATLASSIAN_US_BU_USD);
        creditNoteBDM.setTaxPrintAddress("42 Main St");
        creditNoteBDM.setTechnicalContactId("42");
        creditNoteBDM.setTransactionDate("2020-03-01");
        creditNoteBDM.setTransactionNumber("42");
        CreditNoteBDM actualForBdmFieldsResult = creditMemoLookup.forBdmFields(creditNoteBDM, new CreditMemo());
        assertSame(creditNoteBDM, actualForBdmFieldsResult);
        assertEquals("GBP", actualForBdmFieldsResult.getCurrency());
        assertEquals("42", actualForBdmFieldsResult.getSlaCreditAmount().toString());
        assertEquals("42", actualForBdmFieldsResult.getAppliedAmount().toString());
        verify(lookupHelper).currency((RecordRef) any());
    }

    /**
     * Method under test: {@link CreditMemoLookup#forBdmFields(CreditNoteBDM, CreditMemo)}
     */
    @Test
    void testForBdmFields2() {
        when(lookupHelper.country((Country) any())).thenReturn("GB");
        when(lookupHelper.currency((RecordRef) any())).thenReturn("GBP");

        AddressBDM addressBDM = new AddressBDM();
        addressBDM.setAddressErpId("42 Main St");
        addressBDM.setAddressee("42 Main St");
        addressBDM.setCity("Oxford");
        addressBDM.setCountry("GB");
        addressBDM.setLine1("Line1");
        addressBDM.setLine2("Line2");
        addressBDM.setLine3("Line3");
        addressBDM.setPhone("4105551212");
        addressBDM.setPostalCode("Postal Code");
        addressBDM.setState("MD");

        CreditNoteBDM creditNoteBDM = new CreditNoteBDM();
        creditNoteBDM.setAccountId("42");
        creditNoteBDM.setAppliedAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setApplyCreditMemoToInvoice(true);
        creditNoteBDM.setBillTo(addressBDM);
        creditNoteBDM.setBillingContactEmail("jane.doe@example.org");
        creditNoteBDM.setBillingContactId("42");
        creditNoteBDM.setCcpOnAccountCredit(true);
        creditNoteBDM.setCreditMemoLines(new ArrayList<>());
        creditNoteBDM.setCreditMemoSource(CreditMemoSource.SLA);
        creditNoteBDM.setCreditNoteApplyLines(new ArrayList<>());
        creditNoteBDM.setCreditNoteCreationReasonId("42");
        creditNoteBDM.setCreditnoteErpId("42");
        creditNoteBDM.setCurrency("GBP");
        creditNoteBDM.setCustomerId("42");
        creditNoteBDM.setCustomerType("Aggregator");
        creditNoteBDM.setExchangeRate(BigDecimal.valueOf(42L));
        creditNoteBDM.setFormType(CreditNoteFormType.OPSGENIE_CM);
        creditNoteBDM.setId("42");
        creditNoteBDM.setInvoiceId("42");
        creditNoteBDM.setIssueRefund(true);
        creditNoteBDM.setMemo("Memo");
        creditNoteBDM.setPaymentMethod("Payment Method");
        creditNoteBDM.setPurchaseOrderNumber("42");
        creditNoteBDM.setRefundCode("Refund Code");
        creditNoteBDM.setRefundReasonDescription("Just cause");
        creditNoteBDM.setSlaCreditAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setSlaCreditCheck(true);
        creditNoteBDM.setSourceSystem(TransactionSourceSystem.INTELLUM_INTEGRATION);
        creditNoteBDM.setSubsidiaryId(BusinessUnit.ATLASSIAN_US_BU_USD);
        creditNoteBDM.setTaxPrintAddress("42 Main St");
        creditNoteBDM.setTechnicalContactId("42");
        creditNoteBDM.setTransactionDate("2020-03-01");
        creditNoteBDM.setTransactionNumber("42");
        RollingCalendar createdDate = new RollingCalendar("2020-03-01");
        RollingCalendar lastModifiedDate = new RollingCalendar("2020-03-01");
        RecordRef nexus = new RecordRef();
        RecordRef subsidiaryTaxRegNum = new RecordRef();
        RecordRef customForm = new RecordRef();
        RecordRef currency = new RecordRef();
        RecordRef entity = new RecordRef();
        RollingCalendar tranDate = new RollingCalendar("2020-03-01");
        RecordRef entityTaxRegNum = new RecordRef();
        RecordRef createdFrom = new RecordRef();
        RecordRef postingPeriod = new RecordRef();
        RecordRef department = new RecordRef();
        RecordRef _class = new RecordRef();
        RecordRef location = new RecordRef();
        RecordRef subsidiary = new RecordRef();
        RecordRef job = new RecordRef();
        RecordRef salesRep = new RecordRef();
        RecordRef partner = new RecordRef();
        RecordRef leadSource = new RecordRef();
        RecordRef account = new RecordRef();
        RollingCalendar salesEffectiveDate = new RollingCalendar("2020-03-01");
        RecordRef promoCode = new RecordRef();
        RecordRef discountItem = new RecordRef();
        RecordRef taxItem = new RecordRef();
        RecordRef messageSel = new RecordRef();
        Address billingAddress = new Address();
        RecordRef billAddressList = new RecordRef();
        RecordRef shipMethod = new RecordRef();
        RecordRef shippingTaxCode = new RecordRef();
        RecordRef handlingTaxCode = new RecordRef();
        RecordRef salesGroup = new RecordRef();
        RecordRef giftCert = new RecordRef();
        CreditMemoItemList itemList = new CreditMemoItemList();

        CreditMemoApplyList applyList = new CreditMemoApplyList();
        CreditNoteBDM actualForBdmFieldsResult = creditMemoLookup.forBdmFields(creditNoteBDM,
                new CreditMemo("42", "42", createdDate, lastModifiedDate, nexus, subsidiaryTaxRegNum, true, true, customForm,
                        currency, entity, "Vat Reg Num", tranDate, "42", entityTaxRegNum, createdFrom, postingPeriod, department,
                        _class, location, subsidiary, job, salesRep, partner, "Contrib Pct", "Other Ref Num", "Memo", true,
                        leadSource, 10.0d, account, 10.0d, "On Credit Hold", 10.0d, salesEffectiveDate, 10.0d, 10.0d, 10.0d,
                        "GBP", promoCode, 10.0d, discountItem, "Source", "3", true, taxItem, 10.0d, 10.0d, true, 10.0d, true,
                        true, "jane.doe@example.org", true, "Fax", messageSel, "Not all who wander are lost", billingAddress,
                        billAddressList, shipMethod, 10.0d, 10.0d, shippingTaxCode, handlingTaxCode, "Shipping Tax2 Rate", 10.0d,
                        "Handling Tax2 Rate", 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d,
                        salesGroup, true, "Status", giftCert, 10.0d, 10.0d, 10.0d, true, true, true, itemList, applyList,
                        new CustomFieldList()));
        assertSame(creditNoteBDM, actualForBdmFieldsResult);
        assertEquals("GBP", actualForBdmFieldsResult.getCurrency());
        assertEquals("42", actualForBdmFieldsResult.getSlaCreditAmount().toString());
        assertEquals("GB", actualForBdmFieldsResult.getBillTo().getCountry());
        assertEquals("42", actualForBdmFieldsResult.getAppliedAmount().toString());
        verify(lookupHelper).country((Country) any());
        verify(lookupHelper).currency((RecordRef) any());
    }

    /**
     * Method under test: {@link CreditMemoLookup#forNsFields(CreditMemo, CreditNoteBDM)}
     */
    @Test
    void testForNsFields() {
        CreditMemo creditMemo = new CreditMemo();
        CreditMemoItemList creditMemoItemList= new CreditMemoItemList();
        CreditMemoItem[] creditMemoItem = {};
        creditMemoItemList.setItem(creditMemoItem);
        creditMemo.setItemList(creditMemoItemList);

        AddressBDM addressBDM = new AddressBDM();
        addressBDM.setAddressErpId("42 Main St");
        addressBDM.setAddressee("42 Main St");
        addressBDM.setCity("Oxford");
        addressBDM.setCountry("GB");
        addressBDM.setLine1("Line1");
        addressBDM.setLine2("Line2");
        addressBDM.setLine3("Line3");
        addressBDM.setPhone("4105551212");
        addressBDM.setPostalCode("Postal Code");
        addressBDM.setState("MD");

        CreditNoteBDM creditNoteBDM = new CreditNoteBDM();
        creditNoteBDM.setAccountId("42");
        creditNoteBDM.setAppliedAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setApplyCreditMemoToInvoice(true);
        creditNoteBDM.setBillTo(addressBDM);
        creditNoteBDM.setBillingContactEmail("jane.doe@example.org");
        creditNoteBDM.setBillingContactId("42");
        creditNoteBDM.setCcpOnAccountCredit(true);
        creditNoteBDM.setCreditMemoLines(new ArrayList<>());
        creditNoteBDM.setCreditMemoSource(CreditMemoSource.SLA);
        creditNoteBDM.setCreditNoteApplyLines(new ArrayList<>());
        creditNoteBDM.setCreditNoteCreationReasonId("42");
        creditNoteBDM.setCreditnoteErpId("42");
        creditNoteBDM.setCurrency("GBP");
        creditNoteBDM.setCustomerId("42");
        creditNoteBDM.setCustomerType("Aggregator");
        creditNoteBDM.setExchangeRate(BigDecimal.valueOf(42L));
        creditNoteBDM.setFormType(CreditNoteFormType.OPSGENIE_CM);
        creditNoteBDM.setId("42");
        creditNoteBDM.setInvoiceId("42");
        creditNoteBDM.setIssueRefund(true);
        creditNoteBDM.setMemo("Memo");
        creditNoteBDM.setPaymentMethod("Payment Method");
        creditNoteBDM.setPurchaseOrderNumber("42");
        creditNoteBDM.setRefundCode("Refund Code");
        creditNoteBDM.setRefundReasonDescription("Just cause");
        creditNoteBDM.setSlaCreditAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setSlaCreditCheck(true);
        creditNoteBDM.setSourceSystem(TransactionSourceSystem.INTELLUM_INTEGRATION);
        creditNoteBDM.setSubsidiaryId(BusinessUnit.ATLASSIAN_US_BU_USD);
        creditNoteBDM.setTaxPrintAddress("42 Main St");
        creditNoteBDM.setTechnicalContactId("42");
        creditNoteBDM.setTransactionDate("2020-03-01");
        creditNoteBDM.setTransactionNumber("42");
        assertSame(creditMemo, creditMemoLookup.forNsFields(creditMemo, creditNoteBDM));
    }

    /**
     * Method under test: {@link CreditMemoLookup#forNsFields(CreditMemo, CreditNoteBDM)}
     */
    @Test
    void testForNsFields2() {
        when(lookupHelper.nsCountry((String) any())).thenReturn(mock(Country.class));
        RollingCalendar createdDate = new RollingCalendar("2020-03-01");
        RollingCalendar lastModifiedDate = new RollingCalendar("2020-03-01");
        RecordRef nexus = new RecordRef();
        RecordRef subsidiaryTaxRegNum = new RecordRef();
        RecordRef customForm = new RecordRef();
        RecordRef currency = new RecordRef();
        RecordRef entity = new RecordRef();
        RollingCalendar tranDate = new RollingCalendar("2020-03-01");
        RecordRef entityTaxRegNum = new RecordRef();
        RecordRef createdFrom = new RecordRef();
        RecordRef postingPeriod = new RecordRef();
        RecordRef department = new RecordRef();
        RecordRef _class = new RecordRef();
        RecordRef location = new RecordRef();
        RecordRef subsidiary = new RecordRef();
        RecordRef job = new RecordRef();
        RecordRef salesRep = new RecordRef();
        RecordRef partner = new RecordRef();
        RecordRef leadSource = new RecordRef();
        RecordRef account = new RecordRef();
        RollingCalendar salesEffectiveDate = new RollingCalendar("2020-03-01");
        RecordRef promoCode = new RecordRef();
        RecordRef discountItem = new RecordRef();
        RecordRef taxItem = new RecordRef();
        RecordRef messageSel = new RecordRef();
        Address billingAddress = new Address();
        RecordRef billAddressList = new RecordRef();
        RecordRef shipMethod = new RecordRef();
        RecordRef shippingTaxCode = new RecordRef();
        RecordRef handlingTaxCode = new RecordRef();
        RecordRef salesGroup = new RecordRef();
        RecordRef giftCert = new RecordRef();
        CreditMemoItemList itemList = new CreditMemoItemList();
        CreditMemoItem[] creditMemoItem = {};
        itemList.setItem(creditMemoItem);
        CreditMemoApplyList applyList = new CreditMemoApplyList();
        CreditMemo creditMemo = new CreditMemo("42", "42", createdDate, lastModifiedDate, nexus, subsidiaryTaxRegNum,
                true, true, customForm, currency, entity, "Vat Reg Num", tranDate, "42", entityTaxRegNum, createdFrom,
                postingPeriod, department, _class, location, subsidiary, job, salesRep, partner, "Contrib Pct",
                "Other Ref Num", "Memo", true, leadSource, 10.0d, account, 10.0d, "On Credit Hold", 10.0d, salesEffectiveDate,
                10.0d, 10.0d, 10.0d, "GBP", promoCode, 10.0d, discountItem, "Source", "3", true, taxItem, 10.0d, 10.0d, true,
                10.0d, true, true, "jane.doe@example.org", true, "Fax", messageSel, "Not all who wander are lost",
                billingAddress, billAddressList, shipMethod, 10.0d, 10.0d, shippingTaxCode, handlingTaxCode,
                "Shipping Tax2 Rate", 10.0d, "Handling Tax2 Rate", 10.0d, 10.0d, 10.0d, 10.0d, 10.0d, true, 10.0d, 10.0d,
                10.0d, 10.0d, true, 10.0d, salesGroup, true, "Status", giftCert, 10.0d, 10.0d, 10.0d, true, true, true,
                itemList, applyList, new CustomFieldList());

        AddressBDM addressBDM = new AddressBDM();
        addressBDM.setAddressErpId("42 Main St");
        addressBDM.setAddressee("42 Main St");
        addressBDM.setCity("Oxford");
        addressBDM.setCountry("GB");
        addressBDM.setLine1("Line1");
        addressBDM.setLine2("Line2");
        addressBDM.setLine3("Line3");
        addressBDM.setPhone("4105551212");
        addressBDM.setPostalCode("Postal Code");
        addressBDM.setState("MD");

        CreditNoteBDM creditNoteBDM = new CreditNoteBDM();
        creditNoteBDM.setAccountId("42");
        creditNoteBDM.setAppliedAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setApplyCreditMemoToInvoice(true);
        creditNoteBDM.setBillTo(addressBDM);
        creditNoteBDM.setBillingContactEmail("jane.doe@example.org");
        creditNoteBDM.setBillingContactId("42");
        creditNoteBDM.setCcpOnAccountCredit(true);
        creditNoteBDM.setCreditMemoLines(new ArrayList<>());
        creditNoteBDM.setCreditMemoSource(CreditMemoSource.SLA);
        creditNoteBDM.setCreditNoteApplyLines(new ArrayList<>());
        creditNoteBDM.setCreditNoteCreationReasonId("42");
        creditNoteBDM.setCreditnoteErpId("42");
        creditNoteBDM.setCurrency("GBP");
        creditNoteBDM.setCustomerId("42");
        creditNoteBDM.setCustomerType("Aggregator");
        creditNoteBDM.setExchangeRate(BigDecimal.valueOf(42L));
        creditNoteBDM.setFormType(CreditNoteFormType.OPSGENIE_CM);
        creditNoteBDM.setId("42");
        creditNoteBDM.setInvoiceId("42");
        creditNoteBDM.setIssueRefund(true);
        creditNoteBDM.setMemo("Memo");
        creditNoteBDM.setPaymentMethod("Payment Method");
        creditNoteBDM.setPurchaseOrderNumber("42");
        creditNoteBDM.setRefundCode("Refund Code");
        creditNoteBDM.setRefundReasonDescription("Just cause");
        creditNoteBDM.setSlaCreditAmount(BigDecimal.valueOf(42L));
        creditNoteBDM.setSlaCreditCheck(true);
        creditNoteBDM.setSourceSystem(TransactionSourceSystem.INTELLUM_INTEGRATION);
        creditNoteBDM.setSubsidiaryId(BusinessUnit.ATLASSIAN_US_BU_USD);
        creditNoteBDM.setTaxPrintAddress("42 Main St");
        creditNoteBDM.setTechnicalContactId("42");
        creditNoteBDM.setTransactionDate("2020-03-01");
        creditNoteBDM.setTransactionNumber("42");
        assertSame(creditMemo, creditMemoLookup.forNsFields(creditMemo, creditNoteBDM));
        verify(lookupHelper).nsCountry((String) any());
    }
}

