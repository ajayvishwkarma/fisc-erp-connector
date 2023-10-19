Feature: Perform Contact operations on Fisc Erp Connector

  Background:
    * url hostName
    * def token = bambooToken

  Scenario: Get Contact by ID, unauthorised request scenario
    Given path '/api/v1/contact/1489212143'
    When method get
    Then status 401

  Scenario: Get Contact by ID, success scenario
    Given path '/api/v1/contact/1489212148'
    And header Authorization = token
    When method get
    Then status 200
    * assert response.internalId == '300000008424529'
    * assert response.email == 'randomChar@inttest.com'
    * assert response.firstName == 'Farhan'
    * assert response.company.internalId == 'randomChar-12344'

  Scenario: Non existing Contact endpoint scenario
    Given path '/api/v1/contacts'
    And header Authorization = token
    When method get
    Then status 404

  Scenario: Create Contact success scenario
    Given path '/api/v1/contact'
    And header Authorization = token
    And request read('contactRequests/contact-post-request.json')
    When method post
    Then status 200
    * assert response.internalId == '300000008471815'
    * assert response.email == 'ab1b6918-2@inttest.com'
    * assert response.firstName == 'First'
    * assert response.company.internalId == '117501349USD'