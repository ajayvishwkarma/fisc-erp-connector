package com.atlassian.fisc.erp.connector.service;

import com.atlassian.fisc.erp.connector.domain.customer.Customer;

public interface CustomerService {

  Customer getCustomer(String id);

  Customer createCustomer(Customer customer);
}
