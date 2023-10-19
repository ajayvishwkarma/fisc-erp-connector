package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.InvoiceBDM;
import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.finsys.micros.logging.JsonLogV2;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.lookups.InvoiceLookup;
import com.atlassian.fisc.erp.connector.lookups.PaymentLookup;
import com.atlassian.fisc.erp.connector.mappers.InvoiceMapper;
import com.atlassian.fisc.erp.connector.mappers.PaymentMapper;
import com.atlassian.fisc.erp.connector.service.InvoiceService;
import com.atlassian.fisc.erp.connector.util.DateUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class InvoiceServiceImpl implements InvoiceService {
  //  @Autowired through constructor
  private final ErpServiceClient erpServiceClient;
  private final InvoiceLookup lookup;
  private final PaymentLookup paymentLookup;

  @Override
  @JsonLogV2(logOutputAdditionalProperties = "tranId,externalId", suppressErrorLogToWarning = false)
  public Invoice getInvoice(String id) {
    InvoiceBDM invoiceBdmResponse = erpServiceClient.getInvoice(id);

    return lookup.forNsFields(
        InvoiceMapper.INSTANCE.convert(invoiceBdmResponse), invoiceBdmResponse);
  }

  @Override
  @JsonLogV2(
      logId = "tranId",
      logOutputAdditionalProperties = "tranId,externalId",
      suppressErrorLogToWarning = false)
  public Invoice createInvoice(Invoice nsInvoice) {
    lookup.prePopulateNsFields(nsInvoice);

    InvoiceBDM invoiceBDM =
        lookup.forBdmFields(InvoiceMapper.INSTANCE.convert(nsInvoice), nsInvoice);

    InvoiceBDM invoiceBdmResponse = erpServiceClient.createInvoice(invoiceBDM);

    return lookup.forNsFields(
        InvoiceMapper.INSTANCE.convert(invoiceBdmResponse), invoiceBdmResponse);
  }

  @Override
  public List<CustomerPayment> getPaymentFromInvoice(String invoiceNumber) {
    List<PaymentBDM> paymentBdmResponse = erpServiceClient.getPaymentFromInvoice(invoiceNumber);

    paymentBdmResponse.forEach(
        paymentBDM -> {
          paymentBDM.setTransactionCreateDate(
              DateUtil.convertDateTakeOffMillis(paymentBDM.getTransactionCreateDate()));
        });

    return paymentBdmResponse.stream()
        .map(
            paymentBdm ->
                paymentLookup.forNsFields(PaymentMapper.INSTANCE.convert(paymentBdm), paymentBdm))
        .collect(Collectors.toList());
  }
}
