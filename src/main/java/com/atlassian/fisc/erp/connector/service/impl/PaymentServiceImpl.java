package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.finsys.micros.logging.JsonLogV2;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.lookups.PaymentLookup;
import com.atlassian.fisc.erp.connector.mappers.PaymentMapper;
import com.atlassian.fisc.erp.connector.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
  //  @Autowired through constructor
  private final ErpServiceClient erpServiceClient;
  private final PaymentLookup lookup;

  @Override
  @JsonLogV2(logOutputAdditionalProperties = "tranId,externalId", suppressErrorLogToWarning = false)
  public CustomerPayment getPaymentDetails(String tranId) {
    var paymentBdmResponse = erpServiceClient.getPayment(tranId);
    return lookup.forNsFields(
        PaymentMapper.INSTANCE.convert(paymentBdmResponse), paymentBdmResponse);
  }

  @Override
  @JsonLogV2(
      logId = "tranId",
      logOutputAdditionalProperties = "tranId,externalId",
      suppressErrorLogToWarning = false)
  public CustomerPayment createPayment(CustomerPayment nsPayment) {
    PaymentBDM paymentBDM =
        lookup.forBdmFields(PaymentMapper.INSTANCE.convert(nsPayment), nsPayment);

    var paymentBdmResponse = erpServiceClient.createPayment(paymentBDM);

    return lookup.forNsFields(
        PaymentMapper.INSTANCE.convert(paymentBdmResponse), paymentBdmResponse);
  }
}
