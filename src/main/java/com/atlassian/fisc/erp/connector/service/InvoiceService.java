package com.atlassian.fisc.erp.connector.service;

import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import java.util.List;

public interface InvoiceService {
  Invoice getInvoice(String id);

  Invoice createInvoice(Invoice invoiceBDM);
  List<CustomerPayment> getPaymentFromInvoice(String invoiceNumber);
}
