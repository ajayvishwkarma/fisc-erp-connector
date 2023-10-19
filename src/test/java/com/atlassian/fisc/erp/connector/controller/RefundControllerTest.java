package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefund;
import com.atlassian.fisc.erp.connector.service.RefundService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RefundControllerTest {

    private RefundController refundController;
    @Mock
    RefundService refundService;

    @BeforeEach
    @SneakyThrows
    void setUp() {
        MockitoAnnotations.openMocks(this);
        refundController = new RefundController(refundService);
    }

    @Nested
    class RefundApi {
        @Test
        @DisplayName("Should successfully create refund object for provided request")
        void createCustomer() {
            var customerRefund = new CustomerRefund();
            customerRefund.setTranId("1");
            when(refundService.createRefund(any(CustomerRefund.class))).thenReturn(customerRefund);
            var refundResponse = refundController.createRefund(customerRefund);
            assertNotNull(refundResponse);
            assertEquals("1", customerRefund.getTranId());
        }
    }
}
