package org.example.aop.shoping;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: zyh
 * @date: 2022/3/5
 */
@Aspect
@Component
public class BuyAspectJ {

    /**
     * 切点
     */
    @Pointcut("execution(* org.example.aop.shoping.IBuy.buy(double)) &&" +
            "args(prise) && bean(girl)")
    public void gif(double prise){
    }

    @Around("gif(price)")
    public String hehe(ProceedingJoinPoint pj, double price){
        try {
            pj.proceed();
            if (price > 68) {
                System.out.println("女孩买衣服超过了68元，赠送一双袜子");
                return "衣服和袜子";
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return "衣服";
    }

}
