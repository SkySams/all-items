package com.example.basicspring.interceptor;

import com.example.basicspring.annotation.RepeatDaMie;
import com.example.basicspring.util.ContextUtil;
import com.example.basicspring.util.Md5Encrypt;
import com.example.basicspring.util.RedisUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: zyh
 * @date: 2023/2/1
 */
@Component
public class ApiRepeatInterceptor implements HandlerInterceptor {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final String IDEMPOTENCE_KEY = "idem:";

    private static final String POST = "POST";
    private static final String GET = "GET";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                // 获取RepeatDaMie注解
                RepeatDaMie repeatDaMie = handlerMethod.getMethodAnnotation(RepeatDaMie.class);
                if (null == repeatDaMie) {
                    return true;
                }
                //限制的时间范围
                int seconds = repeatDaMie.second();
                //这个用户唯一标识，可以自己细微调整，是userId还是token还是sessionId还是不需要
                String userUniqueKey = request.getHeader("userUniqueKey");
                String method = request.getMethod();
                String apiParams = "";
                if (GET.equals(method)) {
                    log.info("GET请求来了");
                    apiParams = new ObjectMapper().writeValueAsString(request.getParameterMap());
                } else if (POST.equals(method)) {
                    log.info("POST请求来了");
//                    CustomHttpServletRequestWrapper wrapper = (CustomHttpServletRequestWrapper) request;
//                    apiParams = wrapper.getBody();
                    apiParams = new ObjectMapper().writeValueAsString(request.getParameterMap());
                }
                log.info("当前参数是：{}", apiParams);
                // 存储key
                String keyRepeatDaMie = Md5Encrypt.md5(userUniqueKey + request.getServletPath() + apiParams);
                RedisUtils redisUtils = ContextUtil.getBean(RedisUtils.class);
                if (Objects.nonNull(redisUtils.get(IDEMPOTENCE_KEY+keyRepeatDaMie))) {
                    log.info("重复请求了，重复请求了，拦截了");
                    returnData(response, repeatDaMie.describe());
                    return false;
                } else {
                    redisUtils.setWithTime(IDEMPOTENCE_KEY+keyRepeatDaMie, true, seconds);
                }

            }
            return true;
        } catch (Exception e) {
            log.warn("请求出现异常,errorMsg={}", e.getMessage());
            returnData(response, "请求出现异常");
            return false;
        }
    }

    public void returnData(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        ObjectMapper objectMapper = new ObjectMapper();
        //这里传提示语可以改成自己项目的返回数据封装的类
        response.getWriter().println(objectMapper.writeValueAsString(msg));
        return;
    }

}
