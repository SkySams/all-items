package org.example.service.async;

import java.util.concurrent.Future;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
public interface AsyncService {

    void wocao();

    Future<String> asyncMethod();

}
