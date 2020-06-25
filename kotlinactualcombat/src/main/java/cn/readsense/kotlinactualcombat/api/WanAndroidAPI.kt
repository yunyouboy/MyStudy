package cn.readsense.kotlinactualcombat.api

import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponseWrapper
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
    ): Observable<RegisterLoginResponseWrapper<RegisterLoginResponse>>

    /**
     * 注册API
     */
    @POST("/user/register")
    @FormUrlEncoded
    fun registerAction(@Field("username") username: String,
                       @Field("password") password: String,
                       @Field("repassword") rePassword: String
    ): Observable<RegisterLoginResponseWrapper<RegisterLoginResponse>>
}