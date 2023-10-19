package com.atlassian.fisc.erp.connector.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.service.InvoiceService;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InvoiceControllerTest {
  @Mock InvoiceController invoiceController;

  @Mock InvoiceService invoiceService;

  @BeforeEach
  @SneakyThrows
  void setUp() {
    MockitoAnnotations.openMocks(this);
    invoiceController = new InvoiceController(invoiceService);
  }

  @Nested
  class FiscSspInvoiceApi {

    @Test
    @DisplayName("Should successfully return invoice object for provided id")
    void getInvoiceDetailsTest() throws JsonProcessingException {
      Invoice invoice = new Invoice();
      invoice.setInternalId("123");

      when(invoiceService.getInvoice(anyString())).thenReturn(invoice);
      Invoice invoiceResp = invoiceController.getInvoiceDetails("1");
      assertEquals("123", invoiceResp.getInternalId());
    }

    @Test
    @DisplayName("Should successfully create invoice object")
    void createInvoiceTest() throws JsonProcessingException {

      Invoice invoice = new Invoice();
      invoice.setInternalId("123");
      when(invoiceService.createInvoice(any())).thenReturn(invoice);
      Invoice invoice1 = invoiceController.createInvoice(invoice);
      assertEquals("123", invoice1.getInternalId());
    }
  }

  @Test
  void getStandardReceiptsFromInvoiceTest() {
    Mockito.lenient()
            .when(invoiceService.getPaymentFromInvoice(anyString())
            ).thenReturn(getDummyPaymentBDMList());
    List<CustomerPayment> responseInvoice =
        invoiceController.getStandardReceiptsFromInvoice("invoice");
    assertNotNull(responseInvoice);
    assertEquals(1,responseInvoice.size());
    assertEquals("TEST-HdVwA9EGq6kamgw",responseInvoice.get(0).getTranId());
    assertEquals(100,responseInvoice.get(0).getPayment().intValue());
  }

  private List<CustomerPayment> getDummyPaymentBDMList() {
    return List.of(
    TestUtil.deserializeResource("payloads/get-Standard-Receipts-From-Invoice.json", CustomerPayment.class));
  }
}
