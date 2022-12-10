package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "缓存")
@RestController
@RequestMapping("/security/cache")
public class SecurityCacheController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("清楚role缓存")
    @DeleteMapping("/role")
    public ClearRolesCacheResponse roleCache(@RequestParam String role)throws Exception{
        ClearRolesCacheRequest request = new ClearRolesCacheRequest(role);
        ClearRolesCacheResponse response = client.security().clearRolesCache(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("清楚privileges缓存")
    @DeleteMapping("/privileges")
    public ClearPrivilegesCacheResponse privilegesCache(@RequestParam String appName)throws Exception{
        ClearPrivilegesCacheRequest request = new ClearPrivilegesCacheRequest(appName);
        ClearPrivilegesCacheResponse response = client.security().clearPrivilegesCache(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("清楚realm缓存")
    @DeleteMapping("/realm")
    public ClearRealmCacheResponse realmCache()throws Exception{
        ClearRealmCacheRequest request = new ClearRealmCacheRequest(Collections.emptyList(), Collections.emptyList());
        ClearRealmCacheResponse response = client.security().clearRealmCache(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("清楚ApiKey缓存")
    @DeleteMapping("/api/key")
    public ClearSecurityCacheResponse apiKeyCache()throws Exception{
        ClearApiKeyCacheRequest request = ClearApiKeyCacheRequest.clearById("yVGMr3QByxdh1MSaicYx");
        ClearSecurityCacheResponse response = client.security().clearApiKeyCache(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation("清楚AccountToken缓存")
    @DeleteMapping("/account/token")
    public ClearSecurityCacheResponse serviceAccountToken()throws Exception{
        return null;
    }


}
