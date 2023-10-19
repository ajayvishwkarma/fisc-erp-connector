package com.atlassian.fisc.erp.connector.mappers.common;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.Country;

public class TestGenHelper {
  public static AddressBDM getDummyAddress(boolean billTo) {
    String prefix = billTo ? "billTo " : "shipTo  ";
    AddressBDM addressBDM = new AddressBDM();
//    addressBDM.setUseAddressee(Boolean.TRUE);
    addressBDM.setAddressee(prefix + "Henry Rutherford");
    addressBDM.setLine1("41 Dance Road");
    addressBDM.setLine2(prefix + "Disney world area");
    addressBDM.setLine3("Near park");
    addressBDM.setCity(prefix + "San Ramon");
    addressBDM.setState("CA");
    addressBDM.setCountry("US");
    addressBDM.setPostalCode("40901-5401");
    addressBDM.setPhone("831-323-8424");
    addressBDM.setAddressErpId("121-022-0405");
    return addressBDM;
  }

  public static Address getDummyNsAddress(boolean billTo) {
    String prefix = billTo ? "billTo " : "shipTo ";
    Address address = new Address();
    address.setInternalId("InternalId");
    address.setAttention(prefix + "David Diamond");
    address.setAddressee(prefix + "Chairperson");
    address.setAddrPhone("441-527-3212");
    address.setAddr1(prefix + "41 Dore Street");
    address.setAddr2("Park plaza");
    address.setAddr3("Century Quarters");
    address.setCity("San Diego");
    address.setState("CA");
    address.setCountry(Country._unitedStates);
    address.setZip("98221-4821");
    address.setAddrText(prefix + "AddrText");
    address.setOverride(Boolean.TRUE);
    return address;
  }
}
