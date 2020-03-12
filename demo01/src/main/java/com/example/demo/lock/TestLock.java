package com.example.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: TestLock
 *
 * @author Shinelon
 * @version V1.0
 * @Description: 同步锁 ： 用于解决多线程安全问题的方式：
 *
 *                  synchronnized：隐式锁
 *                  1、同步代码块
 *                  2、同步方法
 *          JDK 1.5 以后
 *          3、同步锁 lock
 *          注意：是一个显示锁，需要通过lock()方法上锁，必须通过unlock()方法进行释放锁
 *
 *
 * @date 2019/5/27 17:07
 */
public class TestLock {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(ticket,"1号窗口").start();
        new Thread(ticket,"2号窗口").start();
        new Thread(ticket,"3号窗口").start();
    }
}

class Ticket implements Runnable {

    private int tick = 100;

    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            //上锁
            lock.lock();
            try {
                if (tick > 0) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                    }
                    System.out.println(Thread.currentThread().getName()+" 完成售票，余票为："+ --tick);
                }
            }finally {
                //释放锁
                lock.unlock();
            }
        }

        /*//synchronize 同步
        while (true) {
            synchronized (this) {
                if (tick > 0) {
                    try {
                        Thread.sleep(20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" 完成售票，余票为："+ --tick);
                }
            }
        }*/

    }
}
