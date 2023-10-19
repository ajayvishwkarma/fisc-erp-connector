Feature: Perform Refund operations on Fisc Erp Connector

  Background:
    * url hostName
    * def token = bambooToken
    * def creditNoteSampleData = call read('creditMemoRequests/creditMemo.js');
    * def sampleData = call read('refundRequests/refund.js');

  Scenario: Create Refund, success scenario
    Given path '/api/v1/creditnote'
    And header Authorization = token
    And request creditNoteSampleData
    When method post
    Then status 200
    * replace sampleData.placeHolder = creditNoteSampleData.tranId
    * json refundRequest = sampleData
    Given path '/api/v1/refund'
    And header Authorization = token
    And request refundRequest
    When method post
    Then status 200
    * assert response.paymentMethod.internalId == '7'