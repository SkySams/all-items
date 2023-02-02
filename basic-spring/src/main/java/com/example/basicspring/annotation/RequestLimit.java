package com.example.basicspring.annotation;

import java.lang.annotation.*;

/**
 * 自定义限流注解
 * @author: zyh
 * @date: 2023/2/2
 */
@Inherited
@Documented
@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLimit {

    /**
     * 时间内  秒为单位
     */
    int second() default 10;


    /**
     *  允许访问次数
     */
    int maxCount() default 5;


}
