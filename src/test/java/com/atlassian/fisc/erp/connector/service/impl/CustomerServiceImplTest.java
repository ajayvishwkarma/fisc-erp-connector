package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.CustomerBDM;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.lookups.CustomerLookup;
import com.atlassian.fisc.erp.connector.lookups.LookupHelper;
import com.atlassian.fisc.erp.connector.mappers.customer.CustomerAssertionHelper;
import com.atlassian.fisc.erp.connector.service.CustomerService;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {
  @Mock private FiscErpConnectorConfig configProperties;
  @Mock private FiscErpConnectorLookup lookupProperties;

  @Mock ErpServiceClient erpServiceClient;

  private CustomerService customerService;

  @BeforeEach
  void setUp() {
    TestUtil.stubConfigProperties(configProperties);
    TestUtil.stubLookupProperties(lookupProperties);
    customerService =
        new CustomerServiceImpl(
            erpServiceClient, new CustomerLookup(new LookupHelper(configProperties, lookupProperties)));
  }

  @Test
  void getCustomer() {
    CustomerBDM customerBDM =
        TestUtil.deserializeResource("payloads/CustomerBDM.json", CustomerBDM.class);
    Mockito.lenient()
        .when(erpServiceClient.getCustomer(Mockito.anyString()))
        .thenReturn(customerBDM);
    Customer nsCustomerResponse = customerService.getCustomer("1234");
    new CustomerAssertionHelper(configProperties).assertNsCustomer(customerBDM, nsCustomerResponse,true);
  }

  @Test
  void createCustomer() {
    final AtomicReference<CustomerBDM> customerBdmHolder = new AtomicReference<>();
    Mockito.lenient()
        .when(erpServiceClient.createCustomer(Mockito.any(CustomerBDM.class)))
        .thenAnswer(
            invocation -> {
              CustomerBDM customerBDM = (CustomerBDM) invocation.getArguments()[0];
              customerBdmHolder.set(customerBDM);
              return customerBDM;
            });

    Customer nsCustomer = TestUtil.deserializeResource("payloads/NsCustomer.json", Customer.class);
    Customer nsCustomerResponse = customerService.createCustomer(nsCustomer);
    CustomerBDM customerBDM = customerBdmHolder.get();
    CustomerAssertionHelper asserter = new CustomerAssertionHelper(configProperties);
    asserter.assertCustomer(nsCustomer, customerBDM,false);
    asserter.assertNsCustomer(customerBDM, nsCustomerResponse,true);
  }
}
