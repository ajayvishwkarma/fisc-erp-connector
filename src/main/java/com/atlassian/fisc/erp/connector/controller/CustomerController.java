package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.config.FiscErpConnectorApiResponse;
import com.atlassian.fisc.erp.connector.domain.customer.Customer;
import com.atlassian.fisc.erp.connector.service.CustomerService;
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
public class CustomerController {

  private final CustomerService customerService;

  // Customer End Points

  @GetMapping(value = "/customer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Fetch customer details",
      description = "Fetch details of an existing customer")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = Customer.class))),
      })
  @FiscErpConnectorApiResponse
  public Customer getCustomer(
      @Parameter(description = "customer identifier") @PathVariable(value = "id") String id) {
    return customerService.getCustomer(id);
  }

  @PostMapping(path = "/customer")
  @Operation(
      summary = "Create customer",
      description = "Create a new customer that does not exist in the system")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = Customer.class))),
      })
  @FiscErpConnectorApiResponse
  public Customer createCustomer(@RequestBody Customer customer) {
    return customerService.createCustomer(customer);
  }
}
