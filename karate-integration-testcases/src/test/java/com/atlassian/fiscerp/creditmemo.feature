Feature: Perform CreditMemo operations on Fisc Erp Connector

  Background:
    * url hostName
    * def token = bambooToken
    * def sampleData = call read('creditMemoRequests/creditMemo.js');
    * def customFieldObject1 = {"scriptId":"custcol_product_feature_usage","value":"1"}
    * def customFieldObject2 = {"scriptId":"custcol_marketplace_vendor","value":{"internalId":"12345678"}}
    * def customFieldObject3 = {"scriptId":"custbodyatl_credit_memo_source","value":{"internalId":"SLA"}}

  Scenario: Get CreditMemo by ID, unauthorised request scenario
    Given path '/api/v1/creditnote/TEST-P7DBEO2MHMGU'
    When method get
    Then status 401

  Scenario: Get CreditMemo by ID, success scenario
    Given path '/api/v1/creditnote/TEST-P7DBEO2MHMGU'
    And header Authorization = token
    When method get
    Then status 200
    * assert response.tranId == 'TEST-P7DBEO2MHMGU'
    * assert response.source == 'HAMS Integration'
    * assert response.entity.internalId == 'TEST-54d3e84f-944d-4152-8797-cfbb399d3362'
    * assert response.currency.internalId == 'USD'
    * assert response.status == 'Fully Applied'
    * match response.itemList.item[0].customFieldList.customField contains customFieldObject2;
    * match response.customFieldList.customField contains customFieldObject3;

  Scenario: Non existing creditnote endpoint scenario
    Given path '/api/v1/creditnotes'
    And header Authorization = token
    When method get
    Then status 404

  Scenario: Create creditnote, success scenario
    Given path '/api/v1/creditnote'
    And header Authorization = token
    And request sampleData
    When method post
    Then status 200
    * assert response.tranId == sampleData.tranId
    * assert response.source == 'HAMS Integration'
    * assert response.entity.internalId == 'TEST-54d3e84f-944d-4152-8797-cfbb399d3362'
    * assert response.currency.internalId == 'USD'
    * match response.itemList.item[0].customFieldList.customField contains customFieldObject1;
    * match response.itemList.item[0].customFieldList.customField contains customFieldObject2;
    * match response.customFieldList.customField contains customFieldObject3;