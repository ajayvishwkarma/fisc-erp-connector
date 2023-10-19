import random
import string
from datetime import date, datetime, timedelta

now = datetime.now()

states = ["CA", "CO", "IL", "ME"]

def random_charecters():
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=12))

def customer_samples():

    randomChar = str(random_charecters())
    today = date.today()
    yesterday = today - timedelta(days = 1)

    return {
        "entityId": randomChar+" Organisation USD (117501349)",
        "externalId": randomChar,
        "companyName": randomChar+" Organisation",
        "startDate": yesterday.strftime("%Y-%m-%d"),
        "isPerson": False,
        "subsidiary": {
            "internalId": "27",
            "type": {
                "value": "subsidiary"
            }
        },
        "currency": {
            "internalId": "1"
        },
        "addressbookList": {
            "addressbook": [
                {
                    "defaultShipping": True,
                    "defaultBilling": True,
                    "label": "true",
                    "addressbookAddress": {
                        "country": {
                            "value": "_unitedStates"
                        },
                        "addressee": randomChar+" Organisation",
                        "addr1": "Address 1",
                        "addr2": "Address 2",
                        "city": "City",
                        "state": random.choice(states),
                        "zip": "90401"
                    }
                }
            ]
        },
        "customFieldList": {
            "customField": [
                {
                    "scriptId": "custentity_iscommerce",
                    "value": false
                },
                {
                    "scriptId": "custentity_gst_number",
                    "value": "GST-tax-number"
                },
                {
                    "scriptId": "custentity_ccp_customer_category",
                    "value": "CCP-Category"
                },
                {
                    "scriptId": "custentity_hams_id",
                    "value": "Cust-HAMS-ID"
                },
                {
                    "scriptId": "custentity_billing_contact_name",
                    "value": "CUST-BILL-CONTACT-NAME"
                }
            ]
        },
        "vatRegNumber": "vat-"+randomChar,
        "customerId": "CUST-"+randomChar,
        "transactionId": "TRANS-"+randomChar,
        "entityStatus": {
            "internalId": "3"
        },
        "isCustomerActive": "true",
        "terms": {
            "internalId": "45"
        }
    }