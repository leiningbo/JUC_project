package com.example.demo.ThreadPool;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Title: TestScheduledThreadPool
 *
 * @author Shinelon
 * @version V1.0
 * @Description: 线程池   ：1、提供了一个线程队列，队列中保存着所有等待状态的线程。避免了创建与销毁额外开销，提高了响应速度。
 *  *
 *  *      2、线程池的体系结构：
 *  *      java.util.concurrent.Executor：负责线程的使用与调度的根接口
 *  *          ExecutorService 子接口：线程池的主要接口
 *  *              ThreadPoolExecutor ：实现类
 *  *              ScheduledExecutorService 子接口：负责线程的调度
 *  *                  ScheduledThreadPoolExecutor：继承ThreadPoolExecutor，实现ScheduledExecutorService
 *  *
 *  *      3、工具类：Executors
 *  *        ExecutorService  newFixedThreadPool() ：创建固定大小的线程池
 *  *        ExecutorService  newCachedThreadPool() ：缓存线程池，线程池的数量不固定，可以根据需求自动更改数据
 *  *        ExecutorService  newSingleThreadExecutor() ：创建单个线程池，线程池中只有一个线程
 *  *
 *  *        ScheduleExecutorService newScheduledThreadPool() ：创建固定大小的线程，可以延迟或定时的执行任务
 * @date 2019/5/28 17:45
 */
public class TestScheduledThreadPool {
    public static void main(String[] args)throws Exception {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);

        for (int i = 0; i < 5; i++) {
            Future<Integer> result = pool.schedule(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    int num = new Random().nextInt(100);
                    System.out.println(Thread.currentThread().getName() + " : " + num);
                    return num;
                }
            }, 1, TimeUnit.SECONDS);

            System.out.println(result.get());
        }
        pool.shutdown();

    }

}
