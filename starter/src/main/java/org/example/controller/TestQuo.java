package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/11/14
 */
@Slf4j
@Controller
@RequestMapping(value = "/quotation")
public class TestQuo {

    /**

     @Resource
     private IQuotationGoodsBusiness quotationGoodsBusiness;

     */


    @RequestMapping(value = "/freemarker")
    public String freemarker(Model model, @RequestParam String code){
        List<Object> quotationGoodsBOS = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("data",quotationGoodsBOS);
        paramMap.put("nickName","nickName");
        paramMap.put("num", "1");
        paramMap.put("date", "2022-11-15");
        paramMap.put("title", "title");
        paramMap.put("start","2022-11-15");
        model.addAttribute("arr",paramMap);
        return "demo";
    }
}
