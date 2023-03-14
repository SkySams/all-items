package org.example.starter;

import org.example.ApplicationKeeper;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: zyh
 * @date: 2023/3/14
 */
public class DubboStockServiceStarter {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext stockContext = new ClassPathXmlApplicationContext(
                new String[] {"spring/dubbo-stock-service.xml"});
        stockContext.getBean("service");
        JdbcTemplate stockJdbcTemplate = (JdbcTemplate)stockContext.getBean("jdbcTemplate");
        stockJdbcTemplate.update("delete from stock_tbl where commodity_code = 'C00321'");
        stockJdbcTemplate.update("insert into stock_tbl(commodity_code, count) values ('C00321', 1000)");
        new ApplicationKeeper(stockContext).keep();

    }

}
