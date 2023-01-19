package com.example.basicspring.controller;

import com.example.basicspring.entity.MUser;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 有错误
 * @author: zyh
 * @date: 2023/1/19
 */
@RestController
public class UserController {

    private final String USER_BUCKET_KEY = "user:id:";

    @Autowired
    private RedissonClient redisson;

    @PostMapping("/addUser")
    public Integer insert(@RequestBody MUser user) {
        RBucket<Object> bucket = redisson.getBucket(USER_BUCKET_KEY + user.getId());
        //塞入缓存
        MUser u = new MUser();
        u.setId(user.getId());
        u.setName(user.getName());
        bucket.set(user);
        return user.getId();
    }

    @GetMapping("/getUser")
    public MUser insert(@RequestParam Integer userId) {

        RBucket<MUser> bucket = redisson.getBucket(USER_BUCKET_KEY + userId);
        if (bucket != null) {
            System.out.println(bucket.get());
            return bucket.get();
        }
        return null;
    }

    @PostMapping("/updateUser")
    public Integer update(@RequestBody MUser user) {
        RBucket<MUser> bucket = redisson.getBucket(USER_BUCKET_KEY + user.getId());
        //更新缓存
        bucket.set(user);
        return user.getId();
    }

    @PostMapping("/deleteUser")
    public MUser delete(@RequestParam Long id) {
        RBucket<MUser> bucket = redisson.getBucket(USER_BUCKET_KEY + id);
        //删除并获取
        MUser user = bucket.getAndDelete();
        return user;
    }

}
