package com.atlassian.fisc.erp.connector.service;

import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefund;

public interface RefundService {

  CustomerRefund getRefundDetails(String id);

  CustomerRefund createRefund(CustomerRefund refund);
}
