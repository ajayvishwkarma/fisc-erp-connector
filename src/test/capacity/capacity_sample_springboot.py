from locust import TaskSet, task  # pylint: disable=import-error
from perfkit.clients import HttpBaseLocust  # pylint: disable=import-error


class MyTaskSet(TaskSet):

    @task
    def index(self):
        self.client.get('https://fisc-erp-connector.us-east-1.staging.atl-paas.net/heartbeat')


class MyLocust(HttpBaseLocust):
    task_set = MyTaskSet
