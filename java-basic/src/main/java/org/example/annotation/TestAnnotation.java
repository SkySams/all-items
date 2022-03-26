package org.example.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author: zyh
 * @date: 2022/3/26

 元注解
 @Retention    保留（存在时间）；它说明注解的存活时间
   @RetentionPolicy.SOURCE 注解只在源代码保留，在编译器进行编译时它将被丢弃忽视
   @RetentionPolicy.CLASS  注解只被保留在编译代码中，它并不会被加载到jvm中
   @RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们，jvm控制它的生命周期
 @Documented  注解的作用是能够将注解中的元素包含到 Javadoc 中去。
 @Target
 @Inherited   该注解是否被集成
 @Repeatable  是否可重复

 */
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAnnotation {
}
