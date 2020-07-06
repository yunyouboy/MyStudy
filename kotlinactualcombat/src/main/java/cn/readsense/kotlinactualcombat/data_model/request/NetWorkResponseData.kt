package cn.readsense.kotlinactualcombat.data_model.request

import android.os.Handler
import android.os.Looper
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

/**
 *Author:qyg
 *DATE:2020/6/30 17:52
 *Description：
 **/
abstract class NetWorkResponseData :Callback {

    private val myHandler: Handler = Handler(Looper.getMainLooper())
    override fun onFailure(call: Call, e: IOException) {
        myHandler.post(Runnable {
            requestFaliure(e.message)
        })
    }


    override fun onResponse(call: Call, response: Response) {
        myHandler.post(Runnable {
            requestSuccess(response)
        })
    }

    abstract fun requestFaliure(message: String?)

    abstract fun requestSuccess(response: Response)

}