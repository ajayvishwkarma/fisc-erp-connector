package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
  AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

  @Mapping(target = "addr1", source = "line1")
  @Mapping(target = "addr2", source = "line2")
  @Mapping(target = "addr3", source = "line3")
  @Mapping(target = "zip", source = "postalCode")
  @Mapping(target = "addrPhone", source = "phone")
  @Mapping(target = "internalId", source = "addressErpId")
  @Mapping(target = "country", ignore = true) // needs lookup
  Address map(AddressBDM addressBDM);

  @InheritInverseConfiguration
  @Mapping(target = "country", ignore = true) // needs lookup
  AddressBDM map(Address nsAddress);
}
