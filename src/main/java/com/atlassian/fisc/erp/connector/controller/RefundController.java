package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.config.FiscErpConnectorApiResponse;
import com.atlassian.fisc.erp.connector.domain.refund.CustomerRefund;
import com.atlassian.fisc.erp.connector.service.RefundService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class RefundController {

  // begin: Constructor injection dependencies
  private final RefundService refundService;

  @PostMapping(path = "/refund")
  @Operation(
      summary = "Create Refund",
      description = "Create a new refund that does not exist in the system")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = CustomerRefund.class))),
      })
  @FiscErpConnectorApiResponse
  public CustomerRefund createRefund(@RequestBody CustomerRefund nsRefund) {
    return refundService.createRefund(nsRefund);
  }
}
