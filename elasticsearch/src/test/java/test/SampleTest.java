package test;

import lombok.extern.slf4j.Slf4j;
import org.example.Elasticsearch;
import org.example.dao.UserDao;
import org.example.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: zyh
 * @date: 2022/7/23
 */
@Slf4j
@SpringBootTest(classes= Elasticsearch.class)
public class SampleTest {


    @Autowired
    private UserDao userDao;

    @Test
    public void use(){
        /**
         * 在本地数据库隔离级别：读已提交
         * seata AT 默认全局隔离模式隔离模式：度未提交
          */
       User beforUser = userDao.selectById(1);
       log.info("更新前数据：{}",beforUser);
       beforUser.setName("release");
       userDao.updateById(beforUser);
       User user = userDao.selectById(1);
       log.info("更新后数据：{}",user);
    }

}
