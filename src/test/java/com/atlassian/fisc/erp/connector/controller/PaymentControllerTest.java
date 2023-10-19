package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.service.PaymentService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentControllerTest {

    private PaymentController paymentController;
    @Mock
    PaymentService paymentService;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentController = new PaymentController(paymentService);
    }

    @Nested
    class PaymentApi {

        @Test
        @DisplayName("Should successfully return customerPayment object for provided transaction Id")
        void getCustomer() {
            var response = new CustomerPayment();
            response.setTranId("1");
            when(paymentService.getPaymentDetails(anyString())).thenReturn(response);
            var customerPayment = paymentController.getPaymentDetails("1");
            assertNotNull(customerPayment);
            assertEquals("1", customerPayment.getTranId());
        }

        @Test
        @DisplayName("Should successfully create customerPayment object for provided request")
        void createCustomer() {
            var customerPayment = new CustomerPayment();
            customerPayment.setTranId("1");
            when(paymentService.createPayment(any(CustomerPayment.class))).thenReturn(customerPayment);
            var paymentResponse = paymentController.createPayment(customerPayment);
            assertNotNull(paymentResponse);
            assertEquals("1", customerPayment.getTranId());
        }
    }
}
