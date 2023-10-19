package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.Country;
import com.atlassian.fisc.erp.connector.domain.contact.ContactAddressbook;
import com.atlassian.fisc.erp.connector.domain.contact.ContactAddressbookList;
import lombok.Generated;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
@Slf4j
@Generated // TODO Remove once changes are done by the developer assigned for this implementation
public class ContactLookup {
  private final LookupHelper helper;

  private static final ContactAddressbook[] EMPTY_AB_ARRAY = new ContactAddressbook[] {};

//  public ContactBDM forBdmFields(ContactBDM contactBDM, Contact nsContact) {
//
//    // country
//    if (nonNull(contactBDM.getShipTo())) {
//      contactBDM
//              .getShipTo()
//              .setCountry(helper.country(countryFromAddress(nsContact.getAddressbookList(), true)));
//    }
//
//    if (nonNull(contactBDM.getBillTo())) {
//      contactBDM
//              .getBillTo()
//              .setCountry(helper.country(countryFromAddress(nsContact.getAddressbookList(), false)));
//    }
//
//    // TODO handle other lookups
//    return contactBDM;
//  }

  private Country countryFromAddress(ContactAddressbookList cabList, boolean shipAddr) {
    if (isNull(cabList) || isEmpty(cabList.getAddressbook())) return null;

    //  select required address
    return Arrays.stream(cabList.getAddressbook())
        .filter(v -> shipAddr && v.getDefaultShipping() || !shipAddr && v.getDefaultBilling())
        .map(ContactAddressbook::getAddressbookAddress)
        .map(Address::getCountry)
        .findFirst()
        .orElse(null);
  }

//  public Contact forNsFields(Contact nsContact, ContactBDM contactBDM) {
//
//    // country
//    ContactAddressbook[] cabs = nonNullCollection(nsContact.getAddressbookList());
//    var shipAddr =
//            Arrays.stream(cabs).filter(ContactAddressbook::getDefaultShipping).findFirst().orElse(null);
//    if (nonNull(shipAddr) && nonNull(shipAddr.getAddressbookAddress())) {
//      shipAddr
//              .getAddressbookAddress()
//              .setCountry(helper.nsCountry(contactBDM.getShipTo().getCountry()));
//    }
//
//    var billAddr =
//            Arrays.stream(cabs).filter(ContactAddressbook::getDefaultBilling).findFirst().orElse(null);
//    if (nonNull(billAddr) && nonNull(billAddr.getAddressbookAddress())) {
//      billAddr
//              .getAddressbookAddress()
//              .setCountry(helper.nsCountry(contactBDM.getBillTo().getCountry()));
//    }
//
//    // TODO: handle other lookups
//    return nsContact;
//  }

  private ContactAddressbook[] nonNullCollection(ContactAddressbookList caList) {
    if (isNull(caList)) return EMPTY_AB_ARRAY;
    return Objects.requireNonNullElse(caList.getAddressbook(), EMPTY_AB_ARRAY);
  }
}
