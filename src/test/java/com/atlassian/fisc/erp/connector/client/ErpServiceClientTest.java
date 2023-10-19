package com.atlassian.fisc.erp.connector.client;

import com.atlassian.finance.bdm.*;
import com.atlassian.finance.bdm.lookup.SpokeSystemItemMap;
import com.atlassian.fisc.erp.connector.util.LookupTestDataUtil;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
@TestPropertySource(properties = {"erp-service.api.host=20"})
class ErpServiceClientTest {

  @InjectMocks private ErpServiceClient erpServiceClient;

  @Mock private ClientHelper clientHelper;

  private final String erpHost = "http://127.0.0.1:8080/api/v1";

  private final String path = "Dummy";

  @BeforeEach
  void setUp() {

    ReflectionTestUtils.setField(erpServiceClient, "erpHost", erpHost);
    ReflectionTestUtils.setField(erpServiceClient,  "invoicePaymentUri",path);
  }

  @Test
  @DisplayName("Should successfully calling erp service with params")
  void callErpService_genericGetMethodTest() {
    ResponseEntity<Object> expectedResponseEntity =
        new ResponseEntity<>(
            TestUtil.deserializeResource("payloads/RefundBDM.json", RefundBDM.class),
            HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("id", "1");
    RefundBDM actualResponse = erpServiceClient.getRefund("1");
    assertNotNull(actualResponse);
    assertEquals(expectedResponseEntity.getBody(), actualResponse);
  }

  @Test
  @DisplayName("Should successfully calling erp service with params")
  @SneakyThrows
  void callErpService_genericPostMethodTest() {
    ResponseEntity<Object> expectedResponseEntity =
        new ResponseEntity<>(
            TestUtil.deserializeResource("payloads/RefundBDM.json", RefundBDM.class),
            HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    Map<String, String> queryParams = new HashMap<>();
    queryParams.put("id", "1");
    RefundBDM actualResponse = erpServiceClient.createRefund(new RefundBDM());
    assertNotNull(actualResponse);
    assertEquals(expectedResponseEntity.getBody(), actualResponse);
  }

  @Test
  @DisplayName("Should successfully get spokeItemMap from erp-servcie")
  void getHamsSpokeItemMap() {
    var data = LookupTestDataUtil.testDataSpokeItemMapArray();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getHamsSpokeItemMap();
    assertEquals(data.length, response.size());
  }

  @Test
  @DisplayName("Should successfully get spokeItemMap from erp-servcie")
  void getHamsSpokeItemMapEmpty() {
    var emptyData = new SpokeSystemItemMap[] {};
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(emptyData, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getHamsSpokeItemMap();
    assertNull(response);
  }

  @Test
  void applyCreditNote() {
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(new CreditNoteBDM(), HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.applyCreditNote(new CreditNoteBDM());
    assertNotNull(response);
  }

  @Test
  void createCreditNote() {
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(new CreditNoteBDM(), HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.createCreditNote(new CreditNoteBDM());
    assertNotNull(response);
  }

  @Test
  void getCreditNote() {
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(new CreditNoteBDM(), HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getCreditNote("1");
    assertNotNull(response);
  }

  @Test
  void cratePayment() {
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(new PaymentBDM(), HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.createPayment(new PaymentBDM());
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#getCustomer(String)}
   */
  @Test
  @DisplayName("Should successfully get Customer Data from erp-service")
  void getCustomerTest() {
    var data = new CustomerBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getCustomer("123");
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#createCustomer(CustomerBDM)}
   */
  @Test
  @DisplayName("Should successfully create Customer record")
  void createCustomerTest() {
    var data = new CustomerBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.createCustomer(data);
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#getContact(String)}
   */
  @Test
  @DisplayName("Should successfully get Contact Data from erp-service")
  void getCustomer() {
    var data = new ContactBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getContact("123");
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#createContact(ContactBDM)}
   */
  @Test
  @DisplayName("Should successfully create Contact Data")
  void getCustomer1() {
    var data = new ContactBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.createContact(data);
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#getInvoice(String)}
   */
  @Test
  @DisplayName("Should successfully get Invoice Data from erp-service")
  void getInvoiceTest() {
    var data = new InvoiceBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getInvoice("123");
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#createInvoice(InvoiceBDM)}
   */
  @Test
  @DisplayName("Should successfully create Invoice record")
  void createInvoiceTest() {
    var data = new InvoiceBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.createInvoice(data);
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#getPayment(String)}
   */
  @Test
  @DisplayName("Should successfully get Payment Data from erp-service")
  void getPaymentTest() {
    var data = new PaymentBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getPayment("123");
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#createPayment(PaymentBDM)}
   */
  @Test
  @DisplayName("Should successfully create Payment record")
  void createPaymentTest() {
    var data = new PaymentBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.createPayment(data);
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#getCreditNote(String)}
   */
  @Test
  @DisplayName("Should successfully get CreditNote Data from erp-service")
  void getCreditNoteTest() {
    var data = new CreditNoteBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getCreditNote("123");
    assertNotNull(response);
  }

  /**
   * Method under test: {@link ErpServiceClient#createCreditNote(CreditNoteBDM)}
   */
  @Test
  @DisplayName("Should successfully create CreditNote record")
  void createCreditNoteTest() {
    var data = new CreditNoteBDM();
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(data, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.createCreditNote(data);
    assertNotNull(response);
  }

  @Test
  void getPaymentFromInvoiceTest() {
    var reportResponse = new PaymentBDM[] {
                    TestUtil.deserializeResource("payloads/PaymentBDM.json", PaymentBDM.class)};

    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(reportResponse, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var responseInvoice = erpServiceClient.getPaymentFromInvoice("TEST-NWmH_cjPEDykRCT");
    assertNotNull(responseInvoice);
    assertEquals(1, responseInvoice.size());
    assertEquals("TEST-HdVwA9EGq6kamgw", responseInvoice.get(0).getTransactionNumber());
    assertEquals(100, responseInvoice.get(0).getTotalAmount().intValue());
  }

  @Test
  @DisplayName("Should successfully get spokeItemMap from erp-servcie")
  void getPaymentFromInvoiceTestEmpty() {
    var emptyData = new PaymentBDM[] {};
    ResponseEntity<Object> expectedResponseEntity = new ResponseEntity<>(emptyData, HttpStatus.OK);
    stubClientHelper(expectedResponseEntity);
    var response = erpServiceClient.getPaymentFromInvoice("TEST-NWmH_cjPEDykRCT");
    assertNull(response);
  }

  private void stubClientHelper(ResponseEntity<Object> expectedResponseEntity) {
    Mockito.lenient()
        .when(
            clientHelper.execute(
                Mockito.any(URI.class), Mockito.any(), Mockito.any(), Mockito.any(Class.class)))
        .thenReturn(expectedResponseEntity);
  }
}
