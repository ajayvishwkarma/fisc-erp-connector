import logging
import os
from locust import task, constant_pacing, HttpUser, SequentialTaskSet
from perfkit import asap

from lib.contact_events import contact_samples
from lib.creditmemo_events import creditmemo_samples
from lib.customer_events import customer_samples
from lib.invoice_events import invoice_samples
from lib.payment_events import payment_samples

# Set up ASAP authenticator for making requests to the service
perf_auth = asap.asap_auth("fisc-erp-connector")

# Set up logger
logger = logging.getLogger()
logger.setLevel(os.getenv("LOG_LEVEL", logging.NOTSET))

#TARGET_ENVIRONMENT = "ddev"
TARGET_ENVIRONMENT = os.environ.get("MICROS_ENV", "unknown")

hosts = {
    'ddev': 'fisc-erp-connector.ap-southeast-2.dev.atl-paas.net',
    'stg-east': 'fisc-erp-connector.us-east-1.staging.atl-paas.net',
    'adev': 'fisc-erp-connector--app.ap-southeast-2.dev.atl-paas.net',
}

if (TARGET_ENVIRONMENT != "ddev" and TARGET_ENVIRONMENT != "stg-east" and TARGET_ENVIRONMENT != "adev"):
    raise Exception(TARGET_ENVIRONMENT + " environment is not support for performance testing. Only 'ddev', 'adev' and 'stg-east' are supported now.")


class TransactionTasksSet(SequentialTaskSet):

    customerId = "cust_id"
    invoiceId = "inv_id"

    @task
    def post_create_customer(self):
        post_create_customer_payload = customer_samples()
        post_create_customer_endpoint = "/api/v1/customer"
        if (not post_create_customer_payload):
            logger.info("Create customer details are empty")
        else:
            logger.info("Trying to create customer  %s ",  post_create_customer_payload['externalId'])
            TransactionTasksSet.customerId = post_create_customer_payload['externalId']
            response = self.client.post(
                post_create_customer_endpoint,
                timeout=500,
                json=post_create_customer_payload,
                name="post_create_customer",
                auth=perf_auth,
                headers={
                    "Content-Type": 'application/json',
                },
            )

            if not 200 <= response.status_code < 300:
                logger.info("POST create customer failed with status code = %d, Body = %s",response.status_code, response.text)
            else:
                logger.info("POST create customer successful with status code = %d",response.status_code)

    @task
    def post_create_contact(self):
        post_create_contact_payload = contact_samples(TransactionTasksSet.customerId)
        post_create_contact_endpoint = "/api/v1/contact"
        if (not post_create_contact_payload):
            logger.info("Create contact details are empty")
        else:
            logger.info("Trying to create contact  %s ",  post_create_contact_payload["externalId"])
            response = self.client.post(
                post_create_contact_endpoint,
                timeout=500,
                json=post_create_contact_payload,
                name="post_create_contact",
                auth=perf_auth,
                headers={
                    "Content-Type": 'application/json',
                },
            )

            if not 200 <= response.status_code < 300:
                logger.info("POST create contact failed with status code = %d, Body = %s",response.status_code, response.text)
            else:
                logger.info("POST create contact successful with status code = %d",response.status_code)

    @task
    def post_create_invoice(self):
        post_create_invoice_payload = invoice_samples(TransactionTasksSet.customerId)
        post_create_invoice_endpoint = "/api/v1/invoice"
        if (not post_create_invoice_payload):
            logger.info("Create invoice details are empty")
        else:
            logger.info("Trying to create invoice  %s ",  post_create_invoice_payload["tranId"])

            response = self.client.post(
                post_create_invoice_endpoint,
                timeout=500,
                json=post_create_invoice_payload,
                name="post_create_invoice",
                auth=perf_auth,
                headers={
                    "Content-Type": 'application/json',
                },
            )

            if not 200 <= response.status_code < 300:
                logger.info("POST create invoice failed with status code = %d, Body = %s",response.status_code, response.text)
            else:
                invoice = response.json()
                TransactionTasksSet.invoiceId = invoice["internalId"]
                logger.info("POST create invoice successful with status code = %d",response.status_code)

    @task
    def post_create_payment(self):
        post_create_payment_payload = payment_samples(TransactionTasksSet.customerId, TransactionTasksSet.invoiceId)
        post_create_payment_endpoint = "/api/v1/payment"
        if (not post_create_payment_payload):
            logger.info("Create payment details are empty")
        else:
            logger.info("Trying to create payment  %s ",  post_create_payment_payload["tranId"])
            response = self.client.post(
                post_create_payment_endpoint,
                timeout=500,
                json=post_create_payment_payload,
                name="post_create_payment",
                auth=perf_auth,
                headers={
                    "Content-Type": 'application/json',
                },
            )

            if not 200 <= response.status_code < 300:
                logger.info("POST create payment failed with status code = %d, Body = %s",response.status_code, response.text)
            else:
                logger.info("POST create payment successful with status code = %d",response.status_code)

    @task
    def post_create_creditmemo(self):
        post_create_creditmemo_payload = creditmemo_samples(TransactionTasksSet.customerId, TransactionTasksSet.invoiceId)
        post_create_creditmemo_endpoint = "/api/v1/creditnote"
        if (not post_create_creditmemo_payload):
            logger.info("Create creditmemo details are empty")
        else:
            logger.info("Trying to create creditmemo  %s ",  post_create_creditmemo_payload["tranId"])
            response = self.client.post(
                post_create_creditmemo_endpoint,
                timeout=500,
                json=post_create_creditmemo_payload,
                name="post_create_creditmemo",
                auth=perf_auth,
                headers={
                    "Content-Type": 'application/json',
                },
            )

            if not 200 <= response.status_code < 300:
                logger.info("POST create creditmemo failed with status code = %d, Body = %s",response.status_code, response.text)
            else:
                logger.info("POST create creditmemo successful with status code = %d",response.status_code)

class testHttpUser(HttpUser):
    host = f"https://{hosts[TARGET_ENVIRONMENT]}"
    wait_time = constant_pacing(1)
    tasks = [TransactionTasksSet]


