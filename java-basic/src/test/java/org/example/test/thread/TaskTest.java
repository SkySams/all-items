package org.example.test.thread;

import org.example.pattern.thread.Task;
import org.junit.jupiter.api.Test;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.IntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author: zyh
 * @date: 2022/8/9
 */
public abstract class TaskTest <T extends Task>{

    private static final int TASK_COUNT = 128 * 1024;

    private static final int THREAD_COUNT =8;

    private final IntFunction<T> factory;

    private final int expectedExecutionTime;

    public TaskTest(final IntFunction<T> factory, final int expectedExecutionTime) {
        this.factory = factory;
        this.expectedExecutionTime = expectedExecutionTime;
    }

    @Test
    void testIdGeneration()throws Exception{
        assertTimeout(ofMillis(10000), () -> {
            ExecutorService service = Executors.newFixedThreadPool(THREAD_COUNT);

            List tasks = IntStream.range(0, TASK_COUNT)
                    .<Callable<Integer>>mapToObj(i -> () -> factory.apply(1).getId())
                    .collect(Collectors.toCollection(ArrayList::new));

            service.shutdownNow();

        });
    }



    @Test
    void testTimeMs() {
        for (int i = 0; i < 10; i++) {
            assertEquals(this.expectedExecutionTime * i, this.factory.apply(i).getTimeMs());
        }
    }

    @Test
    void testToString() {
        assertNotNull(this.factory.apply(0).toString());
    }


}
