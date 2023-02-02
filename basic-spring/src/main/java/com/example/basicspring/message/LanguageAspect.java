package com.example.basicspring.message;


import com.example.basicspring.apireturn.ResultData;
import com.example.basicspring.util.I18nUtils;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author: zyh
 * @date: 2023/2/2
 */
@Slf4j
@Aspect
@Component
@AllArgsConstructor
@ConditionalOnProperty(prefix = "lang", name = "open", havingValue = "true")
public class LanguageAspect {

    @Autowired
    I18nUtils i18nUtils;

    @Pointcut("execution(* com.example.basicspring.controller.*.*(..)))")
    public void annotationLangCut() {
        log.info("annotationLangCut {}","I18n 切面");
    }

    /**
     * 拦截controller层返回的结果，修改msg字段
     *
     * @param point
     * @param obj
     */
    @AfterReturning(pointcut = "annotationLangCut()", returning = "obj")
    public void around(JoinPoint point, Object obj) {
        Object resultObject = obj;
        try {
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String langFlag = request.getHeader("lang");
            if (null != langFlag) {
                ResultData r = (ResultData) obj;
                String msg = r.getMessage().trim();
                if (StringUtils.isNotEmpty(msg)) {
                    if ("CN".equals(langFlag)) {
                        Locale locale = Locale.CHINA;
                        msg = i18nUtils.getKey(msg, locale);
                    } else if ("EN".equals(langFlag)) {
                        Locale locale = Locale.US;
                        msg = i18nUtils.getKey(msg, locale);
                    } else {
                        msg = i18nUtils.getKey(msg);
                    }
                }
                r.setMessage(msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //返回原值
            obj = resultObject;
        }
    }
}
