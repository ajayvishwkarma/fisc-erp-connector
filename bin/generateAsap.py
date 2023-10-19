#!/usr/bin/env python3
import atlassian_jwt_auth,sys,os
from atlassian_jwt_auth.key import DataUriPrivateKeyRetriever

issuer=os.environ['bamboo_asap_issuer']
audience=os.environ['bamboo_asap_audience']
key_id=os.environ['bamboo_asap_key_id']
private_key_uri=os.environ['bamboo_asap_private_key_uri_integration_1']+os.environ['bamboo_asap_private_key_uri_integration_2']

key_id, private_key_pem = DataUriPrivateKeyRetriever(private_key_uri).load(issuer)
signer = atlassian_jwt_auth.create_signer(issuer, key_id, private_key_pem)
a_jwt = signer.generate_jwt(audience)
print(str(a_jwt)[2:-1])
sys.exit(0)