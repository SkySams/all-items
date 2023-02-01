package com.example.basicspring.service.impl;

import com.example.basicspring.entity.MUser;
import com.example.basicspring.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author: zyh
 * @date: 2023/1/19
 */
@Slf4j
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

    @Async("MyExecutor")
    @Override
    public void testAsync() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("正在测试testAysnc.....");
    }
}
