package org.example.controller.security;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.security.GetSslCertificatesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/12/10
 */
@Api(tags = "证书")
@RestController
@RequestMapping("/ssl")
public class SSLController {

    @Autowired
    private RestHighLevelClient client;

    @ApiOperation("SSL")
    @GetMapping()
    public GetSslCertificatesResponse getSSLCertificates()throws Exception{
        GetSslCertificatesResponse response = client.security().getSslCertificates(RequestOptions.DEFAULT);
        return response;
    }
}
