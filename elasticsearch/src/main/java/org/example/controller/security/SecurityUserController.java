package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.*;
import org.elasticsearch.client.security.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "安全用户")
@RestController
@RequestMapping("/security")
public class SecurityUserController {

    @Autowired
    private RestHighLevelClient client;


    @ApiOperation(value = "创建或修改",notes = "创建或修改security用户")
    @PostMapping("/create")
    public PutUserResponse creareOrUpdate() throws Exception{
        char[] password = new char[]{'t', 'e', 's', 't', '-', 'u', 's', 'e', 'r', '-', 'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        User user = new User("sky", Collections.singletonList("superuser"));
        PutUserRequest request = PutUserRequest.withPassword(user, password, true, RefreshPolicy.NONE);
        PutUserResponse response = client.security().putUser(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "创建或修改",notes = "创建或修改security用户")
    @PostMapping("/update")
    public PutUserResponse createOrUpdateOne()throws Exception{
        char[] password = new char[]{'t', 'e', 's', 't', '-', 'u', 's', 'e', 'r', '-', 'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        User user = new User("sky", Collections.singletonList("superuser"));
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2withHMACSHA512");
        PBEKeySpec keySpec = new PBEKeySpec(password, "salt".getBytes(StandardCharsets.UTF_8), 10000, 256);
        final byte[] pbkdfEncoded = secretKeyFactory.generateSecret(keySpec).getEncoded();
        char[] passwordHash = ("{PBKDF2}10000$" + Base64.getEncoder().encodeToString("salt".getBytes(StandardCharsets.UTF_8))
                + "$" + Base64.getEncoder().encodeToString(pbkdfEncoded)).toCharArray();
        PutUserRequest request = PutUserRequest.withPasswordHash(user, passwordHash, true, RefreshPolicy.NONE);

        PutUserResponse response = client.security().putUser(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "获取用户",notes = "获取security用户")
    @GetMapping("/user")
    public GetUsersResponse getUsers(@RequestParam String username)throws  Exception{
        GetUsersRequest request = new GetUsersRequest(username);
        GetUsersResponse response = client.security().getUsers(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "删除用户",notes = "删除security用户")
    @DeleteMapping("/user")
    public DeleteUserResponse deleted(@RequestParam String username)throws  Exception{
        DeleteUserRequest request = new DeleteUserRequest(username);
        DeleteUserResponse response = client.security().deleteUser(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "启用用户",notes = "启用security用户")
    @PutMapping("/enable")
    public boolean enable(@RequestParam String usernames)throws  Exception{
        EnableUserRequest request = new EnableUserRequest(usernames,RefreshPolicy.NONE);
        boolean response = client.security().enableUser(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "禁用用户",notes = "禁用security用户")
    @PutMapping("/disable")
    public boolean disable(@RequestParam String username) throws  Exception{
        DisableUserRequest request = new DisableUserRequest(username,RefreshPolicy.NONE);
        boolean response = client.security().disableUser(request, RequestOptions.DEFAULT);
        return response;
    }

    @ApiOperation(value = "更改密码",notes = "更改密码")
    @PutMapping("/change/password")
    public boolean changePassword(@RequestParam String username,@RequestParam String password) throws  Exception{
        char[] newPassword = new char[]{'t', 'e', 's', 't', '-', 'u', 's', 'e', 'r', '-', 'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        ChangePasswordRequest request = new ChangePasswordRequest(username, newPassword, RefreshPolicy.NONE);
        boolean response = client.security().changePassword(request, RequestOptions.DEFAULT);
        return response;
    }





}
