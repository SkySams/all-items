package com.example.basicspring.service.impl;

import com.example.basicspring.entity.MUser;
import com.example.basicspring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


/**
 * @author: zyh
 * @date: 2023/1/19
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    @Cacheable(value={"muser"}, key = "#id")
    public MUser addUser(Integer id){

        logger.trace(" --- trace --- ");
        logger.debug(" --- debug --- ");
        logger.info(" --- info --- ");
        logger.warn(" --- warn --- ");
        logger.error(" --- error --- ");

        MUser user = new MUser();
        user.setId(1);
        user.setName("name");
        System.out.println("nice");
        return user;
    }
}
