package com.atlassian.fisc.erp.connector.client;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
@Slf4j
class ClientHelperTest {

    @InjectMocks
    private ClientHelper clientHelper;

    @Mock
    private RestTemplate restTemplate;

    private static final String URI = "http://127.0.0.1:8080/api/v1";

    @Test
    @SneakyThrows
    @DisplayName("Should successfully execute rest calls with provided params")
    void executeTest() {
        String expectedResponse = "Hello World!";
        ResponseEntity<String> expectedResponseEntity =
                new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        Mockito.lenient()
                .when(
                        restTemplate.exchange(
                                Mockito.any(URI.class), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenReturn(expectedResponseEntity);
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        ResponseEntity<String> actualResponseEntity = clientHelper.execute(uri, entity, HttpMethod.GET);
        Assertions.assertEquals(expectedResponseEntity, actualResponseEntity);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should successfully handle rest call http status code exception")
    void executeWithHttpExceptionTest() {
        Mockito.when(
                        restTemplate.exchange(
                                Mockito.any(URI.class), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        HttpStatusCodeException httpStatusCodeException =
                Assertions.assertThrows(
                        HttpStatusCodeException.class,
                        () -> {
                            clientHelper.execute(uri, entity, HttpMethod.GET);
                        });
        Assertions.assertEquals(401, httpStatusCodeException.getStatusCode().value());
    }

    /**
     * Method under test: {@link ClientHelper#execute(URI, HttpEntity, HttpMethod, Class)}
     */

    @Test
    @SneakyThrows
    @DisplayName("Should successfully execute rest calls with provided response type class for success scenario")
    void executeTestWithResponseTypeSuccess() {
        Mockito.when(
                        restTemplate.exchange(
                                Mockito.any(URI.class), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenReturn(ResponseEntity.ok("Hello world"));
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        Assertions.assertEquals(HttpStatus.OK, clientHelper.execute(uri, entity, HttpMethod.GET, String.class).getStatusCode());
        Assertions.assertEquals("Hello world", clientHelper.execute(uri, entity, HttpMethod.GET, String.class).getBody());
    }


    /**
     * Method under test: {@link ClientHelper#execute(URI, HttpEntity, HttpMethod, Class)}
     */

    @Test
    @SneakyThrows
    @DisplayName("Should successfully execute rest calls with provided response type class for client error")
    void executeTestWithResponseTypeError() {
        Mockito.when(
                        restTemplate.exchange(
                                Mockito.any(URI.class), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        HttpStatusCodeException httpStatusCodeException =
                Assertions.assertThrows(
                        HttpStatusCodeException.class,
                        () -> {
                            clientHelper.execute(uri, entity, HttpMethod.GET, String.class);
                        });
        Assertions.assertEquals(401, httpStatusCodeException.getStatusCode().value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should successfully handle rest call generic exception")
    void executeWithExceptionTest() {
        Mockito.when(
                        restTemplate.exchange(
                                Mockito.any(URI.class), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenThrow(new ResourceAccessException("Exception Occurred"));
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        Exception exception =
                Assertions.assertThrows(
                        Exception.class,
                        () -> {
                            clientHelper.execute(uri, entity, HttpMethod.GET);
                        });
        Assertions.assertEquals("Exception Occurred", exception.getMessage());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should successfully execute rest calls with provided request body")
    void executeStringUrlTest() {
        String expectedResponse = "Hello World!";
        ResponseEntity<String> expectedResponseEntity =
                new ResponseEntity<>(expectedResponse, HttpStatus.OK);
        Mockito.lenient()
                .when(restTemplate.exchange(anyString(), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenReturn(expectedResponseEntity);
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        ResponseEntity<String> actualResponseEntity =
                clientHelper.execute(uri.toString(), entity, HttpMethod.GET);
        Assertions.assertEquals(expectedResponseEntity, actualResponseEntity);
    }

    @Test
    @SneakyThrows
    @DisplayName("Should successfully handle rest call http status code exception")
    void executeStringUrlWithHttpExceptionTest() {
        Mockito.when(restTemplate.exchange(anyString(), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        HttpStatusCodeException httpStatusCodeException =
                Assertions.assertThrows(
                        HttpStatusCodeException.class,
                        () -> {
                            clientHelper.execute(uri.toString(), entity, HttpMethod.GET);
                        });
        Assertions.assertEquals(401, httpStatusCodeException.getStatusCode().value());
    }

    @Test
    @SneakyThrows
    @DisplayName("Should successfully handle rest call generic exception")
    void executeStringUrlWithExceptionTest() {
        Mockito.when(restTemplate.exchange(anyString(), Mockito.any(), Mockito.any(), eq(String.class)))
                .thenThrow(new ResourceAccessException("Exception Occurred"));
        URI uri = new URI(URI);
        HttpEntity<?> entity = new HttpEntity<>(null, null);
        Exception exception =
                Assertions.assertThrows(
                        Exception.class,
                        () -> {
                            clientHelper.execute(uri.toString(), entity, HttpMethod.GET);
                        });
        Assertions.assertEquals("Exception Occurred", exception.getMessage());
    }
}
