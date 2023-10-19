import random
import string
from datetime import datetime

now = datetime.now()
def random_charecters():
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=12))

def invoice_samples(customerId):

    randomChar = str(random_charecters())

    return {
        "customForm": {
            "internalId": "HAMS Inv"
        },
        "entity": {
            "externalId": customerId
        },
        "tranDate": now.strftime("%Y-%m-%d"),
        "tranId": 'TEST-' + randomChar,
        "currency": {
            "internalId": "3"
        },
        "startDate": now.strftime("%Y-%m-%d"),
        "endDate": now.strftime("%Y-%m-%d"),
        "revRecStartDate": now.strftime("%Y-%m-%d"),
        "revRecEndDate": now.strftime("%Y-%m-%d"),
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
                    "quantity": 1,
                    "revRecStartDate": now.strftime("%Y-%m-%d"),
                    "revRecEndDate": now.strftime("%Y-%m-%d"),
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
                                "value": False
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
                                "value": True
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
                                "value": False
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
                                "internalId": "custcolmaintstart",
                                "scriptId": "custcolmaintstart",
                                "value": "2023-01-02"
                            },
                            {
                                "internalId": "custcolmaintend",
                                "scriptId": "custcolmaintend",
                                "value": "2023-02-05"
                            }
                        ]
                    }
                }
            ],
            "replaceAll": False
        },
        "customFieldList": {
            "customField": [
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
                    "value": customerId
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
                    "value": False
                },
                {
                    "scriptId": "custbody_payment_method",
                    "value": "PAIDCC"
                },
                {
                    "scriptId": "custbody_atl_quote_date",
                    "value": "2023-02-05"
                },
                {
                    "scriptId": "custbody_manual_cont_reqd",
                    "value": False
                },
                {
                    "scriptId": "custbody_contract_modification",
                    "value": False
                },
                {
                    "internalId": "bafa3d2d-3958-4b1a-805a-60c714c166c7",
                    "scriptId": "custcol_sen",
                    "value": "SEN-123"
                }
            ]
        },
        "externalId": "1182520723"
    }