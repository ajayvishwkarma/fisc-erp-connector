package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.ContactBDM;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorLookup;
import com.atlassian.fisc.erp.connector.domain.contact.Contact;
import com.atlassian.fisc.erp.connector.lookups.ContactLookup;
import com.atlassian.fisc.erp.connector.lookups.LookupHelper;
import com.atlassian.fisc.erp.connector.mappers.contact.ContactAssertionHelper;
import com.atlassian.fisc.erp.connector.service.ContactService;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.concurrent.atomic.AtomicReference;

@ExtendWith(MockitoExtension.class)
class ContactServiceImplTest {
  @Mock private FiscErpConnectorConfig configProperties;
  @Mock private FiscErpConnectorLookup lookupProperties;
  @Mock private ErpServiceClient erpServiceClient;

  private ContactService contactService;

  @BeforeEach
  void setUp() {
    TestUtil.stubConfigProperties(configProperties);
    TestUtil.stubLookupProperties(lookupProperties);
    contactService =
        new ContactServiceImpl(
            erpServiceClient,
            new ContactLookup(new LookupHelper(configProperties, lookupProperties)));
  }

  @Test
  void getContact() {
    ContactBDM contactBDM =
        TestUtil.deserializeResource("payloads/ContactBDM.json", ContactBDM.class);
    Mockito.lenient().when(erpServiceClient.getContact(Mockito.anyString())).thenReturn(contactBDM);
    Contact nsContactResponse = contactService.getContactDetails("1234");
    new ContactAssertionHelper(configProperties).assertNsContact(contactBDM, nsContactResponse,true);
  }

  @Test
  void createContact() {
    AtomicReference<ContactBDM> contactBdmHolder = new AtomicReference<>();
    Mockito.lenient()
        .when(erpServiceClient.createContact(Mockito.any(ContactBDM.class)))
        .thenAnswer(
            invocation -> {
              ContactBDM contactBDM = (ContactBDM) invocation.getArguments()[0];
              contactBdmHolder.set(contactBDM);
              return contactBDM;
            });

    Contact nsContact =
        contactService.createContact(
            TestUtil.deserializeResource("payloads/NsContact.json", Contact.class));

    Contact nsContactResponse = contactService.createContact(nsContact);

    ContactAssertionHelper asserter = new ContactAssertionHelper(configProperties);
    asserter.assertContact(nsContact, contactBdmHolder.get(),false);
    asserter.assertNsContact(contactBdmHolder.get(), nsContactResponse,false);
  }
}
