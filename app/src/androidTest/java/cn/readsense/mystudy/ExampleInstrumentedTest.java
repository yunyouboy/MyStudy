package cn.readsense.mystudy;

import android.util.Log;

import androidx.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    public static String TAG = "ThreadTest";

    @Test
    public void test() {
        Log.e(TAG, "-----------" + Thread.currentThread().getName() + "-----------");
        Thread thread1 = new Thread(new JoinA());
        thread1.start();
        Thread thread2 = new Thread(new JoinB(thread1));
        thread2.start();
        try {
            Thread.sleep(5*10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "+++++++++++" + Thread.currentThread().getName() + "+++++++++++");
    }


    class JoinA implements Runnable {

        @Override
        public void run() {
            Log.e(TAG, "thread1 线程启动");
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.e(TAG, "thread1 -> " + i);
            }
        }
    }


    class JoinB implements Runnable {
        private Thread thread1;

        public JoinB(Thread thread1) {
            this.thread1 = thread1;
        }

        @Override
        public void run() {
            try {
                Log.e(TAG, "thread2 线程启动");
                Log.e(TAG, "thread2 调用 thread1.join 方法， 并等待 thread1 执行结束后执行");
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < 10; i++) {
                Log.e(TAG, "thread2 -> " + i);
            }
        }

    }
}
