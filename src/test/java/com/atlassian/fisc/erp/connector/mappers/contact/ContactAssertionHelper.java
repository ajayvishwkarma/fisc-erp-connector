package com.atlassian.fisc.erp.connector.mappers.contact;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.ContactBDM;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.contact.Contact;
import com.atlassian.fisc.erp.connector.domain.contact.ContactAddressbook;
import com.atlassian.fisc.erp.connector.mappers.common.AssertionHelper;
import com.atlassian.fisc.erp.connector.util.TwoWayAsserter;

import static com.atlassian.fisc.erp.connector.mappers.NetSuiteMapperFn.mapToString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ContactAssertionHelper {
  private final AssertionHelper assertionHelper;

  public ContactAssertionHelper(FiscErpConnectorConfig configProperties) {
    this.assertionHelper = new AssertionHelper(configProperties);
  }

  public void assertNsContact(ContactBDM contact, Contact nsContact,boolean expectedFirst) {
    assertNotNull(contact);
    assertNotNull(nsContact);
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    twoWayAsserter.assertEquals(contact.getEntityId(), nsContact.getEntityId());
    twoWayAsserter.assertEquals(contact.getId(), nsContact.getExternalId());
    twoWayAsserter.assertEquals(contact.getCompanyName(), nsContact.getCompany().getName());
    twoWayAsserter.assertEquals(contact.getCustomerId(), mapToString(nsContact.getCompany()));
    twoWayAsserter.assertEquals(contact.getFirstName(), nsContact.getFirstName());
    twoWayAsserter.assertEquals(contact.getLastName(), nsContact.getLastName());
    twoWayAsserter.assertEquals(contact.getPhone(), nsContact.getPhone());
    twoWayAsserter.assertEquals(contact.getEmail(), nsContact.getEmail());
    twoWayAsserter.assertEquals(contact.getSubsidiary().getName(), mapToString(nsContact.getSubsidiary()));


    // TODO contactId? = transactionId? contactBDM.setTransactionId(nsContact.get??());

    // TODO contactBDM.setCompanyType(nsContact.get??());
    // TODO contactBDM.setCategoryName(nsContact.get??());
    // TODO contactBDM.setContactType(nsContact.get??());
    // TODO contactBDM.setSubsidiaryType(nsContact.get??);


    // TODO contactBDM.setContactErpId(nsContact.get??());
    // TODO contactBDM.setContactRelationshipId(nsContact.get??());
  }

  public void assertContact(Contact nsContact, ContactBDM contact,boolean expectedFirst) {
    assertNotNull(contact);
    assertNotNull(nsContact);
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    twoWayAsserter.assertEquals(Constant.SOURCE_SYSTEM_HAMS, contact.getSourceSystem().name());
    twoWayAsserter.assertEquals(nsContact.getEntityId(), contact.getEntityId());
    twoWayAsserter.assertEquals(nsContact.getExternalId(), contact.getId());
    twoWayAsserter.assertEquals(nsContact.getCompany().getName(), contact.getCompanyName());
    twoWayAsserter.assertEquals(mapToString(nsContact.getCompany()), contact.getCustomerId());
    twoWayAsserter.assertEquals(nsContact.getFirstName(), contact.getFirstName());
    twoWayAsserter.assertEquals(nsContact.getLastName(), contact.getLastName());
    twoWayAsserter.assertEquals(nsContact.getEmail(), contact.getEmail());
    twoWayAsserter.assertEquals(mapToString(nsContact.getSubsidiary()), contact.getSubsidiary().getName());



    // TODO contactId? = transactionId? contactBDM.setTransactionId(nsContact.get??());

    // TODO contactBDM.setCompanyType(nsContact.get??());
    // TODO contactBDM.setCategoryName(nsContact.get??());
    // TODO contactBDM.setContactType(nsContact.get??());
    // TODO contactBDM.setSubsidiaryType(nsContact.get??);


    // TODO contactBDM.setContactErpId(nsContact.get??());
    // TODO contactBDM.setContactRelationshipId(nsContact.get??());
  }

  private void validateAddress(
      AddressBDM addressBDM, ContactAddressbook nsAddressbook, boolean expectedFirst) {
    Address nsAddress = nsAddressbook.getAddressbookAddress();
    assertionHelper.validateAddress(addressBDM, nsAddress, expectedFirst);
  }
}
