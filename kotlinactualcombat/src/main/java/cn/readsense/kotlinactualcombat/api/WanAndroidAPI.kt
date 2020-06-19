package cn.readsense.kotlinactualcombat.api

import cn.readsense.kotlinactualcombat.entity.LoginResponse
import cn.readsense.kotlinactualcombat.entity.LoginResponseWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *Author:qyg
 *DATE:2020/6/19 17:38
 *Description：
 **/

// 客户端API 可以访问 服务器的API
interface WanAndroidAPI {
    /**
     * 登录API
     * username=Derry-vip&password=123456
     */
    @POST("/user/login")
    @FormUrlEncoded
    fun loginAction(@Field("username") username: String,
                    @Field("password") password: String
    ): Observable<LoginResponseWrapper<LoginResponse>>

    /**
     * 注册API
     */
}