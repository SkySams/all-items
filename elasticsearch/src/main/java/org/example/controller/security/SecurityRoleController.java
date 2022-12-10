package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.*;
import org.elasticsearch.client.security.user.privileges.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "删除特权角色",notes = "删除特权角色")
    @DeleteMapping("/role/privileges")
    public DeletePrivilegesResponse deletedPrivileges(@RequestParam String roleName) throws Exception{
        DeletePrivilegesRequest request = new DeletePrivilegesRequest("testapp", "read", "write");
        DeletePrivilegesResponse response = client.security().deletePrivileges(request, RequestOptions.DEFAULT);
        return response;
    }



}
