import random
import string

states = ["CA", "CO", "IL", "ME"]

def random_charecters():
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=12))

def contact_samples(customerId):
    randomChar = str(random_charecters())

    return {
        "entityId": "Test Organisation USD (117501349)",
        "company": {
            "externalId": customerId
        },
        "firstName": "F-" + randomChar,
        "lastName": "L-" + randomChar,
        "email": randomChar +"@inttest.com",
        "externalId": "1489212143",
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
                        "addr1": "Address 1"+ randomChar,
                        "addr2": "Address 2"+ randomChar,
                        "city": "City",
                        "state": random.choice(states),
                        "zip": "90401"
                    }
                }
            ]
        }
    }