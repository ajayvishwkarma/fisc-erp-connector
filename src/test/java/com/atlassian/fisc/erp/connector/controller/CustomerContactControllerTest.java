package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.service.CustomerService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerContactControllerTest {

  private CustomerController customerContactController;
  @Mock CustomerService customerService;

  @BeforeEach
  @SneakyThrows
  void setUp() {
    MockitoAnnotations.openMocks(this);
    customerContactController = new CustomerController(customerService);
  }

  @Nested
  class CustomerApi {

    @Test
    @DisplayName("Should successfully return customer object for provided id")
    void getCustomer() {
      var nsCustomer = new Customer();
      nsCustomer.setEntityId("1");
      when(customerService.getCustomer(anyString())).thenReturn(nsCustomer);
      Customer nsCustomerResponse = customerContactController.getCustomer("1");
      assertNotNull(nsCustomerResponse);
    }

    @Test
    @DisplayName("Should successfully return customer object for provided id")
    void createCustomer() {
      var nsCustomer = new Customer();
      nsCustomer.setEntityId("1");
      when(customerService.createCustomer(any(Customer.class))).thenReturn(nsCustomer);
      Customer nsCustomerResponse = customerContactController.createCustomer(new Customer());
      assertNotNull(nsCustomerResponse);
    }
  }
}
