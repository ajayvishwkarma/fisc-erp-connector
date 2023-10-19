package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finsys.micros.logging.JsonLogV2;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.lookups.CustomerLookup;
import com.atlassian.fisc.erp.connector.mappers.CustomerMapper;
import com.atlassian.fisc.erp.connector.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {
  //  @Autowired through constructor
  private final ErpServiceClient erpServiceClient;
  private final CustomerLookup lookup;

  @Override
  @JsonLogV2(
      logOutputAdditionalProperties = "entityId,externalId",
      suppressErrorLogToWarning = false)
  public Customer getCustomer(String id) {
    var customerBdmResponse = erpServiceClient.getCustomer(id);
    return lookup.forNsFields(
        CustomerMapper.INSTANCE.convert(customerBdmResponse), customerBdmResponse);
  }

  @Override
  @JsonLogV2(
      logId = "externalId",
      logOutputAdditionalProperties = "entityId,externalId",
      suppressErrorLogToWarning = false)
  public Customer createCustomer(Customer nsCustomer) {
    var customerBDM = lookup.forBdmFields(CustomerMapper.INSTANCE.convert(nsCustomer), nsCustomer);
    var customerBdmResponse = erpServiceClient.createCustomer(customerBDM);
    return lookup.forNsFields(
        CustomerMapper.INSTANCE.convert(customerBdmResponse), customerBdmResponse);
  }
}
