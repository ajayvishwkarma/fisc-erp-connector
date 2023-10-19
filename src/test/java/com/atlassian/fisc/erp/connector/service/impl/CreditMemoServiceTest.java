package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoApply;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoApplyList;
import com.atlassian.fisc.erp.connector.lookups.CreditMemoLookup;
import com.atlassian.fisc.erp.connector.lookups.LookupHelper;
import com.atlassian.fisc.erp.connector.mappers.creditMemo.CreditMemoAssertionHelper;
import com.atlassian.fisc.erp.connector.mappers.creditMemo.CreditNoteBDMTestGen;
import com.atlassian.fisc.erp.connector.mappers.creditMemo.NsCreditMemoTestGen;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class CreditMemoServiceTest {
  @Mock private ErpServiceClient erpServiceClient;

  @Mock private FiscErpConnectorConfig configProperties;
  @Mock private FiscErpConnectorLookup lookupProperties;

  private CreditMemoServiceImpl creditMemoService;

  @BeforeEach
  void setUp() {
    TestUtil.stubConfigProperties(configProperties);
    TestUtil.stubLookupProperties(lookupProperties);
    creditMemoService =
            new CreditMemoServiceImpl(
                    erpServiceClient,
                    new CreditMemoLookup(new LookupHelper(configProperties, lookupProperties)));
  }

  @Test
  void getCreditMemoTest() {
    CreditNoteBDM creditNoteBdm = new CreditNoteBDMTestGen().createDummy();
    AddressBDM addressBDM = new AddressBDM();
    addressBDM.setCountry("US");
    creditNoteBdm.setBillTo(addressBDM);
    Mockito.lenient()
            .when(erpServiceClient.getCreditNote(Mockito.anyString()))
            .thenReturn(creditNoteBdm);
    CreditMemo nsCreditMemo = creditMemoService.getCreditMemoDetails("123");
    new CreditMemoAssertionHelper().assertNsCreditMemo(creditNoteBdm, nsCreditMemo, true);
  }

  @Test
  void createCreditMemoTest() {
    CreditMemo nsCreditMemo = new NsCreditMemoTestGen().createDummy();
    createCreditMemoTest_Validation(nsCreditMemo);
  }

  @Test
  void createCreditMemoTest_CustomerIDBlank() {
    CreditMemo nsCreditMemo = new NsCreditMemoTestGen().createDummy();
    TestUtil.removeCustomField(nsCreditMemo.getCustomFieldList(),"custbody_end_customer");
    createCreditMemoTest_Validation(nsCreditMemo);
  }

  private void createCreditMemoTest_Validation(CreditMemo nsCreditMemo) {
    final AtomicReference<CreditNoteBDM> creditNoteHolder = new AtomicReference<>();

    Mockito.lenient()
            .when(erpServiceClient.createCreditNote(Mockito.any(CreditNoteBDM.class)))
            .thenAnswer(
                    invocation -> {
                      CreditNoteBDM creditNoteBDM = (CreditNoteBDM) invocation.getArguments()[0];
                      creditNoteHolder.set(creditNoteBDM);
                      return creditNoteBDM;
                    });

    CreditMemo creditMemoReturn = creditMemoService.createCreditMemo(nsCreditMemo);

    CreditMemoAssertionHelper creditMemoAssertionHelper = new CreditMemoAssertionHelper();

    creditMemoAssertionHelper.assertCreditNoteBDM(nsCreditMemo, creditNoteHolder.get(), false);
    creditMemoAssertionHelper.assertNsCreditMemo(creditNoteHolder.get(), creditMemoReturn, true);
  }

  @Test
  void createCreditMemoTestWithEmptyApplyList() {
    CreditMemo nsCreditMemo = new NsCreditMemoTestGen().createDummy();
    nsCreditMemo.setApplyList(new CreditMemoApplyList());

    final AtomicReference<CreditNoteBDM> creditNoteHolder = new AtomicReference<>();

    Mockito.lenient()
            .when(erpServiceClient.createCreditNote(Mockito.any(CreditNoteBDM.class)))
            .thenAnswer(
                    invocation -> {
                      CreditNoteBDM creditNoteBDM = (CreditNoteBDM) invocation.getArguments()[0];
                      creditNoteHolder.set(creditNoteBDM);
                      return creditNoteBDM;
                    });

    CreditMemo creditMemoReturn = creditMemoService.createCreditMemo(nsCreditMemo);

    CreditMemoAssertionHelper creditMemoAssertionHelper = new CreditMemoAssertionHelper();

    creditMemoAssertionHelper.assertCreditNoteBDM(nsCreditMemo, creditNoteHolder.get(), false);
    creditMemoAssertionHelper.assertNsCreditMemo(creditNoteHolder.get(), creditMemoReturn, true);
  }

  @Test
  void createCreditMemoTestWithApplyList() {
    CreditMemo nsCreditMemo = new NsCreditMemoTestGen().createDummy();
    CreditMemoApplyList creditMemoApplyList = new CreditMemoApplyList();
    creditMemoApplyList.setApply(new CreditMemoApply[] {new CreditMemoApply()});
    nsCreditMemo.setApplyList(creditMemoApplyList);

    final AtomicReference<CreditNoteBDM> creditNoteHolder = new AtomicReference<>();

    Mockito.lenient()
            .when(erpServiceClient.createCreditNote(Mockito.any(CreditNoteBDM.class)))
            .thenAnswer(
                    invocation -> {
                      CreditNoteBDM creditNoteBDM = (CreditNoteBDM) invocation.getArguments()[0];
                      creditNoteHolder.set(creditNoteBDM);
                      return creditNoteBDM;
                    });

    CreditMemo creditMemoReturn = creditMemoService.createCreditMemo(nsCreditMemo);

    CreditMemoAssertionHelper creditMemoAssertionHelper = new CreditMemoAssertionHelper();

    creditMemoAssertionHelper.assertCreditNoteBDM(nsCreditMemo, creditNoteHolder.get(), false);
    creditMemoAssertionHelper.assertNsCreditMemo(creditNoteHolder.get(), creditMemoReturn, true);
  }

  @Test
  void applyCreditMemo() {
    CreditMemo nsCreditMemo = new NsCreditMemoTestGen().createDummy();
    applyCreditMemo_validation(nsCreditMemo);
  }
  @Test
  void applyCreditMemoItemListNull() {
    CreditMemo nsCreditMemo = new NsCreditMemoTestGen().createDummyNoItemList();
    applyCreditMemo_validation(nsCreditMemo);
  }
  @Test
  void applyCreditMemoTest_CustomerIDBlank() {
    CreditMemo nsCreditMemo = new NsCreditMemoTestGen().createDummy();
    TestUtil.removeCustomField(nsCreditMemo.getCustomFieldList(), "custbody_end_customer");
    applyCreditMemo_validation(nsCreditMemo);
  }

  private void applyCreditMemo_validation(CreditMemo nsCreditMemo) {
    CreditMemoApplyList creditMemoApplyList = new CreditMemoApplyList();
    CreditMemoApply cmApply = new CreditMemoApply();
    cmApply.setDoc(300000003776718L);
    cmApply.setAmount(10d);
    cmApply.setApplyDate(TestUtil.calDate(2023,03,01));
    creditMemoApplyList.setApply(new CreditMemoApply[] {cmApply});
    nsCreditMemo.setApplyList(creditMemoApplyList);

    final AtomicReference<CreditNoteBDM> creditNoteHolder = new AtomicReference<>();

    Mockito.lenient()
            .when(erpServiceClient.applyCreditNote(Mockito.any(CreditNoteBDM.class)))
            .thenAnswer(
                    invocation -> {
                      CreditNoteBDM creditNoteBDM = (CreditNoteBDM) invocation.getArguments()[0];
                      creditNoteHolder.set(creditNoteBDM);
                      return creditNoteBDM;
                    });

    CreditMemo creditMemoReturn = creditMemoService.applyCreditMemo(nsCreditMemo);

    CreditMemoAssertionHelper creditMemoAssertionHelper = new CreditMemoAssertionHelper();

    creditMemoAssertionHelper.assertCreditNoteBDM(nsCreditMemo, creditNoteHolder.get(), false);
    creditMemoAssertionHelper.assertNsCreditMemo(creditNoteHolder.get(), creditMemoReturn, true);
  }
}
