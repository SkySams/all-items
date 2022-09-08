package org.example;

import org.example.dao.UserDao;
import org.example.entity.eo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/9/8
 */
@SpringBootTest
public class SampleTest {

    @Resource
    private UserDao userDao;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userDao.selectList(null);
        userList.forEach(System.out::println);
    }

}

