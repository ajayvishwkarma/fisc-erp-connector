package com.atlassian.fisc.erp.connector.service;

import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;

public interface CreditMemoService {
  CreditMemo getCreditMemoDetails(String transactionNumber);

  CreditMemo createCreditMemo(CreditMemo creditMemo);
  CreditMemo applyCreditMemo(CreditMemo creditMemo);
}
