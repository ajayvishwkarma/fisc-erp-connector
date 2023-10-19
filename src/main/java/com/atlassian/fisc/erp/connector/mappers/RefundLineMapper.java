package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.bdm.RefundApplyLineBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefundApply;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RefundLineMapper {
  RefundLineMapper INSTANCE = Mappers.getMapper(RefundLineMapper.class);

  @Mapping(target = "applyAmount", source = "total")  //  Refund-ROW 14
  @Mapping(
      source = "applyDate",
      target = "applyDate",
      dateFormat = Constant.DATE_TIME_FORMAT) //  Refund-ROW 15
  @Mapping(target = "creditNoteId", source = "doc") //  Refund-ROW 16
  @Mapping(target = "creditNoteNumber", source = "refNum") // TODO Refund-ROW ??
  RefundApplyLineBDM map(CustomerRefundApply nsRefundApply);

  @InheritInverseConfiguration
  CustomerRefundApply map(RefundApplyLineBDM applyLineBDM);
}
