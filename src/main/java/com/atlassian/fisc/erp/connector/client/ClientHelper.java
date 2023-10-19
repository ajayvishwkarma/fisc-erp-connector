package com.atlassian.fisc.erp.connector.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;

@Service
public class ClientHelper {

  @Resource(name = "microsRestTemplate")
  private RestTemplate restTemplate;

  public ResponseEntity<String> execute(String url, HttpEntity<?> entity, HttpMethod method) {
    return restTemplate.exchange(url, method, entity, String.class);
  }

  public ResponseEntity<String> execute(URI url, HttpEntity<?> entity, HttpMethod method) {
    return restTemplate.exchange(url, method, entity, String.class);
  }

  public <T> ResponseEntity<T> execute(
      URI url, HttpEntity<?> entity, HttpMethod method, Class<T> type) {
    return restTemplate.exchange(url, method, entity, type);
  }
}
