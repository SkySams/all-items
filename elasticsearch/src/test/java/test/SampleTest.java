package test;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import org.example.Elasticsearch;
import org.example.dao.UserDao;
import org.example.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: zyh
 * @date: 2022/7/23
 */
//@SpringBootTest(classes= Elasticsearch.class)
@MybatisPlusTest
public class SampleTest {

    @Resource
    private UserDao userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
    }


}
