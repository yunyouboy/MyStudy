package cn.readsense.kotlinactualcombat.modules.register.inter

import android.content.Context
import cn.readsense.kotlinactualcombat.base.IBasePresenter
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse

interface RegisterPresenter : IBasePresenter {
    fun registerAction(context: Context, username: String, password: String, rePassword: String)

    interface OnRegisterListener {
        fun onSuccess(registerLoginResponse: RegisterLoginResponse?)

        fun onFailure(errorMsg: String?)
    }
}