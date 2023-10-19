package com.atlassian.fisc.erp.connector.service.impl;


import com.atlassian.finance.bdm.InvoiceBDM;
import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.lookups.InvoiceLookup;
import com.atlassian.fisc.erp.connector.lookups.LookupHelper;
import com.atlassian.fisc.erp.connector.lookups.PaymentLookup;
import com.atlassian.fisc.erp.connector.mappers.invoice.InvoiceAssertionHelper;
import com.atlassian.fisc.erp.connector.mappers.invoice.InvoiceTestGen;
import com.atlassian.fisc.erp.connector.mappers.invoice.NsInvoiceTestGen;
import com.atlassian.fisc.erp.connector.util.TestUtil;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class InvoiceServiceImplTest {
  @Mock private FiscErpConnectorConfig configProperties;
  @Mock private FiscErpConnectorLookup lookupProperties;

  @Mock private ErpServiceClient erpServiceClient;

  private InvoiceServiceImpl invoiceService;

  @BeforeEach
  void setUp() {
    TestUtil.stubConfigProperties(configProperties);
    TestUtil.stubLookupProperties(lookupProperties);
    invoiceService =
        new InvoiceServiceImpl(
            erpServiceClient,
            new InvoiceLookup(new LookupHelper(configProperties, lookupProperties)),
            new PaymentLookup(new LookupHelper(configProperties, lookupProperties)));
  }

  @Test
  void getInvoiceTest() {
    InvoiceBDM invoiceBdm = new InvoiceTestGen().generateDummyData();
    Mockito.lenient().when(erpServiceClient.getInvoice(Mockito.anyString())).thenReturn(invoiceBdm);

    Invoice nsInvoice = invoiceService.getInvoice("1d");

    new InvoiceAssertionHelper(configProperties).assertNsInvoice(invoiceBdm, nsInvoice);
  }

  @Test
  void createInvoiceTest() {
    Invoice nsInvoice = new NsInvoiceTestGen().generateDummyData();
    final AtomicReference<InvoiceBDM> invoiceBdmHolder = new AtomicReference<>();
    Mockito.lenient()
        .when(erpServiceClient.createInvoice(Mockito.any(InvoiceBDM.class)))
        .thenAnswer(
            invocation -> {
              InvoiceBDM invoiceBDM = (InvoiceBDM) invocation.getArguments()[0];
              invoiceBdmHolder.set(invoiceBDM);
              return invoiceBDM;
            });

    Invoice nsInvoiceResponse = invoiceService.createInvoice(nsInvoice);
    InvoiceAssertionHelper invoiceAssertionHelper = new InvoiceAssertionHelper(configProperties);
    invoiceAssertionHelper.assertInvoice(nsInvoice, invoiceBdmHolder.get());
    invoiceAssertionHelper.assertNsInvoice(invoiceBdmHolder.get(), nsInvoiceResponse);
  }

  @Test
  void getStandardReceiptsFromInvoiceTest() {

    List<PaymentBDM> reportResponse =
            List.of(
                    TestUtil.deserializeResource("payloads/PaymentBDM.json", PaymentBDM.class));

    Mockito.lenient()
            .when(erpServiceClient.getPaymentFromInvoice(ArgumentMatchers.anyString()))
            .thenReturn(reportResponse);

    List<CustomerPayment> responseInvoice = invoiceService.getPaymentFromInvoice("TEST-NWmH_cjPEDykRCT");
    assertNotNull(responseInvoice);
    assertEquals(1, responseInvoice.size());
    assertEquals("TEST-HdVwA9EGq6kamgw",responseInvoice.get(0).getTranId());
    assertEquals(100,responseInvoice.get(0).getPayment().intValue());
  }
}
