package com.example.basicspring.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * @author: zyh
 * @date: 2023/2/1
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatDaMie {

    /**
     * 时间ms限制
     */
    int second() default 1;

    /**
     * 提示消息
     */
    String describe() default "重复提交了,兄弟";


}
