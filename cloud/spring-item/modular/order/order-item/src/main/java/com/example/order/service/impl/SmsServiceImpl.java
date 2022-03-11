package com.example.order.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.example.order.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/3/11
 */
@DS("master")
@Service
public class SmsServiceImpl implements SmsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Map<String, Object>> selectAll() {
        List<Map<String, Object>>  list = jdbcTemplate.queryForList("select * from sms_advert");
        return list;
    }


}
