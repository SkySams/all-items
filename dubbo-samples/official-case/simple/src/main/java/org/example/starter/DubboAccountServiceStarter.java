package org.example.starter;

import org.example.ApplicationKeeper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: zyh
 * @date: 2023/3/14
 */
public class DubboAccountServiceStarter {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext accountContext = new ClassPathXmlApplicationContext(new String[]{"spring/dubbo-account-service.xml"});
        accountContext.getBean("service");
        JdbcTemplate accountJdbcTemplate = (JdbcTemplate)accountContext.getBean("jdbcTemplate");
        accountJdbcTemplate.update("delete from account_tbl where user_id = 'U100001'");
        accountJdbcTemplate.update("insert into account_tbl(user_id, money) values ('U100001', 10000)");

        new ApplicationKeeper(accountContext).keep();
    }

}
