package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.CustomerBDM;
import com.atlassian.finance.enums.BusinessUnit;
import com.atlassian.finance.enums.CustomerSourceSystem;
import com.atlassian.finance.enums.PartnerDiscountTier;
import com.atlassian.fisc.erp.connector.domain.common.Country;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.domain.customer.CustomerAddressbookList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {CustomerLookup.class})
@ExtendWith(SpringExtension.class)
class CustomerLookupTest {
    @Autowired
    private CustomerLookup customerLookup;

    @MockBean
    private LookupHelper lookupHelper;

    /**
     * Method under test: {@link CustomerLookup#forBdmFields(CustomerBDM, Customer)}
     */
    @Test
    void testForBdmFields() {
        when(lookupHelper.country((Country) any())).thenReturn("GB");
        when(lookupHelper.currency((String) any())).thenReturn("GBP");

        AddressBDM addressBDM = new AddressBDM();
        addressBDM.setAddressErpId("42 Main St");
        addressBDM.setAddressee("42 Main St");
        addressBDM.setCity("Oxford");
        addressBDM.setCountry("GB");
        addressBDM.setLine1("Line1");
        addressBDM.setLine2("Line2");
        addressBDM.setLine3("Line3");
        addressBDM.setPhone("4105551212");
        addressBDM.setPostalCode("Postal Code");
        addressBDM.setState("MD");

        AddressBDM addressBDM1 = new AddressBDM();
        addressBDM1.setAddressErpId("42 Main St");
        addressBDM1.setAddressee("42 Main St");
        addressBDM1.setCity("Oxford");
        addressBDM1.setCountry("GB");
        addressBDM1.setLine1("Line1");
        addressBDM1.setLine2("Line2");
        addressBDM1.setLine3("Line3");
        addressBDM1.setPhone("4105551212");
        addressBDM1.setPostalCode("Postal Code");
        addressBDM1.setState("MD");

        CustomerBDM customerBDM = new CustomerBDM();
        customerBDM.setBillTo(addressBDM);
        customerBDM.setBillingContactName("Billing Contact Name");
        customerBDM.setCommerceCustomer(true);
        customerBDM.setCurrency("GBP");
        customerBDM.setCustomerAccountId("42");
        customerBDM.setCustomerAccountNumber("42");
        customerBDM.setCustomerActive(true);
        customerBDM.setCustomerCategory("Customer Category");
        customerBDM.setCustomerErpId("42");
        customerBDM.setCustomerId("42");
        customerBDM.setCustomerName("Customer Name");
        customerBDM.setCustomerStatus("Customer Status");
        customerBDM.setEntityId("42");
        customerBDM.setGstNumber("42");
        customerBDM.setHamsId("42");
        customerBDM.setId("42");
        customerBDM.setPartnerDiscountTier(PartnerDiscountTier.SOLUTION_PARTNER_SILVER_5);
        customerBDM.setPerson(true);
        customerBDM.setShipTo(addressBDM1);
        customerBDM.setSourceSystem(CustomerSourceSystem.INTELLUM);
        customerBDM.setStartDate("2020-03-01");
        customerBDM.setSubsidiary(BusinessUnit.ATLASSIAN_US_BU_USD);
        customerBDM.setTerms("Terms");
        customerBDM.setTransactionId("42");
        customerBDM.setVatNumber("42");
        CustomerBDM actualForBdmFieldsResult = customerLookup.forBdmFields(customerBDM, new Customer());
        assertSame(customerBDM, actualForBdmFieldsResult);
        assertEquals("GBP", actualForBdmFieldsResult.getCurrency());
        assertEquals("GB", actualForBdmFieldsResult.getShipTo().getCountry());
        assertEquals("GB", actualForBdmFieldsResult.getBillTo().getCountry());
        verify(lookupHelper, atLeast(1)).country((Country) any());
        verify(lookupHelper).currency((String) any());
    }

