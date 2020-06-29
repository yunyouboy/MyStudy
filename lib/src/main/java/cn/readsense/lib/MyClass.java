package cn.readsense.lib;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

public class MyClass {

    public static void main(String[] args) {
        testParameterizedType();
    }

    private List<String> mmm = new ArrayList<>();
    private String[] aaa = {"aaa", "bbb"};
    private List<String>[] listArray = new ArrayList[]{new ArrayList<String>(), new ArrayList<String>()};

    public static void testParameterizedType() {
        try {
            Field mmm = MyClass.class.getDeclaredField("mmm");
            ParameterizedType genericType = (ParameterizedType) mmm.getGenericType();
            System.out.println(genericType.getActualTypeArguments()[0]);
            System.out.println(genericType.getRawType());
            System.out.println(genericType.getOwnerType());

            Field listArray = MyClass.class.getDeclaredField("listArray");
            GenericArrayType genericTypeListArray = (GenericArrayType) listArray.getGenericType();
            System.out.println(genericTypeListArray.getGenericComponentType());


        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }


    private void test() {
        MyThread thread1 = new MyThread("thread1", Thread.currentThread());
        MyThread thread2 = new MyThread("thread2", thread1);
        MyThread thread3 = new MyThread("thread3", thread2);
        thread1.start();
        thread2.start();
        thread3.start();
        System.out.println(Thread.currentThread().getName());
    }


    private static class MyThread extends Thread {

        Thread nextThread;

        public MyThread(String name, Thread nextThread) {
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
