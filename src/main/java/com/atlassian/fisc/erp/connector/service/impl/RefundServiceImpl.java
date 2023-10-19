package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finsys.micros.logging.JsonLogV2;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefund;
import com.atlassian.fisc.erp.connector.lookups.RefundLookup;
import com.atlassian.fisc.erp.connector.mappers.RefundMapper;
import com.atlassian.fisc.erp.connector.service.RefundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {
  //  @Autowired through constructor
  private final ErpServiceClient erpServiceClient;
  private final RefundLookup lookup;

  @Override
  @JsonLogV2(
      logOutputAdditionalProperties = "customer.externalId,customer.internalId",
      suppressErrorLogToWarning = false)
  public CustomerRefund getRefundDetails(String id) {
    var refundResponse = erpServiceClient.getRefund(id);
    return lookup.forNsFields(RefundMapper.INSTANCE.convert(refundResponse), refundResponse);
  }

  @Override
  @JsonLogV2(
      logId = "customer.externalId",
      logOutputAdditionalProperties = "customer.externalId,customer.internalId",
      suppressErrorLogToWarning = false)
  public CustomerRefund createRefund(CustomerRefund nsRefund) {
    lookup.preMapNsFields(nsRefund);
    var refundBDM = RefundMapper.INSTANCE.convert(nsRefund);
    var refundBdmResponse = erpServiceClient.createRefund(refundBDM);
    return lookup.forNsFields(RefundMapper.INSTANCE.convert(refundBdmResponse), refundBdmResponse);
  }
}
