function getPaymentPostRequest() {
    var SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    var salt = new java.lang.StringBuilder();
    var rnd = new java.util.Random();
    while (salt.length() < 18) { // length of the random string.
        var index = rnd.nextFloat() * SALTCHARS.length();
        salt.append(SALTCHARS.charAt(index));
    }

    var saltStr = salt.toString();

    return {
        "customer": {
            "externalId": "TEST-Customer-I7R1wN50QyJInuJ"
        },
        "currency": {
            "internalId": "3"
        },
        "payment": 1.0,
        "tranDate": "2023-02-28T00:00:00+05:30",
        "tranId": 'TEST-'+saltStr,
        "paymentMethod": {
            "internalId": "7"
        },
        "arAcct": {
            "internalId": "Atlassian AU BU USD"
        },
        "applyList": {
            "apply": [
                {
                    "doc": "300000006156981",
                    "amount": 1.0
                }
            ]
        },
        "externalId": 1182520723,
        "customFieldList": {
            "customField": [
                {
                    "internalId": "3",
                    "scriptId": "stripe_payment_gateway",
                    "value": "Stripe NL"
                }
            ]
        }
    };
}


