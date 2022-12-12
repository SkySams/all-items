package org.example.controller.security;

import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.security.*;
import org.elasticsearch.client.security.user.privileges.Role;
import org.elasticsearch.common.Nullable;
import org.elasticsearch.common.unit.TimeValue;

import java.util.Arrays;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/12/12
 */

public class KeyController {



    public CreateApiKeyResponse roleCache(String name, List<Role> roles, @Nullable TimeValue expiration,
                                            RefreshPolicy refreshPolicy)throws Exception{
        CreateApiKeyRequest createApiKeyRequest = new CreateApiKeyRequest(name, roles, expiration, refreshPolicy);
//        CreateApiKeyResponse response = client.security().createApiKey(createApiKeyRequest, RequestOptions.DEFAULT);
        return null;
    }


    public CreateApiKeyResponse grant(String name, List<Role> roles, @Nullable TimeValue expiration,
                                      RefreshPolicy refreshPolicy,String username){
        CreateApiKeyRequest createApiKeyRequest = new CreateApiKeyRequest(name, roles, expiration, refreshPolicy);
        GrantApiKeyRequest.Grant grant = GrantApiKeyRequest.Grant.passwordGrant(username, null);
        GrantApiKeyRequest grantApiKeyRequest = new GrantApiKeyRequest(grant, createApiKeyRequest);
//        CreateApiKeyResponse apiKeyResponse = client.security().grantApiKey(grantApiKeyRequest, RequestOptions.DEFAULT);
        return null;
    }

    public void getInformationKey(){
//        GetApiKeyRequest getApiKeyRequest = GetApiKeyRequest.usingApiKeyId(createApiKeyResponse1.getId(), false);
    }

    public void invalidate(){
        InvalidateApiKeyRequest invalidateApiKeyRequest = InvalidateApiKeyRequest.usingApiKeyIds(
                Arrays.asList("kI3QZHYBnpSXoDRq1XzR", "ko3SZHYBnpSXoDRqk3zm"), false);
//
//        InvalidateApiKeyRequest invalidateApiKeyRequest = InvalidateApiKeyRequest.usingApiKeyName(createApiKeyResponse2.getName(),
//                false);
    }

}
