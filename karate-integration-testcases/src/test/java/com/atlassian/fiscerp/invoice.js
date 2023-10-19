function getInvoicePostRequest() {

    var SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    var salt = new java.lang.StringBuilder();
    var rnd = new java.util.Random();
    while (salt.length() < 12) { // length of the random string.
        var index = rnd.nextFloat() * SALTCHARS.length();
        salt.append(SALTCHARS.charAt(index));
    }

    var saltStr = salt.toString();

    var today = new Date();
    var dd = String(today.getDate()).padStart(2, '0');
    var mm = String(today.getMonth() + 1).padStart(2, '0'); //January is 0!
    var yyyy = today.getFullYear();

    today = yyyy + '-' + mm + '-' + dd + 'T04:04:05+05:30';

    return {
        "customForm": {
            "internalId": "HAMS Inv"
        },
        "entity": {
            "externalId": "TEST-Customer-2VH5HD9GzYdEZiA"
        },
        "tranDate": today,
        "tranId": 'TEST-' + saltStr,
        "currency": {
            "internalId": "3"
        },
        "startDate": '2023-11-10T04:04:05+05:30',
        "endDate": '2023-11-10T04:04:05+05:30',
        "revRecStartDate": '2023-11-10T04:04:05Z',
        "revRecEndDate": '2023-11-10T04:05:06+00:00',
        "account": {
            "internalId": "133"
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
                    "description": "Description",
                    "amount": 100.05,
                    "line": 1,
                    "orderLine": 34834,
                    "quantity": 1,
                    "revRecStartDate": '2023-11-10T04:04:05-00:00',
                    "revRecEndDate": '2023-11-10T04:04:05-08:00',
                    "grossAmt": 100.05,
                    "tax1Amt": 0.0,
                    "taxCode": {
                        "internalId": "7"
                    },
                    "customFieldList": {
                        "customField": [
                            {
                                "scriptId": "custcol_poi_id",
                                "value": "1"
                            },
                            {
                                "scriptId": "custcol_new_list_price",
                                "value": 100.05
                            },
                            {
                                "scriptId": "custcol_renew_list_price",
                                "value": 100.05
                            },
                            {
                                "scriptId": "custcol_discount_flag",
                                "value": false
                            },
                            {
                                "internalId": "bafa3d2d-3958-4b1a-805a-60c714c166c7",
                                "scriptId": "custcol_sen",
                                "value": "SEN-123"
                            },
                            {
                                "scriptId": "custcol_billing_type",
                                "value": "SUBSCRIPTION"
                            },
                            {
                                "scriptId": "custcol_hosting_type",
                                "value": "BTF"
                            },
                            {
                                "internalId": "NEW",
                                "scriptId": "custcol_sale_action",
                                "value": "NEW"
                            },
                            {
                                "scriptId": "custcol_new_upgrade_flag",
                                "value": true
                            },
                            {
                                "scriptId": "custcol_license_type",
                                "value": "COMMERCIAL"
                            },
                            {
                                "scriptId": "custcol_product_base_name",
                                "value": "Atlassian Premier Support"
                            },
                            {
                                "scriptId": "custcol_product_family",
                                "value": "Services Family"
                            },
                            {
                                "scriptId": "custcol_mpv_forge_app",
                                "value": false
                            },
                            {
                                "scriptId": "custcol_legacy_entitlement_id",
                                "value": "SEN-123"
                            },
                            {
                                "scriptId": "custcol_product_key",
                                "value": "pricingplan.premier-support"
                            },
                            {
                                "scriptId": "custcol_pricing_plan_id",
                                "value": "1"
                            },
                            {
                                "scriptId": "custcol_pricing_plan_months_valid",
                                "value": "12"
                            },
                            {
                                "scriptId": "custcol_product_feature_usage",
                                "value": "1"
                            },
                            {
                                "scriptId": "custcol_product_feature_key",
                                "value": "user-tier"
                            },
                            {
                                "scriptId": "custcolmaintstart",
                                "value": "2023-01-02T04:04:05-00:00"
                            },
                            {
                                "scriptId": "custcolmaintend",
                                "value": "2023-02-05T04:04:05-00:00"
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
                    "scriptId": "custbody_atl_cust_type",
                    "value": "Aggregator"
                },
                {
                    "scriptId": "custbody_atl_fisc_integration_dt_time",
                    "value": "2023-11-01T00:00:00.000Z"
                },
                {
                    "scriptId": "custbodycust_textarea",
                    "value": "[Invoice Created By: FISC]"
                },
                {
                    "scriptId": "custbodybillingcontact",
                    "value": {
                        "internalId": "84819395"
                    }
                },
                {
                    "scriptId": "custbodytechnicalcontact",
                    "value": {
                        "internalId": "84819395"
                    }
                },
                {
                    "scriptId": "custbody_technical_cont_address",
                    "value": "Test Organisation\nAddress 1\nAddress 2\nCity, CA 2000\nUnited States of America"
                },
                {
                    "scriptId": "custbody_end_customer",
                    "value": "TEST-Customer-2VH5HD9GzYdEZiA"
                },
                {
                    "scriptId": "custbody_atl_common_shipregion",
                    "value": "Americas"
                },
                {
                    "scriptId": "custbody_atl_common_billregion",
                    "value": "Americas"
                },
                {
                    "scriptId": "custbody_usd_value_total",
                    "value": 100.5
                },
                {
                    "scriptId": "custbody_atl_cust_type",
                    "value": "CUSTOMER"
                },
                {
                    "scriptId": "custbody_fisc_allocation",
                    "value": false
                },
                {
                    "scriptId": "custbody_payment_method",
                    "value": "PAIDCC"
                },
                {
                    "scriptId": "custbody_atl_quote_date",
                    "value": "2023-02-05T04:04:05-00:00"
                },
                {
                    "scriptId": "custbody_manual_cont_reqd",
                    "value": false
                },
                {
                    "scriptId": "custbody_contract_modification",
                    "value": false
                },
                {
                    "scriptId": "custcol_sen",
                    "value": "SEN-123"
                }
            ]
        },
        "externalId": "1182520723"
    };
}