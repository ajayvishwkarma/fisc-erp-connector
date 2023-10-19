package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finsys.micros.logging.JsonLogV2;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.domain.contact.Contact;
import com.atlassian.fisc.erp.connector.lookups.ContactLookup;
import com.atlassian.fisc.erp.connector.mappers.ContactMapper;
import com.atlassian.fisc.erp.connector.service.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactServiceImpl implements ContactService {
  //  @Autowired through constructor
  private final ErpServiceClient erpServiceClient;
  private final ContactLookup lookup;

  @Override
  @JsonLogV2(
      logOutputAdditionalProperties = "entityId,externalId",
      suppressErrorLogToWarning = false)
  public Contact getContactDetails(String id) {
    var contactBdmResponse = erpServiceClient.getContact(id);
    return ContactMapper.INSTANCE.convert(contactBdmResponse);
  }

  @Override
  @JsonLogV2(
      logId = "externalId",
      logOutputAdditionalProperties = "entityId,externalId",
      suppressErrorLogToWarning = false)
  public Contact createContact(Contact nsContact) {
    var contactBDM =ContactMapper.INSTANCE.map(nsContact);
    var contactBdmResponse = erpServiceClient.createContact(contactBDM);
    return ContactMapper.INSTANCE.convert(contactBdmResponse);
  }
}
