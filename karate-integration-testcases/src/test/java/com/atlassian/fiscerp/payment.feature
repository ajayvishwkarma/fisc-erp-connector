Feature: Perform Payment operations on Fisc Erp Connector

  Background:
    * url hostName
    * def token = bambooToken
    * def sampleData = call read('payment.js');

  Scenario: Get Payment by ID, unauthorised request scenario
    Given path '/api/v1/payment/TEST-NHgruRf6GiXWCuU'
    When method get
    Then status 401

  Scenario: Get Payment by ID, success scenario
    Given path '/api/v1/payment/TEST-local-005'
    And header Authorization = token
    When method get
    Then status 200
    * assert response.customer.internalId == '14855'
    * assert response.tranId == 'TEST-local-005'
    * assert response.paymentMethod.internalId == '7'

  Scenario: Non existing Payment endpoint scenario
    Given path '/api/v1/payments'
    And header Authorization = token
    When method get
    Then status 404

  Scenario: Create payment, success scenario
    Given path '/api/v1/payment'
    And header Authorization = token
    And request sampleData
    When method post
    Then status 200
    * assert response.tranId == sampleData.tranId
    * assert response.paymentMethod.internalId == sampleData.paymentMethod.internalId
    * assert response.currency.internalId == '3'