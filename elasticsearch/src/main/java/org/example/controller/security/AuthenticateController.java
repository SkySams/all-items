package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.AuthenticateResponse;
import org.elasticsearch.client.security.ClearRolesCacheRequest;
import org.elasticsearch.client.security.ClearRolesCacheResponse;
import org.elasticsearch.client.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "Authenticate")
@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("Authenticate")
    @GetMapping()
    public AuthenticateResponse roleCache()throws Exception{
        AuthenticateResponse response = client.security().authenticate(RequestOptions.DEFAULT);
        User user = response.getUser();
        boolean enabled = response.enabled();
        final String authenticationRealmName = response.getAuthenticationRealm().getName();
        final String authenticationRealmType = response.getAuthenticationRealm().getType();
        final String lookupRealmName = response.getLookupRealm().getName();
        final String lookupRealmType = response.getLookupRealm().getType();
        final String authenticationType = response.getAuthenticationType();
        return response;
    }

}
