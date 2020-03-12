package com.example.demo.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Title: TestCallable
 *
 * @author Shinelon
 * @version V1.0
 * @Description:   一、 创建执行线程的方式三： 实现Callable接口。相较于实现Runnable 接口的方式，方法可以有返回值，并且可以抛出异常
 *
 *                  二、 执行 Callbale 方式，需要 FutureTask 实现类的支持，用于接收运算结果。FutureTask 是 Future 接口的实现类
 *
 * @date 2019/5/27 16:38
 */
public class TestCallable {

    public static void main(String[] args) {
        ThreadDemo td = new ThreadDemo();

        //1、执行 Callbale 方式，需要 FutureTask 实现类的支持，用于接收运算结果
        FutureTask<Integer> result = new FutureTask<>(td);

        new Thread(result).start();

        //2、接收线程运算后的接口
        try {
            // FutureTask 也可用于闭锁
            Integer sum = result.get();
            System.out.println(sum);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

class ThreadDemo implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 101; i++) {
            sum+=i;
        }
        return sum;
    }
}

/*
class ThreadDemo implements Runnable {

    @Override
    public void run() {

    }
}*/
