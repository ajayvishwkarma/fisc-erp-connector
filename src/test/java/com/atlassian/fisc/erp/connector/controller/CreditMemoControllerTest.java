package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.service.CreditMemoService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreditMemoControllerTest {
  @Mock CreditMemoController creditMemoController;

  @Mock CreditMemoService creditMemoService;

  @BeforeEach
  @SneakyThrows
  void setUp() {
    MockitoAnnotations.openMocks(this);
    creditMemoController = new CreditMemoController(creditMemoService);
  }

  @Nested
  class FiscCreditMemoApi {

    @Test
    @DisplayName("Should successfully return CreditMemo object for provided id")
    void getCreditMemo() {
      CreditMemo creditMemo = new CreditMemo();
      creditMemo.setSource("SampleSource");
      creditMemo.setTranId("Sample TranId");
      when(creditMemoService.getCreditMemoDetails(anyString())).thenReturn(creditMemo);
      CreditMemo getApi = creditMemoController.getCreditMemoDetails("123");
      assertEquals("SampleSource", getApi.getSource());
    }

    @Test
    @DisplayName("Should successfully create CreditMemo object")
    void createCreditMemo() {
      CreditMemo creditMemo = new CreditMemo();
      creditMemo.setSource("SampleSource");
      creditMemo.setTranId("Sample TranId");
      when(creditMemoService.createCreditMemo(any())).thenReturn(creditMemo);
      CreditMemo getApi = creditMemoController.createCreditMemo(creditMemo);
      assertEquals("SampleSource", getApi.getSource());
    }

    @Test
    @DisplayName("Should successfully apply lines on CreditMemo object")
    void applyCreditMemo() {
      CreditMemo creditMemo = new CreditMemo();
      creditMemo.setSource("SampleSource");
      creditMemo.setTranId("Sample TranId");
      when(creditMemoService.applyCreditMemo(any())).thenReturn(creditMemo);
      CreditMemo getApi = creditMemoController.applyCreditMemo(creditMemo);
      assertEquals("SampleSource", getApi.getSource());
    }
  }
}
