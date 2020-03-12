package com.example.demo.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Title: TestABCAlternate
 *
 * @author Shinelon
 * @version V1.0
 * @Description: 编写一个程序，开启3个线程，这三个线程的ID 分别为 A、B、C，每个线程将自己的ID 在屏幕上打印 10 遍，要求输出的结果必须按顺序显示。
 * 如： ABCABCABC-----
  * @date 2019/5/28 9:00
 */
public class TestABCAlternate {
    public static void main(String[] args) {
        AlternateDemo ad = new AlternateDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ad.loapA(i);
                }

            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ad.loapB(i);
                }

            }
        },"B").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <= 20; i++) {
                    ad.loapC(i);
                    System.out.println("------------");
                }

            }
        },"C").start();
    }
}

class AlternateDemo {
    //当前正在执行线程的标记
    private int number = 1;

    private Lock lock = new ReentrantLock();
    private Condition conditionA = lock.newCondition();
    private Condition conditionB = lock.newCondition();
    private Condition conditionC = lock.newCondition();

    /**
     *
     * @param totalLoap 循环第几轮
     */
    public void loapA(int totalLoap) {
        lock.lock();
        try {
            // 1 、判断
            if (1 != number) {
                conditionA.await();
            }

            // 2 、打印
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoap);
            }

            // 3、唤醒
            number = 2;
            conditionB.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     *
     * @param totalLoap 循环第几轮
     */
    public void loapB(int totalLoap) {
        lock.lock();
        try {
            // 1 、判断
            if (2 != number) {
                conditionB.await();
            }

            // 2 、打印
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoap);
            }

            // 3、唤醒
            number = 3;
            conditionC.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     *
     * @param totalLoap 循环第几轮
     */
    public void loapC(int totalLoap) {
        lock.lock();
        try {
            // 1 、判断
            if (3 != number) {
                conditionC.await();
            }

            // 2 、打印
            for (int i = 0; i < 1; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i + "\t" + totalLoap);
            }

            // 3、唤醒
            number = 1;
            conditionA.signal();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}