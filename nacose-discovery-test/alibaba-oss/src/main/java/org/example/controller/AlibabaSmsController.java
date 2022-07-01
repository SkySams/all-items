package org.example.controller;

import com.alibaba.alicloud.sms.ISmsService;
import com.aliyuncs.dysmsapi.model.v20170525.SendBatchSmsResponse;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zyh
 * @date: 2022/6/25
 */
@RestController
@RequestMapping
public class AlibabaSmsController {

    @Autowired
    private ISmsService smsService;

    @RequestMapping("/batch-sms-send.do")
    public SendSmsResponse batchsendCheckCode(
            @RequestParam(name = "code") String code) {

        SendSmsRequest request = new SendSmsRequest();
        // Required:the mobile number
        request.setPhoneNumbers("15986543431");
        // Required:SMS-SignName-could be found in sms console
        request.setSignName("test");
        // Required:Template-could be found in sms console
        request.setTemplateCode("test");
        // Required:The param of sms template.For exmaple, if the template is "Hello,your verification code is ${code}". The param should be like following value
        request.setTemplateParam("{\"code\":\"" + code + "\"}");
        SendSmsResponse sendSmsResponse ;
        try {
            sendSmsResponse = smsService.sendSmsRequest(request);
        }
        catch (ClientException e) {
            e.printStackTrace();
            sendSmsResponse = new SendSmsResponse();
        }
        return sendSmsResponse;
    }

}
