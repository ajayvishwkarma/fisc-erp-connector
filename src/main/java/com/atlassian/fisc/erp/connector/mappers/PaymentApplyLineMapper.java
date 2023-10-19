package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.PaymentApplyLineBDM;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPaymentApply;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PaymentApplyLineMapper {
  PaymentApplyLineMapper INSTANCE = Mappers.getMapper(PaymentApplyLineMapper.class);

  /*
   Automatically mapped fields
   - amount                     Pay-ROW 15
  */
  @Mapping(target = "invoiceId", source = "doc") //Pay-ROW 14
  PaymentApplyLineBDM map(CustomerPaymentApply nsPaymentApply);

  @InheritInverseConfiguration
  CustomerPaymentApply map(PaymentApplyLineBDM paymentApplyLine);
}
