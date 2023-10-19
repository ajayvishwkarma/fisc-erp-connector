package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.CustomerBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.domain.customer.CustomerAddressbook;
import com.atlassian.fisc.erp.connector.domain.customer.CustomerAddressbookList;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Mapper(uses = NetSuiteMapperFn.class)
public interface CustomerMapper {
  CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

  default CustomerBDM convert(Customer nsCustomer) {
    CustomerBDM customerBDM = map(nsCustomer);
    return updateCustomFields(
        customerBDM, CustomFieldsMapper.INSTANCE.convert(nsCustomer.getCustomFieldList()));
  }

  default Customer convert(CustomerBDM customerBDM) {
    Customer nsCustomer = map(customerBDM);
    return updateCustomFields(nsCustomer, extractNsCustomFields(customerBDM));
  }

  @Mapping(target = "sourceSystem", constant = Constant.SOURCE_SYSTEM_HAMS) //Customer-ROW 1
  @Mapping(target = "entityId", source = "entityId") // explicit for readability //  Customer-ROW 2
  @Mapping(target = "id", source = "externalId") //Customer-ROW 3
  @Mapping(target = "customerName", source = "companyName") //Customer-ROW 4
  @Mapping(target = "startDate", source = "startDate", dateFormat = Constant.DATE_TIME_FORMAT) //Customer-ROW 5
  @Mapping(target = "person", source = "isPerson")// Customer-ROW 6
  @Mapping(target = "subsidiary", constant = Constant.ATLASSIAN_AU_BU_USD_BUSINESS_UNIT_NAME)//Customer-ROW 7
  @Mapping(target = "currency", source = "currency", qualifiedBy = NetSuiteMapping.class)// Customer-ROW ??
  @Mapping(target = "vatNumber", source = "vatRegNumber")
  @Mapping(target = "customerStatus", source = "entityStatus", qualifiedBy = NetSuiteMapping.class)//TODO Revisit Customer-ROW ??
  @Mapping(
      target = "customerActive",
      source = "isInactive",
      qualifiedByName = "invertBoolean") // negate boolean //Customer-ROW 38
  @Mapping(target = "terms", source = "terms", qualifiedBy = NetSuiteMapping.class)//Customer-ROW 39

  @Mapping(target = "billTo", source = "addressbookList", qualifiedByName = "billTo")
  @Mapping(target = "shipTo", source = "addressbookList", qualifiedByName = "shipTo")
  CustomerBDM map(Customer nsCustomer);

  @InheritInverseConfiguration
  @Mapping(target = "addressbookList", source = ".")
  @Mapping(target = "internalId", source = "customerAccountNumber")
  Customer map(CustomerBDM customerBDM);

  @Mapping(target = "commerceCustomer", source = "custentity_iscommerce")//Customer-ROW 26
  @Mapping(target = "gstNumber", source = "custentity_gst_number")//Customer-ROW 27
  @Mapping(target = "customerCategory", source = "custentity_ccp_customer_category")//Customer-ROW 29
  @Mapping(target = "hamsId", source = "custentity_hams_id") //Customer-ROW 30
  @Mapping(target = "billingContactName", source = "custentity_billing_contact_name")//Customer-ROW 32
  @Mapping(target = "customerType", source = "custbody_atl_cust_type")//Customer-ROW 40
  CustomerBDM updateCustomFields(@MappingTarget CustomerBDM customerBdm, CustomFields customFields);

  @InheritInverseConfiguration
  @Mapping(source = "customerAccountId", target = "customer_account_id")// Customer-ROW 40
  @Mapping(source = "customerErpId", target = "customer_erp_id")//Customer-ROW 42
  CustomFields extractNsCustomFields(CustomerBDM customer);

  @Mapping(target = "customFieldList", source = "customFields", qualifiedBy = NetSuiteMapping.class)
  Customer updateCustomFields(@MappingTarget Customer nsCustomer, CustomFields customFields);

  // Extract billTo and shipTo
  default CustomerAddressbookList mapToNsAddress(CustomerBDM customerBDM) {
    List<CustomerAddressbook> customerAddressbookList = new ArrayList<>();
    if (null != customerBDM.getShipTo()) {
      customerAddressbookList.add(mapToCustomerAddressBook(customerBDM.getShipTo(), true));
    }
    if (null != customerBDM.getBillTo()) {
      customerAddressbookList.add(mapToCustomerAddressBook(customerBDM.getBillTo(), false));
    }
    if (isEmpty(customerAddressbookList)) return null;

    CustomerAddressbookList caList = new CustomerAddressbookList();
    caList.setAddressbook(
        customerAddressbookList.toArray(new CustomerAddressbook[customerAddressbookList.size()]));
    return caList;
  }

  private CustomerAddressbook mapToCustomerAddressBook(
      AddressBDM addressBDM, boolean defaultShipping) {
    var nsAddressBook = new CustomerAddressbook();
    nsAddressBook.setAddressbookAddress(AddressMapper.INSTANCE.map(addressBDM));
    nsAddressBook.setDefaultShipping(defaultShipping);
    nsAddressBook.setDefaultBilling(!defaultShipping);
    return nsAddressBook;
  }

  @Named("billTo")
  default AddressBDM billTo(CustomerAddressbookList customerAddressbookList) {
    return mapAddress(customerAddressbookList, true);
  }

  @Named("shipTo")
  default AddressBDM shipTo(CustomerAddressbookList customerAddressbookList) {
    return mapAddress(customerAddressbookList, false);
  }

  default AddressBDM mapAddress(CustomerAddressbookList customerAddressbookList, boolean shipTo) {
    if (isNull(customerAddressbookList) || isEmpty(customerAddressbookList.getAddressbook()))
      return null;
    CustomerAddressbook[] customerAddressBooks = customerAddressbookList.getAddressbook();

    CustomerAddressbook nsAddressbook =
        Arrays.stream(customerAddressBooks)
            .filter(v -> shipTo && v.getDefaultShipping() || !shipTo && v.getDefaultBilling())
            .findFirst()
            .orElse(null);

    return mapToAddressBdm(nsAddressbook);
  }

  private AddressBDM mapToAddressBdm(CustomerAddressbook cab) {
    if (isNull(cab) || isNull(cab.getAddressbookAddress())) return null;
    var addressBDM = AddressMapper.INSTANCE.map(cab.getAddressbookAddress());
    addressBDM.setAddressee(cab.getLabel()); // TODO validate
    return addressBDM;
  }

  @Named("invertBoolean")
  default Boolean invertBoolean(Boolean bool) {
    return null != bool ? !bool : null;
  }
}
