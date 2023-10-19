package com.atlassian.fisc.erp.connector.controller;

import com.atlassian.fisc.erp.connector.config.FiscErpConnectorApiResponse;
import com.atlassian.fisc.erp.connector.domain.contact.Contact;
import com.atlassian.fisc.erp.connector.service.ContactService;
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
public class ContactController {
  private final ContactService contactService;

  @GetMapping(path = "/contact/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  @Operation(
      summary = "Fetch Contact details",
      description = "This resource is used to fetch the contact details")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = Contact.class))),
      })
  @FiscErpConnectorApiResponse
  public Contact getContactDetails(
      @Parameter(description = "Contact Identifier") @PathVariable(value = "id") String id) {
    return contactService.getContactDetails(id);
  }

  @PostMapping(path = "/contact")
  @Operation(
      summary = "Create Contact",
      description = "Create a new contact that does not exist in the system")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "OK",
            content = @Content(schema = @Schema(implementation = Contact.class))),
      })
  @FiscErpConnectorApiResponse
  public Contact createContact(@RequestBody Contact fiscSspContact) {
    return contactService.createContact(fiscSspContact);
  }
}
