import random
import string
from datetime import datetime

now = datetime.now()

def random_charecters():
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=12))

def creditmemo_samples(customerId, invoiceId):
    randomChar = str(random_charecters())
    return {
        "source": "HAMS Integration",
        "tranId": 'TEST-' + randomChar,
        "tranDate": now.strftime("%Y-%m-%d"),
        "currency": {
            "internalId": "3"
        },

        "entity": {
            "externalId": customerId
        },
        "createdFrom": {
            "internalId": invoiceId,
            "externalId": invoiceId,
        },
        "memo": "memo",
        "exchangeRate": 10.0,
        "billingAddress": {
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
                        "internalId": "IN_TAX_5"
                    },
                    "taxRate1": 10,
                    "tax1Amt": 44,
                    "revRecStartDate": now.strftime("%Y-%m-%d"),
                    "revRecEndDate": now.strftime("%Y-%m-%d"),
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
        },
        "customFieldList": {
            "customField": [
                {
                    "internalId": "1",
                    "scriptId": "custbody_refund_code",
                    "value": "refundCode"
                },
                {
                    "scriptId": "custbody_refund_reason_desc",
                    "value": "Return"
                },
                {
                    "internalId": "2",
                    "scriptId": "custbody_payment_method",
                    "value": "PAIDCC"
                },
                {
                    "scriptId": "custbody_sla_credit_check",
                    "value": "Y"
                },
                {
                    "scriptId": "custbody_ccp_on_account_credit",
                    "value": "false"
                }
            ]
        }
    }
