package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.finance.bdm.InvoiceBDM;
import com.atlassian.finance.bdm.PaymentBDM;
import com.atlassian.fisc.erp.connector.config.FiscErpConnectorApiResponse;
import com.atlassian.fisc.erp.connector.domain.invoice.Invoice;
import com.atlassian.fisc.erp.connector.domain.payment.CustomerPayment;
import com.atlassian.fisc.erp.connector.service.InvoiceService;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/invoice")
@RequiredArgsConstructor
@Slf4j
public class InvoiceController {
  private final InvoiceService invoiceService;

  @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Fetch Invoice details",
      description = "This resource is used to fetch the invoice details")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = InvoiceBDM.class))),
      })
  @FiscErpConnectorApiResponse
  public Invoice getInvoiceDetails(
      @Parameter(description = "Invoice Identifier") @PathVariable(value = "id") String id)
      throws JsonProcessingException {
    System.out.println("in get Invoice");
    return invoiceService.getInvoice(id);
  }

  @PostMapping
  @Operation(
      summary = "Fetch Invoice details",
      description = "This resource is used to create the invoice")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = Invoice.class))),
      })
  @FiscErpConnectorApiResponse
  public Invoice createInvoice(@RequestBody Invoice invoice) throws JsonProcessingException {
    return invoiceService.createInvoice(invoice);
  }

  @GetMapping(value = "/payments/{invoiceNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Fetch invoice details",
      description = "Fetch details of an existing invoice")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content =
                @Content(
                    array = @ArraySchema(schema = @Schema(implementation = CustomerPayment.class)))),
      })
  @FiscErpConnectorApiResponse
  public List<CustomerPayment> getStandardReceiptsFromInvoice(
      @Parameter(description = "Invoice identifier") @PathVariable(value = "invoiceNumber")
          String invoiceNumber) {
    log.info("Received a request for invoice transactionNumber: {}", invoiceNumber);
    return invoiceService.getPaymentFromInvoice(invoiceNumber);
  }

}
