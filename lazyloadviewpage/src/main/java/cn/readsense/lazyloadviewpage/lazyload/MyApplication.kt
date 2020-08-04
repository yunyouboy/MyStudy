package cn.readsense.lazyloadviewpage.lazyload

import android.app.Application
import android.util.Log
import com.bumptech.glide.Glide

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread(Runnable {
            var time = System.currentTimeMillis()
            Log.d(TAG, "run1: " + System.currentTimeMillis())
            Glide.get(applicationContext)
            Log.d(TAG, "run2: " + (System.currentTimeMillis() - time))
            Log.d(TAG, "run1: " + System.currentTimeMillis())
            time = System.currentTimeMillis()
            Log.d(TAG, "run1: " + System.currentTimeMillis())
            Glide.get(applicationContext)
            Log.d(TAG, "run2: " + (System.currentTimeMillis() - time))
        }).start()
    }

    companion object {
        private const val TAG = "MyApplication"
    }
}