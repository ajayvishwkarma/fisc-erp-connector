package com.atlassian.fisc.erp.connector.mappers.customer;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.CustomerBDM;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.domain.customer.CustomerAddressbook;
import com.atlassian.fisc.erp.connector.mappers.common.AssertionHelper;
import com.atlassian.fisc.erp.connector.util.TwoWayAsserter;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.atlassian.fisc.erp.connector.mappers.NetSuiteMapperFn.mapToString;
import static com.atlassian.fisc.erp.connector.util.TestUtil.epoch;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerAssertionHelper {
  private final FiscErpConnectorConfig configProperties;
  private final AssertionHelper assertionHelper;

  public CustomerAssertionHelper(FiscErpConnectorConfig configProperties) {
    this.configProperties = configProperties;
    this.assertionHelper = new AssertionHelper(configProperties);
  }

  public void assertNsCustomer(CustomerBDM customer, Customer nsCustomer,boolean expectedFirst) {
    assertNotNull(customer);
    assertNotNull(nsCustomer);

    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    twoWayAsserter.assertEquals(customer.getEntityId(), nsCustomer.getEntityId());
    twoWayAsserter.assertEquals(customer.getId(), nsCustomer.getExternalId());
    twoWayAsserter.assertEquals(customer.getCustomerName(), nsCustomer.getCompanyName());
    twoWayAsserter.assertEquals(epoch(customer.getStartDate()),
            epoch(nsCustomer.getStartDate()));
    twoWayAsserter.assertEquals(customer.getPerson(), nsCustomer.getIsPerson());
    twoWayAsserter.assertEquals(customer.getSubsidiary().getName(), nsCustomer.getSubsidiary().getName());
    twoWayAsserter.assertEquals( configProperties.getCurrency().get(customer.getCurrency()),
            mapToString(nsCustomer.getCurrency()));
    twoWayAsserter.assertEquals(customer.getCustomerStatus(), mapToString(nsCustomer.getEntityStatus()));
    twoWayAsserter.assertEquals(customer.getTerms(), mapToString(nsCustomer.getTerms()));


    AddressBDM addressBDMShipTo = customer.getShipTo();
    var nsAddressbookShipTo =
        Arrays.stream(nsCustomer.getAddressbookList().getAddressbook())
            .filter(CustomerAddressbook::getDefaultShipping)
            .collect(Collectors.toList())
            .get(0);

    validateAddress(addressBDMShipTo, nsAddressbookShipTo, true);

    AddressBDM addressBDMBillTo = customer.getBillTo();
    var nsAddressbookBillTo =
        Arrays.stream(nsCustomer.getAddressbookList().getAddressbook())
            .filter(CustomerAddressbook::getDefaultBilling)
            .collect(Collectors.toList())
            .get(0);

    validateAddress(addressBDMBillTo, nsAddressbookBillTo, true);

    validateCustomFields(nsCustomer.getCustomFieldList(), customer,twoWayAsserter);
  }

  public void assertCustomer(Customer nsCustomer, CustomerBDM customer,boolean expectedFirst) {
    assertNotNull(customer);
    assertNotNull(nsCustomer);
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    twoWayAsserter.assertEquals(Constant.SOURCE_SYSTEM_HAMS, customer.getSourceSystem().getName());
    twoWayAsserter.assertEquals(nsCustomer.getEntityId(), customer.getEntityId());
    twoWayAsserter.assertEquals(nsCustomer.getExternalId(), customer.getId());
    twoWayAsserter.assertEquals(nsCustomer.getCompanyName(), customer.getCustomerName());
    twoWayAsserter.assertEquals(epoch(nsCustomer.getStartDate()),
            epoch(customer.getStartDate()));
    twoWayAsserter.assertEquals(nsCustomer.getIsPerson(), customer.getPerson());
    twoWayAsserter.assertEquals(nsCustomer.getSubsidiary().getInternalId(), customer.getSubsidiary().getName());
    twoWayAsserter.assertEquals(configProperties.getCurrency().get(nsCustomer.getCurrency().getInternalId()),
            customer.getCurrency());
    twoWayAsserter.assertEquals(nsCustomer.getEntityStatus().getInternalId(), customer.getCustomerStatus());
    twoWayAsserter.assertEquals(nsCustomer.getTerms().getInternalId(), customer.getTerms());

    AddressBDM addressBDMShipTo = customer.getShipTo();
    var nsAddressbookShipTo =
        Arrays.stream(nsCustomer.getAddressbookList().getAddressbook())
            .filter(CustomerAddressbook::getDefaultShipping)
            .collect(Collectors.toList())
            .get(0);

    validateAddress(addressBDMShipTo, nsAddressbookShipTo, false);

    validateCustomFields(nsCustomer.getCustomFieldList(), customer,twoWayAsserter);
  }

  private void validateAddress(
      AddressBDM addressBDM, CustomerAddressbook nsAddressbook, boolean expectedFirst) {
    Address nsAddress = nsAddressbook.getAddressbookAddress();
    assertionHelper.validateAddress(addressBDM, nsAddress, expectedFirst);
  }

  private void validateCustomFields(CustomFieldList customFieldList, CustomerBDM customer,TwoWayAsserter twoWayAsserter) {
    if (isNull(customFieldList)) {
      return;
    }

    for (CustomFieldRef customFieldRef : customFieldList.getCustomField()) {
      String scriptId = customFieldRef.getScriptId();
      switch (scriptId) {
        case "custentity_iscommerce":
          twoWayAsserter.assertEquals(customFieldRef.getValue(), customer.getCommerceCustomer());
         // assertEquals(customFieldRef.getValue(), customer.getCommerceCustomer());
          break;
        case "custentity_gst_number":
          twoWayAsserter.assertEquals(customFieldRef.getValue(), customer.getGstNumber());
       //   assertEquals(customFieldRef.getValue(), customer.getGstNumber());
          break;
        case "custentity_ccp_customer_category":
          twoWayAsserter.assertEquals(customFieldRef.getValue(), customer.getCustomerCategory());
         // assertEquals(customFieldRef.getValue(), customer.getCustomerCategory());
          break;
        case "custentity_hams_id":
          twoWayAsserter.assertEquals(customFieldRef.getValue(), customer.getHamsId());
         // assertEquals(customFieldRef.getValue(), customer.getHamsId());
          break;
        case "custbody_atl_cust_type":
          twoWayAsserter.assertEquals(customFieldRef.getValue(), customer.getCustomerType());
        //  assertEquals(customFieldRef.getValue(), customer.getCustomerType());
          break;
        case "custentity_billing_contact_name":
          twoWayAsserter.assertEquals(customFieldRef.getValue(), customer.getBillingContactName());
       //   assertEquals(customFieldRef.getValue(), customer.getBillingContactName());
          break;
        default:
          break;
      }
    }
  }
}
