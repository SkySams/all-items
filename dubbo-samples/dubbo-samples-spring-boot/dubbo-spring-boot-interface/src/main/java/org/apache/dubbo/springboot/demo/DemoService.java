package org.apache.dubbo.springboot.demo;

import java.util.concurrent.CompletableFuture;

/**
 * @author: zyh
 * @date: 2022/10/12
 */
public interface DemoService {

    String sayHello(String name);

    default CompletableFuture<String> sayHelloAsync(String name) {
        return CompletableFuture.completedFuture(sayHello(name));
    }

}
