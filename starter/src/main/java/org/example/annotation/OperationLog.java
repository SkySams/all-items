package org.example.annotation;

import java.lang.annotation.*;

/**
 * @author: zyh
 * @date: 2022/5/30
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationLog {

    String value() default "";

}
