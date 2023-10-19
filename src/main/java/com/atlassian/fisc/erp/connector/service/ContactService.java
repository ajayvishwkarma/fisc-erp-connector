package com.atlassian.fisc.erp.connector.service;

import com.atlassian.fisc.erp.connector.domain.contact.Contact;

public interface ContactService {

  Contact getContactDetails(String id);

  Contact createContact(Contact nsContact);
}
