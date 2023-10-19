package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.lookups.LookupHelper;
import com.atlassian.fisc.erp.connector.lookups.PaymentLookup;
import com.atlassian.fisc.erp.connector.mappers.payment.NsPaymentTestGen;
import com.atlassian.fisc.erp.connector.mappers.payment.PaymentAssertionHelper;
import com.atlassian.fisc.erp.connector.mappers.payment.PaymentTestGen;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {
  @Mock private ErpServiceClient erpServiceClient;
  @Mock private FiscErpConnectorConfig configProperties;
  @Mock private FiscErpConnectorLookup lookupProperties;

  private PaymentServiceImpl paymentService;

  @BeforeEach
  void setUp() {
    TestUtil.stubConfigProperties(configProperties);
    TestUtil.stubLookupProperties(lookupProperties);
    paymentService =
        new PaymentServiceImpl(
            erpServiceClient,
            new PaymentLookup(new LookupHelper(configProperties, lookupProperties)));
  }

  @Test
  void getPaymentTest() {
    PaymentBDM payment = new PaymentTestGen().generateDummyData();
    Mockito.lenient().when(erpServiceClient.getPayment(Mockito.anyString())).thenReturn(payment);

    CustomerPayment nsPayment = paymentService.getPaymentDetails("1234");

    new PaymentAssertionHelper(configProperties).assertNsPayment(payment, nsPayment,true);
  }

  @Test
  void createPaymentTest() {
    CustomerPayment nsPayment = new NsPaymentTestGen().generateDummyData();

    final AtomicReference<PaymentBDM> paymentHolder = new AtomicReference<>();

    Mockito.lenient()
        .when(erpServiceClient.createPayment(Mockito.any(PaymentBDM.class)))
        .thenAnswer(
            invocation -> {
              PaymentBDM payment = (PaymentBDM) invocation.getArguments()[0];
              paymentHolder.set(payment);
              return payment;
            });

    CustomerPayment customerPaymentReturn = paymentService.createPayment(nsPayment);

    PaymentAssertionHelper paymentAssertionHelper = new PaymentAssertionHelper(configProperties);

    paymentAssertionHelper.assertPayment(nsPayment, paymentHolder.get(),false);
    paymentAssertionHelper.assertNsPayment(paymentHolder.get(), customerPaymentReturn,true);
  }
}
