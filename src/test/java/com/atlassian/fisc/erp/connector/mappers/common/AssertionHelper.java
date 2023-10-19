package com.atlassian.fisc.erp.connector.mappers.common;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorConfig;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.util.TwoWayAsserter;

public class AssertionHelper {
  private final FiscErpConnectorConfig configProperties;

  public AssertionHelper(FiscErpConnectorConfig configProperties) {
    this.configProperties = configProperties;
  }

  public void validateAddress(AddressBDM addressBDM, Address nsAddress, boolean expectedFirst) {
    validateAddress(addressBDM, nsAddress, new TwoWayAsserter(expectedFirst));
  }

  public void validateAddress(AddressBDM addressBDM, Address nsAddress, TwoWayAsserter ast) {
    ast.assertEquals(addressBDM.getAddressee(), nsAddress.getAddressee());
    ast.assertEquals(addressBDM.getLine1(), nsAddress.getAddr1());
    ast.assertEquals(addressBDM.getLine2(), nsAddress.getAddr2());
    ast.assertEquals(addressBDM.getLine3(), nsAddress.getAddr3());
    ast.assertEquals(addressBDM.getCity(), nsAddress.getCity());
    ast.assertEquals(addressBDM.getState(), nsAddress.getState());
    ast.assertEquals(addressBDM.getPostalCode(), nsAddress.getZip());
    ast.assertEquals(addressBDM.getPhone(), nsAddress.getAddrPhone());
    ast.assertEquals(
        configProperties.getCountry().get(addressBDM.getCountry()),
        nsAddress.getCountry().getValue());
  }
}
