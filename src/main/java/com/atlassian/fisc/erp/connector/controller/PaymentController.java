package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.config.FiscErpConnectorApiResponse;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.service.PaymentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class PaymentController {

  // begin: Constructor injection dependencies
  private final PaymentService paymentService;

  @GetMapping(path = "/payment/{tranId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Fetch Payment details",
      description = "This resource is used to fetch the payment details")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = CustomerPayment.class))),
      })
  @FiscErpConnectorApiResponse
  public CustomerPayment getPaymentDetails(
      @Parameter(description = "Payment Identifier") @PathVariable(value = "tranId")
          String tranId) {
    return paymentService.getPaymentDetails(tranId);
  }

  @PostMapping(path = "/payment")
  @Operation(
      summary = "Create Payment",
      description = "Create a new payment that does not exist in the system")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = CustomerPayment.class))),
      })
  @FiscErpConnectorApiResponse
  public CustomerPayment createPayment(@RequestBody CustomerPayment nsPayment) {
    return paymentService.createPayment(nsPayment);
  }
}
