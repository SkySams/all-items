package org.example;

import org.example.business.TransactionalService;
import org.example.dao.UserDao;
import org.example.dao.UserLogDao;
import org.example.entity.UserLog;
import org.example.entity.eo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/9/8
 */
@SpringBootTest(classes = MybatisPlusApp.class)
public class SampleTest {

    @Resource
    private UserDao userDao;

    @Resource
    private UserLogDao userLogDao;

    @Resource
    private TransactionalService transactionalService;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testUserLog(){
        System.out.println("---- testUserLog method test ---");
        UserLog userLog = new UserLog();
        userLog.setUserId(1);
        userLogDao.insert(userLog);
    }

    @Test
    public void one(){
        transactionalService.annexation();
    }

}

