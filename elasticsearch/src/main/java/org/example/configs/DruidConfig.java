package org.example.configs;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 运行 访问 ：http://localhost:9000/druid/login.html
 *
 * @author: zyh
 * @date: 2022/3/11
 */
@Configuration
public class DruidConfig {


    /**
     * 配置 Druid 监控管理后台的Servlet；
     * 内置 Servlet 容器时没有web.xml文件，所以使用 Spring Boot 的注册 Servlet 方式
     * @return
     */
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        /**
         *  这些参数可以在 com.alibaba.druid.support.http.StatViewServlet 的父类 com.alibaba.druid.support.http.ResourceServlet 中找到
         */
        Map<String, String> initParams = new HashMap<>();

        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        /**
         * 默认就是允许所有访问
         */
        initParams.put("allow", "");

        /**
         * deny：Druid 后台拒绝谁访问，表示禁止此ip访问
         * initParams.put("deny","192.168.10.132")
         */
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean
    public FilterRegistrationBean<?> statFilter() {
        FilterRegistrationBean<?> filterRegistrationBean = new FilterRegistrationBean<>(new WebStatFilter());
        //添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略过滤的格式
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }


//    @ConfigurationProperties(prefix = "spring.datasource")
//    @Bean
//    public DataSource druid() {
//        return new DruidDataSource();
//    }
//
//



}
