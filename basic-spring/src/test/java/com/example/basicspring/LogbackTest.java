package com.example.basicspring;

import com.example.basicspring.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: zyh
 * @date: 2023/1/30
 */
@SpringBootTest
public class LogbackTest {

    @Autowired
    private UserServiceImpl userService;

    @Test
    public void testLevel() {
        userService.addUser(1);
    }


}
