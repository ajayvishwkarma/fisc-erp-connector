package com.atlassian.fisc.erp.connector.client;

import com.atlassian.finance.bdm.*;
import com.atlassian.finance.bdm.lookup.SpokeSystemItemMap;
import com.atlassian.finsys.micros.logging.JsonLogV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.atlassian.fisc.erp.connector.constant.Constant.HTTP_SLAUTH_HEADER;

@Component
@Slf4j
@RequiredArgsConstructor
public class ErpServiceClient {
  // constructor injected dependencies
  private final ClientHelper clientHelper;

  @Value("${erp-service.api.host}")
  private String erpHost;

  @Value("${erp-service.api.customer.path}")
  private String customerUri;

  @Value("${erp-service.api.contact.path}")
  private String contactUri;

  @Value("${erp-service.api.invoice.path}")
  private String invoiceUri;

  @Value("${erp-service.api.payment.path}")
  private String paymentUri;

  @Value("${erp-service.api.creditNote.path}")
  private String creditNoteUri;

  @Value("${erp-service.api.applyCreditNote.path}")
  private String applyCreditNoteUri;

  @Value("${erp-service.api.refund.path}")
  private String refundUri;

  @Value("${erp-service.api.lookup.spokeItemMap.path}")
  private String spokeItemMapUri;

  @Value("${erp-service.api.invoicePayment.path}")
  private String invoicePaymentUri;

  @java.lang.SuppressWarnings(
      "squid:S1192") // SONAR moving "id" as string constant not desired for readability
  public CustomerBDM getCustomer(String id) {
    return callErpService(customerUri + "/{id}", Map.of("id", id), CustomerBDM.class);
  }

  public CustomerBDM createCustomer(CustomerBDM customerBDM) {
    return callErpService(customerUri, customerBDM, Collections.emptyMap(), CustomerBDM.class);
  }

  public ContactBDM getContact(String id) {
    return callErpService(contactUri + "/{id}", Map.of("id", id), ContactBDM.class);
  }

  public ContactBDM createContact(ContactBDM contactBDM) {
    return callErpService(contactUri, contactBDM, Collections.emptyMap(), ContactBDM.class);
  }

  public InvoiceBDM getInvoice(String id) {
    return callErpService(invoiceUri + "/{id}", Map.of("id", id), InvoiceBDM.class);
  }

  public InvoiceBDM createInvoice(InvoiceBDM invoiceBDM) {
    return callErpService(invoiceUri, invoiceBDM, Collections.emptyMap(), InvoiceBDM.class);
  }

  public PaymentBDM getPayment(String transactionNumber) {
    return callErpService(
        paymentUri + "/{transactionNumber}",
        Map.of("transactionNumber", transactionNumber),
        PaymentBDM.class);
  }

  public PaymentBDM createPayment(PaymentBDM payment) {
    return callErpService(paymentUri, payment, Collections.emptyMap(), PaymentBDM.class);
  }

  public CreditNoteBDM getCreditNote(String transactionNumber) {
    return callErpService(
        creditNoteUri + "/{transactionNumber}",
        Map.of("transactionNumber", transactionNumber),
        CreditNoteBDM.class);
  }

  public CreditNoteBDM createCreditNote(CreditNoteBDM creditNoteBDM) {
    return callErpService(
        creditNoteUri, creditNoteBDM, Collections.emptyMap(), CreditNoteBDM.class);
  }

  public CreditNoteBDM applyCreditNote(CreditNoteBDM creditNoteBDM) {
    return callErpService(
        applyCreditNoteUri, creditNoteBDM, Collections.emptyMap(), CreditNoteBDM.class);
  }

  public RefundBDM getRefund(String id) {
    return callErpService(refundUri + "/{id}", Map.of("id", id), RefundBDM.class);
  }

  public RefundBDM createRefund(RefundBDM refundBDM) {
    return callErpService(refundUri, refundBDM, Collections.emptyMap(), RefundBDM.class);
  }

  public List<SpokeSystemItemMap> getHamsSpokeItemMap() {
    var response =
        callErpService(spokeItemMapUri, Collections.emptyMap(), SpokeSystemItemMap[].class);
    return ObjectUtils.isNotEmpty(response) ? Arrays.asList(response) : null;
  }

  public List<PaymentBDM> getPaymentFromInvoice(String invoiceNumber) {
    String uri = MessageFormat.format(invoicePaymentUri,invoiceNumber);
    var response = callErpService(uri, Collections.emptyMap(), PaymentBDM[].class);
    return ObjectUtils.isNotEmpty(response) ? Arrays.asList(response) : null;
  }

  @JsonLogV2()
  private <T> T callErpService(String path, Map<String, String> params, Class<T> type) {
    String requestUrl = erpHost + path;
    var uriComponents =
        UriComponentsBuilder.fromUriString(requestUrl).build().expand(params).encode();
    var uri = uriComponents.toUri();
    var headers = new HttpHeaders();
    headers.add(HTTP_SLAUTH_HEADER, Boolean.TRUE.toString());

    HttpEntity<?> entity = new HttpEntity<>(null, headers);
    ResponseEntity<T> responseEntity = clientHelper.execute(uri, entity, HttpMethod.GET, type);
    return responseEntity.getBody();
  }

  @JsonLogV2()
  private <T> T callErpService(String path, T body, Map<String, String> params, Class<T> type) {
    String requestUrl = erpHost + path;
    var uriComponents =
        UriComponentsBuilder.fromUriString(requestUrl).build().expand(params).encode();
    var uri = uriComponents.toUri();
    var headers = new HttpHeaders();
    headers.add(HTTP_SLAUTH_HEADER, Boolean.TRUE.toString());

    HttpEntity<?> entity = new HttpEntity<>(body, headers);
    ResponseEntity<T> responseEntity = clientHelper.execute(uri, entity, HttpMethod.POST, type);
    return responseEntity.getBody();
  }
}
