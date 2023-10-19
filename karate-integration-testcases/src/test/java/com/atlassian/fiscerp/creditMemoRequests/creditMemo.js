function getCreditMemoPostRequest() {
    var SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    var salt = new java.lang.StringBuilder();
    var rnd = new java.util.Random();
    while (salt.length() < 12) { // length of the random string.
        var index = rnd.nextFloat() * SALTCHARS.length();
        salt.append(SALTCHARS.charAt(index));
    }

    var randomChar = salt.toString();

    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd + 'T04:04:05+05:30';
    return {
        "source": "HAMS Integration",
        "tranId": 'TEST-' + randomChar,
        "tranDate": today,
        "currency": {
            "name": null,
            "internalId": "3",
            "externalId": null,
            "type": null
        },

        "entity": {
            "name": null,
            "internalId": "TEST-54d3e84f-944d-4152-8797-cfbb399d3362",
            "externalId": null,
            "type": null
        },
        "createdFrom": {
            "name": null,
            "internalId": "300000004076785",
            "externalId": "300000004076785",
            "type": null
        },
        "memo": "memo",
        "exchangeRate": 10.0,
        "billingAddress": {
            "internalId": null,
            "country": {
                "value": "_unitedStates"
            }
        },
        "itemList": {
            "item": [
                {
                    "item": {
                        "internalId": "14899"
                    },
                    "_class": {
                        "internalId": "87"
                    },
                    "quantity": 1,
                    "description": "Cloud (annual) Confluence",
                    "amount": 200,
                    "taxCode": {
                        "name": null,
                        "internalId": "IN_TAX_5",
                        "externalId": null,
                        "type": null
                    },
                    "taxRate1": 10,
                    "taxRate2": null,
                    "tax1Amt": 44,
                    "revRecStartDate": "2023-01-02T04:04:05Z",
                    "revRecEndDate": "2023-02-05T04:04:05+00:00",
                    "taxAmount": null,
                    "customFieldList": {
                        "customField": [
                            {
                                "internalId": "1",
                                "scriptId": "custcol_sen",
                                "value": "123"
                            },
                            {
                                "internalId": "1",
                                "scriptId": "custcol_new_list_price",
                                "value": "150"
                            },
                            {
                                "internalId": "1",
                                "scriptId": "custcol_sale_action",
                                "value": "saleAction"
                            }, {
                                "internalId": "1",
                                "scriptId": "custcol_cm_inv_line",
                                "value": "Cloud (annual):CFLU"
                            },
                            {
                                "internalId": null,
                                "scriptId": "custcol_marketplace_vendor",
                                "value": "12345678"
                            },
                            {
                                "internalId": "1",
                                "scriptId": "custcol_hosting_type",
                                "value": "CLOUD"
                            }
                        ]
                    }
                }
            ],
            "replaceAll": false
        },
        "customFieldList": {
            "customField": [
                {
                    "internalId": "1",
                    "scriptId": "custbody_refund_code",
                    "value": "refundCode"
                },
                {
                    "scriptId": "custbody_atl_cust_type",
                    "value": "Aggregator"
                },
                {
                    "internalId": null,
                    "scriptId": "custbody_refund_reason_desc",
                    "value": "Return"
                },
                {
                    "scriptId": "custbody_end_customer",
                    "value": "TEST-54d3e84f-944d-4152-8797-cfbb399d3362"
                },
                {
                    "internalId": "1",
                    "scriptId": "custbodyatl_credit_memo_source",
                    "value": "1"
                },
                {
                    "internalId": "2",
                    "scriptId": "custbody_payment_method",
                    "value": "PAIDCC"
                },
                {
                    "internalId": null,
                    "scriptId": "custbody_sla_credit_check",
                    "value": "Y"
                },
                {
                    "internalId": null,
                    "scriptId": "custbody_ccp_on_account_credit",
                    "value": "false"
                }
            ]
        },
        "internalId": null,
        "externalId": null
    };
}