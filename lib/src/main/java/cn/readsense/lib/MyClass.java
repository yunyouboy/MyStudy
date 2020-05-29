package cn.readsense.lib;

import sun.rmi.runtime.Log;

public class MyClass {


    private void test(){
        MyThread thread1 = new MyThread("thread1",Thread.currentThread());
        MyThread thread2 = new MyThread("thread2",thread1);
        MyThread thread3 = new MyThread("thread3",thread2);
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(Thread.currentThread().getName());
    }



    private static class MyThread extends Thread {

        Thread nextThread;

        public MyThread(String name,Thread nextThread) {
            super(name);
            this.nextThread = nextThread;
        }

        @Override
        public void run() {
            try {
                nextThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        }
    }
}
