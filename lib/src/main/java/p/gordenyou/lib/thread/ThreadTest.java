package p.gordenyou.lib.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by GORDENyou on 2020/6/13.
 * mailbox:1193688859@qq.com
 * have nothing but……
 */

public class ThreadTest {
    // 其实我们知道安卓有三种开启线程的方式。

    private static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "Thread style1");
        }
    }

    private static Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread() + "Thread style2");
        }
    };

    private static Callable<String> myCall = new Callable<String>() {
        @Override
        public String call() throws Exception {
            return Thread.currentThread() + "Thread style3";
        }
    };


    public static void main(String[] args) throws Exception {
        MyThread myThread = new MyThread();
        myThread.start();

        new Thread(myRunnable).start();

        // 第三种方式是阻塞的，需要最后的返回值线程才会继续运行，AsyncTask 底层实现就是这个原理
        FutureTask<String> futureTask = new FutureTask<>(myCall);
        new Thread(futureTask).start();

        System.out.println(futureTask.get());
    }


}
