package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.domain.contact.Contact;
import com.atlassian.fisc.erp.connector.service.ContactService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FiscSspContactControllerTest {
  @Mock private ContactService contactService;
  @InjectMocks private ContactController contactController;

  @Nested
  class FiscSspContactApi {
    @Test
    @DisplayName("Should successfully return contact object")
    void getContact() {
      when(contactService.getContactDetails(anyString())).thenReturn(new Contact());
      Contact nsContactResponse = contactController.getContactDetails("1");
      assertNotNull(nsContactResponse);
    }

    @Test
    @DisplayName("Should successfully return contact object")
    void createContact() {
      when(contactService.createContact(any(Contact.class))).thenReturn(new Contact());
      Contact nsContactResponse = contactController.createContact(new Contact());
      assertNotNull(nsContactResponse);
    }
  }
}
