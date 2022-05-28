package org.example.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * @author: zyh
 * @date: 2022/5/28
 */
@Aspect
public class NotVeryUsefulAspect {

    @Pointcut("execution(* transfer(..))")
    private void anyOldTransfer() {}

}
