package cn.readsense.kotlinactualcombat.data_model.request

import com.xiangxue.kotlinproject.config.Flag
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request

/**
 *Author:qyg
 *DATE:2020/6/30 18:40
 *Description：
 **/
object RequestAPI : IRequest {
    override fun requesstAction(url: String, value: String, responseData: NetWorkResponseData) {
        commonRequestAction(url, responseData, value)
    }

    override fun requesstAction(url: String, value1: String, value2: String, responseData: NetWorkResponseData) {
        commonRequestAction(url, responseData, value1, value2)
    }

    override fun requesstAction(url: String, value1: String, value2: String, value3: String, responseData: NetWorkResponseData) {
        commonRequestAction(url, responseData, value1, value2, value3)
    }

    override fun requesstAction(url: String, map: HashMap<String, String>, responseData: NetWorkResponseData) {
        //commonRequestAction(url,responseData)
    }

    private fun commonRequestAction(url: String, responseData: NetWorkResponseData, vararg values: String) {
        // 1.创建一个OkhttpClient对象
        val okHttpClient: OkHttpClient = OkHttpClient()

        // 2.构建参数的body  MultipartBody.FORM 表单形式
        val builder: MultipartBody.Builder = MultipartBody.Builder().setType(MultipartBody.FORM)
        for (value in values) {
            builder.addFormDataPart(Flag.PART, value)
        }

        // 3.构建一个请求  post 提交里面是参数的builder   url()请求路径
        var request = Request.Builder().url(url).post(builder.build()).build()

        // 4.发送一个请求  给服务器
        okHttpClient.newCall(request).enqueue(responseData)
    }

}