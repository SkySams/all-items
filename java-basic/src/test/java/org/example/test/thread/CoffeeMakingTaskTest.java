package org.example.test.thread;

import org.example.pattern.thread.CoffeeMakingTask;

import java.util.function.IntFunction;

/**
 * @author: zyh
 * @date: 2022/8/9
 */
public class CoffeeMakingTaskTest extends TaskTest<CoffeeMakingTask>{

    public CoffeeMakingTaskTest(IntFunction<CoffeeMakingTask> factory, int expectedExecutionTime) {
        super(factory, expectedExecutionTime);
    }
}
