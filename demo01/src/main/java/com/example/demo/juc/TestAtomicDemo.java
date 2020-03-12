package com.example.demo.juc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Title: TestAtomicDemo
 *
 * @author Shinelon
 * @version V1.0
 * @Description:  一、 i++的原子性问题   操作实际上分三个步骤：“读-改-写”
 *
 *                原子变量:  原子变量保证了该变量的所有操作都是原子的，不会因为多线程的同时访问而导致脏数据的读取问题
 *               二、  原子变量：jdk1.5 以后 java.util.concurrent.atomic 包提供了常用的原子变量
 *                   1、volatile 保证内存可见性
 *                   2、CAS （Compare-And-Swap）算法保证数据的原子性
 *                   CAS 算法是硬件对于并发操作共享数据的支持
 *                   CAS 包含三个操作数：
 *
 *                   内存值 V
 *                   预估值 A
 *                   更新值 B
 *                   当且仅当 V == A 时， V = B ；否则将不做任何操作
 *
 *
 * @date 2019/5/27 13:37
 */
public class TestAtomicDemo {

    public static void main(String[] args) {
        AtomicDemo ad = new AtomicDemo();

        for (int i = 0; i < 100; i++) {
            new Thread(ad).start();
        }

    }

}

class AtomicDemo implements Runnable {

//    private volatile int serialNumber = 0;

    private AtomicInteger serialNumber = new AtomicInteger();

    @Override
    public void run() {

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(getSerialNumber());
    }
    //先加后增
    public int getSerialNumber() {
        return serialNumber.getAndIncrement();
    }

}
