package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.config.FiscErpConnectorApiResponse;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.service.CreditMemoService;
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
public class CreditMemoController {

  private final CreditMemoService creditMemoService;

  @GetMapping(path = "/creditnote/{tranId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Fetch CreditMemo details",
      description = "This resource is used to fetch the CreditMemo details")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = CreditMemo.class))),
      })
  @FiscErpConnectorApiResponse
  public CreditMemo getCreditMemoDetails(
      @Parameter(description = "CreditMemo Identifier") @PathVariable(value = "tranId")
          String tranId) {
    return creditMemoService.getCreditMemoDetails(tranId);
  }

  @PostMapping(path = "/creditnote")
  @Operation(
      summary = "Create CreditMemo",
      description = "Create a new CreditMemo that does not exist in the system")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = CreditMemo.class))),
      })
  @FiscErpConnectorApiResponse
  public CreditMemo createCreditMemo(@RequestBody CreditMemo creditNote) {
    return creditMemoService.createCreditMemo(creditNote);
  }

  @PostMapping(path = "/applyCreditnote")
  @Operation(
      summary = "Apply CreditMemo",
      description = "Apply CreditMemo that does not exist in the system")
  @ApiResponses(
      value = {
          @ApiResponse(
              responseCode = "200",
              description = "OK",
              content = @Content(schema = @Schema(implementation = CreditMemo.class))),
      })
  @FiscErpConnectorApiResponse
  public CreditMemo applyCreditMemo(@RequestBody CreditMemo creditNote) {
    return creditMemoService.applyCreditMemo(creditNote);
  }
}
