package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: zyh
 * @date: 2022/3/7
 */
@MapperScan("org.example.dao")
@SpringBootApplication
public class Elasticsearch {

    public static void main(String[] args) {
        SpringApplication.run(Elasticsearch.class,args);
    }

}
