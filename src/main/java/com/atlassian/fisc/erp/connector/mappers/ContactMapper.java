package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.ContactBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.domain.contact.Contact;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper(uses = NetSuiteMapperFn.class)
public interface ContactMapper {
  ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

  default Contact convert(ContactBDM contactBDM) {
    return map(contactBDM);
  }

  default ContactBDM convert(Contact nsContact) {
    return map(nsContact);
  }

  /*
   *  Automatically mapped fields
   *    - entityId              Contact-ROW 1
   *    - firstName             Contact-ROW 7
   *    - lastName              Contact-ROW 8
   *    - phone                 Contact-ROW 9
   *    - email                 Contact-ROW 10
   */
  @Mapping(target = "sourceSystem", constant = Constant.SOURCE_SYSTEM_HAMS) // Contact-ROW 2
  @Mapping(target = "id", source = "externalId") // Contact-ROW 3
  @Mapping(target = "contactErpId", source = "internalId") // Contact-ROW 28
  //  TODO @Mapping(target = "contactId/transactionId?", source = "?") //TODO validate //Contact-ROW 1
  @Mapping(
      target = "customerId",
      source = "company",
      qualifiedBy = NetSuiteMapping.class) // TODO validate // Contact-ROW 6
  //  TODO @Mapping(target = "companyType", source = "?") //TODO Revisit Contact-ROW 5
  //  TODO @Mapping(target = "categoryName", source = "?") //TODO Revisit Contact-ROW 12
  //  TODO @Mapping(target = "contactType", source = "?") // TODO Revisit Contact-ROW 13
  //  TODO @Mapping(target = "subsidiaryType", source = "?") //TODO Revisit Contact-ROW 14
  @Mapping(target = "subsidiary", source = "subsidiary", qualifiedBy = NetSuiteMapping.class) //TODO Revisit Contact-ROW 15
  //  TODO @Mapping(target = "contactRelationshipId", source = "?") //Contact-ROW 29
  ContactBDM map(Contact nsContact);

  @InheritInverseConfiguration
  @Mapping(target = "addressbookList", source = ".")
  Contact map(ContactBDM contactBDM);

  @AfterMapping
  default void map(@MappingTarget ContactBDM contactBDM, Contact nsContact) {
    contactBDM.setCompanyName(recordRefName(nsContact.getCompany()));
  }

  @AfterMapping
  default void map(@MappingTarget Contact nsContact, ContactBDM contactBDM) {
    if (Objects.nonNull(nsContact.getCompany())) {
      nsContact.getCompany().setName(contactBDM.getCompanyName());
    }
  }

  default String recordRefName(RecordRef rr) {
    if (null == rr) return null;
    return rr.getName();
  }
}
