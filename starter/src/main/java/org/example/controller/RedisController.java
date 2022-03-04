package org.example.controller;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
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

}
