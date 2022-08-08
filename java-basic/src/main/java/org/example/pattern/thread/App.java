package org.example.pattern.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
@Slf4j
public class App {

    public static void main(String[] args) {
        System.out.println("Program started");
        List<Task> tasks = Arrays.asList(
                new PotatoPeelingTask(3)
//                new PotatoPeelingTask(6),
//                new CoffeeMakingTask(2),
//                new CoffeeMakingTask(6),
//                new PotatoPeelingTask(4),
//                new CoffeeMakingTask(2),
//                new PotatoPeelingTask(4),
//                new CoffeeMakingTask(9),
//                new PotatoPeelingTask(3),
//                new CoffeeMakingTask(2),
//                new PotatoPeelingTask(4),
//                new CoffeeMakingTask(2),
//                new CoffeeMakingTask(7),
//                new PotatoPeelingTask(4),
//                new PotatoPeelingTask(5)
        );

//        ExecutorService executor = Executors.newFixedThreadPool(3);
        ExecutorService executor = create(3);
        tasks.stream().map(Worker::new).forEach(executor::execute);
        executor.shutdown();
        while (!executor.isTerminated()) {
            Thread.yield();
        }
        System.out.println("Program finished");

    }

    public static ThreadPoolExecutor create(int nThreads){
        return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());
    }


}
