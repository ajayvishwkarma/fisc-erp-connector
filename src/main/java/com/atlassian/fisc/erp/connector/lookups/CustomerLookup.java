package com.atlassian.fisc.erp.connector.lookups;

import com.atlassian.finance.bdm.CustomerBDM;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.Country;
import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.domain.customer.CustomerAddressbook;
import com.atlassian.fisc.erp.connector.domain.customer.CustomerAddressbookList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerLookup {
  //  constructor injected dependencies
  private final LookupHelper helper;

  private static final CustomerAddressbook[] EMPTY_AB_ARRAY = new CustomerAddressbook[] {};

  public CustomerBDM forBdmFields(CustomerBDM customerBDM, Customer nsCustomer) {
    // handle currency
    customerBDM.setCurrency(helper.currency(customerBDM.getCurrency()));

    // country
    if (nonNull(customerBDM.getShipTo())) {
      customerBDM
          .getShipTo()
          .setCountry(helper.country(countryFromAddress(nsCustomer.getAddressbookList(), true)));
    }

    if (nonNull(customerBDM.getBillTo())) {
      customerBDM
          .getBillTo()
          .setCountry(helper.country(countryFromAddress(nsCustomer.getAddressbookList(), false)));
    }

    // TODO handle other lookups
    return customerBDM;
  }

  private Country countryFromAddress(CustomerAddressbookList cabList, boolean shipAddr) {
    if (isNull(cabList) || isEmpty(cabList.getAddressbook())) return null;

    //  select required address
    return Arrays.stream(cabList.getAddressbook())
        .filter(v -> shipAddr && v.getDefaultShipping() || !shipAddr && v.getDefaultBilling())
        .map(CustomerAddressbook::getAddressbookAddress)
        .map(Address::getCountry)
        .findFirst()
        .orElse(null);
  }

  public Customer forNsFields(Customer nsCustomer, CustomerBDM customerBDM) {
    //  currency
    helper.nsCurrency(nsCustomer.getCurrency());

    // country
    CustomerAddressbook[] cabs = nonNullCollection(nsCustomer.getAddressbookList());
    var shipAddr =
        Arrays.stream(cabs)
            .filter(CustomerAddressbook::getDefaultShipping)
            .findFirst()
            .orElse(null);
    if (nonNull(shipAddr) && nonNull(shipAddr.getAddressbookAddress())) {
      shipAddr
          .getAddressbookAddress()
          .setCountry(helper.nsCountry(customerBDM.getShipTo().getCountry()));
    }

    var billAddr =
        Arrays.stream(cabs).filter(CustomerAddressbook::getDefaultBilling).findFirst().orElse(null);
    if (nonNull(billAddr) && nonNull(billAddr.getAddressbookAddress())) {
      billAddr
          .getAddressbookAddress()
          .setCountry(helper.nsCountry(customerBDM.getBillTo().getCountry()));
    }

    // TODO: handle other lookups
    return nsCustomer;
  }

  private CustomerAddressbook[] nonNullCollection(CustomerAddressbookList caList) {
    if (isNull(caList)) return EMPTY_AB_ARRAY;
    return Objects.requireNonNullElse(caList.getAddressbook(), EMPTY_AB_ARRAY);
  }
}
