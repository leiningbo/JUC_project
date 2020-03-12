package com.example.demo.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Title: TestReadWriterLock
 *
 * @author Shinelon
 * @version V1.0
 * @Description: 读写锁
 *
 * 写写/读写 需要“互斥”
 * 读读 不需要“互斥”
 *
 * @date 2019/5/28 9:23
 */
public class TestReadWriterLock {
    public static void main(String[] args) {
        ReadWriterLockDemo rw = new ReadWriterLockDemo();

        new Thread(new Runnable() {
            @Override
            public void run() {
                rw.set((int)(Math.random() * 101));
            }
        },"write:").start();

        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    rw.get();
                }
            }).start();
        }
    }
}

class ReadWriterLockDemo {

    private int number = 0;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() {
        //上锁
        lock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
        }finally {
            //释放锁
            lock.readLock().unlock();
        }
    }

    public void set(int number) {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " : " + number);
            this.number = number;
        }finally {
            lock.writeLock().unlock();
        }
    }

}
