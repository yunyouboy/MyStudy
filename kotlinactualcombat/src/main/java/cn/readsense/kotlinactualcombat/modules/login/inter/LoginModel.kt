package cn.readsense.kotlinactualcombat.modules.login.inter

import android.content.Context

interface LoginModel {

    fun cancelRequest()
    fun login(context: Context, username: String, password: String, listener: LoginPresenter.OnLoginListener)
}