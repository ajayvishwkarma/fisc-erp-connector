import random
import string
from datetime import datetime

now = datetime.now()

def random_charecters():
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=12))

def payment_samples(customerId, invoiceId):

    randomChar = str(random_charecters())

    return {
        "customer": {
            "externalId": customerId
        },
        "currency": {
            "internalId": "3"
        },
        "payment": 1.0,
        "tranDate": now.strftime("%Y-%m-%d"),
        "tranId": 'TEST-'+randomChar,
        "paymentMethod": {
            "internalId": "Bofa Remittance AU Method"
        },
        "applyList": {
            "apply": [
                {
                    "doc": invoiceId,
                    "amount": 1.0
                }
            ]
        },
        "externalId": "1182520723"
    }