Feature: Perform Invoice operations on Fisc Erp Connector

  Background:
    * url hostName
    * def token = bambooToken
    * def sampleData = call read('invoice.js');

  Scenario: Get Invoice by ID, unauthorised request scenario
    Given path '/api/v1/invoice/TEST-SQFBBN7TCG5V'
    When method get
    Then status 401

  Scenario: Get Invoice by ID
    Given path '/api/v1/invoice/TEST-randomChar457'
    And header Authorization = token
    When method get
    Then status 200
    * assert response.internalId == '300000008423831'
    * assert response.tranId == 'TEST-randomChar457'
    * assert response.status == 'Open'

  Scenario: Create Invoice, success scenario
    Given path '/api/v1/invoice'
    And header Authorization = token
    And request sampleData
    When method post
    Then status 200
    * assert response.tranId == sampleData.tranId
    * assert response.entity.internalId == 'TEST-Customer-2VH5HD9GzYdEZiA'
    * assert response.currency.internalId == '3'

  Scenario: Fetch Standard Receipts from invoice Number
    Given path '/api/v1/invoice/payments/TEST-NWmH_cjPEDykRCT'
    And header Authorization = token
    When method get
    Then status 200
    * assert response[0].transactionNumber == 'TEST-HdVwA9EGq6kamgw'
