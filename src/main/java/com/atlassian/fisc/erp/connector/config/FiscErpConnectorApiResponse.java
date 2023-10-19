package com.atlassian.fisc.erp.connector.config;

import com.atlassian.finsys.micros.error.dto.ErrorJson;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ApiResponses(
    value = {
      @ApiResponse(
          responseCode = "400",
          description = "Bad request",
          content = @Content(schema = @Schema(implementation = ErrorJson.class))),
      @ApiResponse(
          responseCode = "401",
          description = "Unauthorized",
          content = @Content(schema = @Schema(implementation = ErrorJson.class))),
      @ApiResponse(
          responseCode = "403",
          description = "Forbidden",
          content = @Content(schema = @Schema(implementation = ErrorJson.class))),
      @ApiResponse(
          responseCode = "404",
          description = "Not Found",
          content = @Content(schema = @Schema(implementation = ErrorJson.class))),
      @ApiResponse(
          responseCode = "405",
          description = "Method not Allowed",
          content = @Content(schema = @Schema(implementation = ErrorJson.class))),
      @ApiResponse(
          responseCode = "500",
          description = "Internal Server Error",
          content = @Content(schema = @Schema(implementation = ErrorJson.class))),
      @ApiResponse(
          responseCode = "503",
          description = "Service Unavailable",
          content = @Content(schema = @Schema(implementation = ErrorJson.class)))
    })
public @interface FiscErpConnectorApiResponse {}
