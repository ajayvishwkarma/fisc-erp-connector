package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.enums.CreditMemoSource;
import com.atlassian.finance.enums.TransactionSourceSystem;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EnumMapperFn {
  EnumMapperFn INSTANCE = Mappers.getMapper(EnumMapperFn.class);

  default String fromCreditMemoSource(CreditMemoSource value) {
    return value != null ? value.getName() : CreditMemoSource.SLA.getName();
  }

  default CreditMemoSource toCreditMemoSource(String value) {
    return value != null && value.equalsIgnoreCase("1") ? CreditMemoSource.SLA : null;
  }

  default String fromTransactionSourceSystem(TransactionSourceSystem value) {
    return value != null ? value.getName() : TransactionSourceSystem.HAMS_INTEGRATION.getName();
  }

  default TransactionSourceSystem toTransactionSourceSystem(String value) {
    return StringUtils.isNotBlank(value)
        ? TransactionSourceSystem.valueOfLabel(value)
        : TransactionSourceSystem.HAMS_INTEGRATION;
  }
}
