package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.*;
import org.elasticsearch.client.security.user.privileges.ApplicationPrivilege;
import org.elasticsearch.client.security.user.privileges.IndicesPrivileges;
import org.elasticsearch.client.security.user.privileges.Role;
import org.elasticsearch.common.util.set.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "安全角色")
@RestController
@RequestMapping("/security")
public class SecurityRoleController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation(value = "创建或修改",notes = "安全角色")
    @PostMapping("/role")
    public PutRoleRequest creareOrUpdate() throws Exception{
        final Role role = Role.builder()
                .name("testPutRole")
                .clusterPrivileges(Role.ClusterPrivilegeName.ALL_ARRAY)
                .build();
        final PutRoleRequest request = new PutRoleRequest(role, RefreshPolicy.NONE);
        return request;
    }

    @ApiOperation(value = "获取角色",notes = "获取角色")
    @GetMapping("/role")
    public GetRolesResponse getRole(@RequestParam String roleName) throws Exception{
        GetRolesRequest request = new GetRolesRequest(roleName);
        GetRolesResponse response = client.security().getRoles(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "删除角色",notes = "删除角色")
    @DeleteMapping("/role")
    public DeleteRoleResponse deleted(@RequestParam String roleName) throws Exception{
        DeleteRoleRequest deleteRoleRequest = new DeleteRoleRequest(roleName);
        DeleteRoleResponse response = client.security().deleteRole(deleteRoleRequest, RequestOptions.DEFAULT);
        boolean found = response.isFound();
        return response;
    }

    @ApiOperation(value = "创建特权角色",notes = "创建特权角色")
    @PostMapping("/role/privileges")
    public PutPrivilegesResponse createPrivileges(@RequestParam String roleName) throws Exception{
        final List<ApplicationPrivilege> privileges = new ArrayList<>();
        privileges.add(ApplicationPrivilege.builder()
                .application("app01")
                .privilege("all")
                .actions(Sets.newHashSet("action:login"))
                .metadata(Collections.singletonMap("k1", "v1"))
                .build());
        privileges.add(ApplicationPrivilege.builder()
                .application("app01")
                .privilege("write")
                .actions(Sets.newHashSet("action:write"))
                .build());
        final PutPrivilegesRequest putPrivilegesRequest = new PutPrivilegesRequest(privileges, RefreshPolicy.IMMEDIATE);

        final PutPrivilegesResponse response = client.security().putPrivileges(putPrivilegesRequest, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "删除特权角色",notes = "删除特权角色")
    @DeleteMapping("/role/privileges")
    public DeletePrivilegesResponse deletedPrivileges(@RequestParam String roleName) throws Exception{
        DeletePrivilegesRequest request = new DeletePrivilegesRequest("testapp", "read", "write");
        DeletePrivilegesResponse response = client.security().deletePrivileges(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "获取内置特权",notes = "获取内置特权")
    @GetMapping("/role/privileges")
    public GetBuiltinPrivilegesResponse getBuiltinPrivileges(@RequestParam String roleName) throws Exception{
        GetBuiltinPrivilegesResponse response = client.security().getBuiltinPrivileges(RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "获取应用程序权限",notes = "获取应用程序权限")
    @GetMapping("/role/application/privileges")
    public GetPrivilegesResponse getApplicationPrivileges(@RequestParam String roleName) throws Exception{
        GetPrivilegesRequest request = new GetPrivilegesRequest("testapp", "write");
//        GetPrivilegesRequest request = GetPrivilegesRequest.getApplicationPrivileges("testapp");
        GetPrivilegesResponse response = client.security().getPrivileges(request, RequestOptions.DEFAULT);
        return response;
    }


    @ApiOperation(value = "Has",notes = "获取应用程序权限")
    @GetMapping("/role/privileges/has")
    public HasPrivilegesResponse has(@RequestParam String roleName) throws Exception{
        HasPrivilegesRequest request = new HasPrivilegesRequest(
                Sets.newHashSet("monitor", "manage"),
                Sets.newHashSet(
                        IndicesPrivileges.builder().indices("logstash-2018-10-05").privileges("read", "write")
                                .allowRestrictedIndices(false).build(),
                        IndicesPrivileges.builder().indices("logstash-2018-*").privileges("read")
                                .allowRestrictedIndices(true).build()
                ),
                null
        );
        HasPrivilegesResponse response = client.security().hasPrivileges(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "Has",notes = "获取应用程序权限")
    @GetMapping("/user/privileges")
    public GetUserPrivilegesResponse getUserPrivileges() throws Exception{
        GetUserPrivilegesResponse response = client.security().getUserPrivileges(RequestOptions.DEFAULT);
        return response;
    }






}
