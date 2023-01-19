package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/10/29
 */
@Api(tags = "Spring 数据转换")
@RestController
@RequestMapping("/init")
public class InitBinderController implements InitializingBean {


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean afterPropertiesSet");
    }

    @InitBinder
    public void initBuider(WebDataBinder dataBinder){
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        CustomDateEditor customDateEditor = new CustomDateEditor(format,true);
//        dataBinder.registerCustomEditor(Date.class,customDateEditor);
        dataBinder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }


    @ApiOperation("时间转换")
    @GetMapping()
    public Map<String,Object> init(Date date){
        Map<String,Object> map = new HashMap<>(10);
        map.put("date", date);
        return map;
    }


}
