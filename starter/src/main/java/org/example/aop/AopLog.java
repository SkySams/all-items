package org.example.aop;

import com.xiaoleilu.hutool.json.JSON;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.example.annotation.OperationLog;
import org.example.encapsulat.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: zyh
 * @date: 2022/5/30
 */
@Aspect
@Component
@Slf4j
public class AopLog {

    /**
     * 切入点
     */
    @Pointcut("@annotation(org.example.annotation.OperationLog)")
    public void pointcut(){
    }
    @Before(value = "pointcut()")
    public void before(JoinPoint joinPoint){
        System.out.println("前");
    }

    @After("pointcut()")
    public void after (JoinPoint joinPoint) throws Throwable {


        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 获取 @WebLog 注解的描述信息
        String methodDescription = getAspectLogDescription(joinPoint);

        log.info("URL            : {}", request.getRequestURL().toString());
        // 打印描述信息
        log.info("Description    : {}", methodDescription);
        // 打印 Http method
        log.info("HTTP Method    : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method   : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        // 打印请求的 IP
        log.info("IP             : {}", request.getRemoteAddr());
        // 打印请求入参
        log.info("Request Args   : {}", joinPoint.getArgs());

    }

    @Around("pointcut()")
    public Object around( ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

//        System.out.println("--"+proceedingJoinPoint.getArgs());

        log.info("proceed Args   : {}", proceedingJoinPoint.proceed());
        if (proceedingJoinPoint.proceed() instanceof Result){
            System.out.println(true);
        }


        Result result = (Result) proceedingJoinPoint.proceed();

//        System.out.println("jsonObject"+jsonObject);
//        System.out.println(jsonObject.get("code"));
//        System.out.println(jsonObject.get("data"));
//        System.out.println(jsonObject.get("message"));

        result.setMessage("哈哈");
        System.out.println("---------------------------------------------------------------------------");
        JSON json = JSONUtil.parse(proceedingJoinPoint.proceed());
        System.out.println(json);
        return result;
    }


     /**
     * 获取切面注解的描述
     *
     * @param joinPoint 切点
     * @return 描述信息
     * @throws Exception
     */
    public String getAspectLogDescription(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        StringBuilder description = new StringBuilder("");
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description.append(method.getAnnotation(OperationLog.class).value());
                    break;
                }
            }
        }
        return description.toString();

    }


}
