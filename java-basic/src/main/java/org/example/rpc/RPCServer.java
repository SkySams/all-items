package org.example.rpc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: zyh
 * @date: 2023/1/4
 */
public class RPCServer {

    private static ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    
}
