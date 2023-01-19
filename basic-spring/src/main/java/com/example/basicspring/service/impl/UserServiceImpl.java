package com.example.basicspring.service.impl;

import com.example.basicspring.entity.MUser;
import com.example.basicspring.service.UserService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author: zyh
 * @date: 2023/1/19
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(value={"muser"}, key = "#id")
    public MUser addUser(Integer id){
        MUser user = new MUser();
        user.setId(1);
        user.setName("name");
        System.out.println("nice");
        return user;
    }
}
