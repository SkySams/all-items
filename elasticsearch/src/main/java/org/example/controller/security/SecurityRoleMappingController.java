package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.*;
import org.elasticsearch.client.security.support.expressiondsl.RoleMapperExpression;
import org.elasticsearch.client.security.support.expressiondsl.expressions.AnyRoleMapperExpression;
import org.elasticsearch.client.security.support.expressiondsl.fields.FieldRoleMapperExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "角色映射")
@RestController
@RequestMapping("/role")
public class SecurityRoleMappingController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "创建",notes = "创建")
    @PostMapping("mapping")
    public PutRoleMappingResponse create(@RequestParam String roleMappingNames) throws Exception{
        final RoleMapperExpression rules = AnyRoleMapperExpression.builder()
                .addExpression(FieldRoleMapperExpression.ofUsername("*"))
                .addExpression(FieldRoleMapperExpression.ofGroups("cn=admins,dc=example,dc=com"))
                .build();
        final PutRoleMappingRequest request = new PutRoleMappingRequest(roleMappingNames, true,
                Collections.singletonList("superuser"), Collections.emptyList(), rules, null, RefreshPolicy.NONE);
        final PutRoleMappingResponse response = client.security().putRoleMapping(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "获取",notes = "获取")
    @GetMapping("mapping")
    public GetRoleMappingsResponse get(@RequestParam String roleMappingNames) throws Exception{
        final GetRoleMappingsRequest request = new GetRoleMappingsRequest(roleMappingNames);
        final GetRoleMappingsResponse response = client.security().getRoleMappings(request, RequestOptions.DEFAULT);
        return response;
    }


    @ApiOperation(value = "获取",notes = "获取")
    @DeleteMapping("mapping")
    public DeleteRoleMappingResponse deleted(@RequestParam String roleMappingNames) throws Exception{
        final DeleteRoleMappingRequest request = new DeleteRoleMappingRequest(roleMappingNames, RefreshPolicy.NONE);
        final DeleteRoleMappingResponse response = client.security().deleteRoleMapping(request, RequestOptions.DEFAULT);
        return response;
    }

}
