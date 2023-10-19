package com.atlassian.fisc.erp.connector.service;

import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;

public interface PaymentService {

  CustomerPayment getPaymentDetails(String tranId);

  CustomerPayment createPayment(CustomerPayment payment);
}