    /**
     * Method under test: {@link CustomerLookup#forNsFields(Customer, CustomerBDM)}
     */
    @Test
    void testForNsFields() {
        doNothing().when(lookupHelper).nsCurrency((RecordRef) any());
        Customer customer = new Customer();

        AddressBDM addressBDM = new AddressBDM();
        addressBDM.setAddressErpId("42 Main St");
        addressBDM.setAddressee("42 Main St");
        addressBDM.setCity("Oxford");
        addressBDM.setCountry("GB");
        addressBDM.setLine1("Line1");
        addressBDM.setLine2("Line2");
        addressBDM.setLine3("Line3");
        addressBDM.setPhone("4105551212");
        addressBDM.setPostalCode("Postal Code");
        addressBDM.setState("MD");

        AddressBDM addressBDM1 = new AddressBDM();
        addressBDM1.setAddressErpId("42 Main St");
        addressBDM1.setAddressee("42 Main St");
        addressBDM1.setCity("Oxford");
        addressBDM1.setCountry("GB");
        addressBDM1.setLine1("Line1");
        addressBDM1.setLine2("Line2");
        addressBDM1.setLine3("Line3");
        addressBDM1.setPhone("4105551212");
        addressBDM1.setPostalCode("Postal Code");
        addressBDM1.setState("MD");

        CustomerBDM customerBDM = new CustomerBDM();
        customerBDM.setBillTo(addressBDM);
        customerBDM.setBillingContactName("Billing Contact Name");
        customerBDM.setCommerceCustomer(true);
        customerBDM.setCurrency("GBP");
        customerBDM.setCustomerAccountId("42");
        customerBDM.setCustomerAccountNumber("42");
        customerBDM.setCustomerActive(true);
        customerBDM.setCustomerCategory("Customer Category");
        customerBDM.setCustomerErpId("42");
        customerBDM.setCustomerId("42");
        customerBDM.setCustomerName("Customer Name");
        customerBDM.setCustomerStatus("Customer Status");
        customerBDM.setEntityId("42");
        customerBDM.setGstNumber("42");
        customerBDM.setHamsId("42");
        customerBDM.setId("42");
        customerBDM.setPartnerDiscountTier(PartnerDiscountTier.SOLUTION_PARTNER_SILVER_5);
        customerBDM.setPerson(true);
        customerBDM.setShipTo(addressBDM1);
        customerBDM.setSourceSystem(CustomerSourceSystem.INTELLUM);
        customerBDM.setStartDate("2020-03-01");
        customerBDM.setSubsidiary(BusinessUnit.ATLASSIAN_US_BU_USD);
        customerBDM.setTerms("Terms");
        customerBDM.setTransactionId("42");
        customerBDM.setVatNumber("42");
        assertSame(customer, customerLookup.forNsFields(customer, customerBDM));
        verify(lookupHelper).nsCurrency((RecordRef) any());
    }

    /**
     * Method under test: {@link CustomerLookup#forNsFields(Customer, CustomerBDM)}
     */
    @Test
    void testForNsFields2() {
        doNothing().when(lookupHelper).nsCurrency((RecordRef) any());

        Customer customer = new Customer();
        customer.setAddressbookList(new CustomerAddressbookList());

        AddressBDM addressBDM = new AddressBDM();
        addressBDM.setAddressErpId("42 Main St");
        addressBDM.setAddressee("42 Main St");
        addressBDM.setCity("Oxford");
        addressBDM.setCountry("GB");
        addressBDM.setLine1("Line1");
        addressBDM.setLine2("Line2");
        addressBDM.setLine3("Line3");
        addressBDM.setPhone("4105551212");
        addressBDM.setPostalCode("Postal Code");
        addressBDM.setState("MD");

        AddressBDM addressBDM1 = new AddressBDM();
        addressBDM1.setAddressErpId("42 Main St");
        addressBDM1.setAddressee("42 Main St");
        addressBDM1.setCity("Oxford");
        addressBDM1.setCountry("GB");
        addressBDM1.setLine1("Line1");
        addressBDM1.setLine2("Line2");
        addressBDM1.setLine3("Line3");
        addressBDM1.setPhone("4105551212");
        addressBDM1.setPostalCode("Postal Code");
        addressBDM1.setState("MD");

        CustomerBDM customerBDM = new CustomerBDM();
        customerBDM.setBillTo(addressBDM);
        customerBDM.setBillingContactName("Billing Contact Name");
        customerBDM.setCommerceCustomer(true);
        customerBDM.setCurrency("GBP");
        customerBDM.setCustomerAccountId("42");
        customerBDM.setCustomerAccountNumber("42");
        customerBDM.setCustomerActive(true);
        customerBDM.setCustomerCategory("Customer Category");
        customerBDM.setCustomerErpId("42");
        customerBDM.setCustomerId("42");
        customerBDM.setCustomerName("Customer Name");
        customerBDM.setCustomerStatus("Customer Status");
        customerBDM.setEntityId("42");
        customerBDM.setGstNumber("42");
        customerBDM.setHamsId("42");
        customerBDM.setId("42");
        customerBDM.setPartnerDiscountTier(PartnerDiscountTier.SOLUTION_PARTNER_SILVER_5);
        customerBDM.setPerson(true);
        customerBDM.setShipTo(addressBDM1);
        customerBDM.setSourceSystem(CustomerSourceSystem.INTELLUM);
        customerBDM.setStartDate("2020-03-01");
        customerBDM.setSubsidiary(BusinessUnit.ATLASSIAN_US_BU_USD);
        customerBDM.setTerms("Terms");
        customerBDM.setTransactionId("42");
        customerBDM.setVatNumber("42");
        assertSame(customer, customerLookup.forNsFields(customer, customerBDM));
        verify(lookupHelper).nsCurrency((RecordRef) any());
    }
}

