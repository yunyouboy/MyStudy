package cn.readsense.kotlinactualcombat.modules.register.inter

import android.content.Context

interface RegisterModel {

    fun cancleRequest()

    fun regist(context: Context, username: String, password: String, rePassword: String, listener: RegisterPresenter.OnRegisterListener)
}