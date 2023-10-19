function fn() {
  var env = karate.env; // get system property 'karate.env'
  karate.log('karate.env system property was:', env);
  var bambooToken = 'Bearer ' +  java.lang.System.getenv('bamboo_JWT_TOKEN');
  karate.log('bambooToken system property was:', bambooToken);
  if (!env) {
    env = 'local';
  }
  var config = {
    env: env
  }
  if (env == 'local') {
    config.hostName='http://localhost:8080'
  } else if (env == 'ddev') {
    config.hostName='https://fisc-erp-connector.ap-southeast-2.dev.atl-paas.net'
  } else if (env == 'adev') {
    config.hostName='https://fisc-erp-connector--app.ap-southeast-2.dev.atl-paas.net'
  }
  else if (env == 'stg-east') {
    config.hostName='https://fisc-erp-connector.us-east-1.staging.atl-paas.net'
  }s
  config.bambooToken = bambooToken
  karate.configure('retry',{ count:0, interval:1000});
  return config;
}