package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.RefundBDM;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordType;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefund;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefundApply;
import com.atlassian.fisc.erp.connector.lookups.LookupHelper;
import com.atlassian.fisc.erp.connector.lookups.RefundLookup;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class RefundServiceImplTest {
  @Mock private ErpServiceClient erpServiceClient;
  @Mock private FiscErpConnectorConfig configProperties;
  @Mock private FiscErpConnectorLookup lookupProperties;

  private RefundServiceImpl refundService;

  @BeforeEach
  void setUp() {
    TestUtil.stubConfigProperties(configProperties);
    TestUtil.stubLookupProperties(lookupProperties);
    refundService =
        new RefundServiceImpl(
            erpServiceClient,
            new RefundLookup(new LookupHelper(configProperties, lookupProperties)));
  }

  @Test
  void RefundServiceTest_getRefundTest() throws JsonProcessingException {
    Mockito.lenient()
        .when(erpServiceClient.getRefund(Mockito.anyString()))
        .thenReturn(TestUtil.deserializeResource("payloads/RefundBDM.json", RefundBDM.class));
    CustomerRefund refundResponse = refundService.getRefundDetails("1234");
    Assertions.assertNotNull(refundResponse);
    Assertions.assertEquals("Paid To: abc customer", refundResponse.getMemo());
    Assertions.assertEquals("CCP-1001128-32", refundResponse.getCustomer().getInternalId());
    Assertions.assertEquals(
        configProperties.getNsPaymentMethod().get("PAIDCC"),
        refundResponse.getPaymentMethod().getInternalId());
    CustomerRefundApply customerRefundApply = refundResponse.getApplyList().getApply(0);
    Assertions.assertEquals("123456", customerRefundApply.getDoc().toString());
    Assertions.assertEquals("100.0", customerRefundApply.getTotal().toString());
    CustomFieldRef customFieldRef1 = refundResponse.getCustomFieldList().getCustomField(0);
    Assertions.assertEquals("custbody_tns_transaction_receipt_id", customFieldRef1.getScriptId());
    Assertions.assertEquals("test-receipt-1234", customFieldRef1.getValue());
  }

  @Test
  void RefundServiceTest_createRefundTest() throws JsonProcessingException {
    Mockito.lenient()
        .when(erpServiceClient.createRefund(Mockito.any(RefundBDM.class)))
        .thenReturn(TestUtil.deserializeResource("payloads/RefundBDM.json", RefundBDM.class));
    CustomerRefund refund = new CustomerRefund();
    refund.setTransactionNumber("Refund-123");
    RecordRef recordRef = new RecordRef();
    recordRef.setType(RecordType.currency);
    recordRef.setInternalId("1");
    refund.setCurrency(recordRef);
    CustomFieldList customFieldList = new CustomFieldList();
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_tns_transaction_receipt_id");
    customFieldRef.setValue("TNS-123");
    customFieldList.setCustomField(new CustomFieldRef[] {customFieldRef});
    refund.setCustomFieldList(customFieldList);
    CustomerRefund refundResponse = refundService.createRefund(refund);
    Assertions.assertNotNull(refundResponse);
    Assertions.assertEquals("Paid To: abc customer", refundResponse.getMemo());
    Assertions.assertEquals("CCP-1001128-32", refundResponse.getCustomer().getInternalId());
    Assertions.assertEquals( configProperties.getNsPaymentMethod().get("PAIDCC"),
        refundResponse.getPaymentMethod().getInternalId());
    CustomerRefundApply customerRefundApply = refundResponse.getApplyList().getApply(0);
    Assertions.assertEquals("123456", customerRefundApply.getDoc().toString());
    Assertions.assertEquals("100.0", customerRefundApply.getTotal().toString());
    var customFieldRef1 = refundResponse.getCustomFieldList().getCustomField(0);
    Assertions.assertEquals("custbody_tns_transaction_receipt_id", customFieldRef1.getScriptId());
    Assertions.assertEquals("test-receipt-1234", customFieldRef1.getValue());
  }

  @Test
  void RefundServiceTest_createRefund() {
    CustomerRefund nsRefund =
        TestUtil.deserializeResource("payloads/NsRefund.json", CustomerRefund.class);

    final AtomicReference<RefundBDM> refundHolder = new AtomicReference<>();

    Mockito.lenient()
        .when(erpServiceClient.createRefund(Mockito.any(RefundBDM.class)))
        .thenAnswer(
            invocation -> {
              RefundBDM refundBDM = (RefundBDM) invocation.getArguments()[0];
              refundHolder.set(refundBDM);
              return refundBDM;
            });

    CustomerRefund nsRefundResponse = refundService.createRefund(nsRefund);

    //  TODO Implement Assertion helper and 2-way asserter
    Assertions.assertNotNull(nsRefund);
    Assertions.assertNotNull(refundHolder.get());
    Assertions.assertNotNull(nsRefundResponse);
  }
}
