package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.CreateTokenRequest;
import org.elasticsearch.client.security.CreateTokenResponse;
import org.elasticsearch.client.security.InvalidateTokenRequest;
import org.elasticsearch.client.security.InvalidateTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "TOKEN")
@RestController
@RequestMapping("/toekn")
public class TokenController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("创建TOKEN")
    @PostMapping()
    public CreateTokenResponse create()throws Exception{
        final char[] password = new char[]{'t', 'e', 's', 't', '-', 'u', 's', 'e', 'r', '-', 'p', 'a', 's', 's', 'w', 'o', 'r', 'd'};
        CreateTokenRequest createTokenRequest = CreateTokenRequest.passwordGrant("token_user", password);
        CreateTokenResponse createTokenResponse = client.security().createToken(createTokenRequest, RequestOptions.DEFAULT);
        return createTokenResponse;
    }

    /**
     * https://www.elastic.co/guide/en/elasticsearch/client/java-rest/7.14/java-rest-high-security-invalidate-token.html#java-rest-high-security-invalidate-token-request
     *
     * @return
     * @throws Exception
     */
    @ApiOperation("使TOKEN失效")
    @PutMapping ()
    public InvalidateTokenResponse invalidate(@RequestParam String accessToken)throws Exception{
        InvalidateTokenRequest invalidateTokenRequest = InvalidateTokenRequest.accessToken(accessToken);
        InvalidateTokenResponse response =
                client.security().invalidateToken(invalidateTokenRequest, RequestOptions.DEFAULT);
        return response;
    }


}
