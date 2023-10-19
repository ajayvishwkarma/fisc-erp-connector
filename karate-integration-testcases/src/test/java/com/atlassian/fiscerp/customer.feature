Feature: Perform Customer operations on Fisc Erp Connector

  Background:
    * url hostName
    * def token = bambooToken

  Scenario: Get Customer by ID, unauthorised request scenario
    Given path '/api/v1/customer/117501349USD'
    When method get
    Then status 401

  Scenario: Get Customer by ID, success scenario
    Given path '/api/v1/customer/117501349USD'
    And header Authorization = token
    When method get
    Then status 200
    * assert response.externalId == '117501349USD'
    * assert response.internalId == '12027'
    * assert response.currency.internalId == '1'

  Scenario: Non existing Customer endpoint scenario
    Given path '/api/v1/customers'
    And header Authorization = token
    When method get
    Then status 404

  Scenario: Create Customer success scenario
    Given path '/api/v1/customer'
    And header Authorization = token
    And request read('customerRequests/customer-post-request.json')
    When method post
    Then status 200
    * assert response.isPerson == false
    * assert response.externalId == '117501349USD'
    * assert response.internalId == '12027'
    * assert response.terms.internalId == '45'
    * assert response.currency.internalId == '1'
    * assert response.subsidiary.name == 'Atlassian AU BU USD'