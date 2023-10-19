function getRefundPostRequest() {
    var SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    var salt = new java.lang.StringBuilder();
    var rnd = new java.util.Random();
    while (salt.length() < 18) { // length of the random string.
        var index = rnd.nextFloat() * SALTCHARS.length();
        salt.append(SALTCHARS.charAt(index));
    }

    var saltStr = salt.toString();

    var SimpleDateFormat = Java.type('java.text.SimpleDateFormat');
    var sdf = new SimpleDateFormat('yyyy-MM-dd');
    var date = new java.util.Date();

    return {
        "externalId": 1182520723,
        "createdDate": sdf.format(date) +'T04:04:05Z',
        "transactionNumber": "IN-000-001-CR-1",
        "customer": {
            "externalId": "TEST-c943a48e-26c7-4012-ab9e-415d5257cee2"
        },
        "currencyName": "USD",
        "memo": "Paid To: abc customer",
        "paymentMethod": {
            "internalId": "7"
        },
        "tranId": 'TEST-' + saltStr,
        "applyList": {
            "apply": [
                {
                    "refNum": "<placeHolder>",
                    "applyDate": sdf.format(date) +'T04:04:05Z',
                    "total": 100
                }
            ],
            "replaceAll": false
        },
        "customFieldList": {
            "customField": [
                {
                    "scriptId": "custbody_tns_transaction_receipt_id",
                    "value": "test-receipt-1234"
                },
                {
                    "scriptId": "stripe_payment_gateway",
                    "value": "Stripe AU"
                }
            ]
        }
    };
}
