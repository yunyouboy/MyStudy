package cn.readsense.kotlinactualcombat

import android.app.Application
import android.util.Log
import cn.readsense.kotlinactualcombat.database.StudentDataBase
import com.xiangxue.kotlinproject.config.Flag

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.e(Flag.TAG, "MyApplication onCreate run")
        StudentDataBase.getDataBase(this@MyApplication)
    }
}