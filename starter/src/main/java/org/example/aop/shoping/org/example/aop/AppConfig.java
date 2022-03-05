package org.example.aop.shoping.org.example.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: zyh
 * @date: 2022/3/5
 */
@Configuration
@ComponentScan(basePackages={"org.example.aop"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {
}