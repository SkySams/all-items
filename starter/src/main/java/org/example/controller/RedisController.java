package org.example.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;

/**
 *
 * 测试 redisson
 *
 * @author: zyh
 * @date: 2022/3/4
 */
@RestController
public class RedisController {

    @Autowired
    private RedissonClient redissonClient;


    @GetMapping("/query")
    public String query() {
        RBucket<Object> bucket = redissonClient.getBucket("REDIS:NAME");
        if(bucket.get() == null){
            bucket.set("BOBO");
            List<Object> list = new ArrayList<>();
            list.add("java");
            list.add("jsp");
            bucket.set(list);
        }
        return bucket.get().toString();
    }

    /**
     * 测试上传文件已经被要求做出回应之后，RepeatedlyReadFilter再次拦截回应错误
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     * @throws IOException
     */
    @GetMapping("test")
    public Object test(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
        StringBuffer sbuf = new StringBuffer(20);
        sbuf.append("{\"state\":1,");
        sbuf.append("\"message\":");
        sbuf.append("\"上传成功\",");
        sbuf.append("\"data\":").append("\"" + "demo" + "\"").append("}");
        httpServletResponse.setContentType("text/plain;charset=UTF-8");
        httpServletResponse.getWriter().write(sbuf.toString());
        return null;
    }

}
