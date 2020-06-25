package cn.readsense.kotlinactualcombat.modules.login.inter

import android.content.Context
import cn.readsense.kotlinactualcombat.base.IBasePresenter
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse

interface LoginPresenter : IBasePresenter {

    fun lognAction(context: Context, username: String, password: String)

    interface OnLoginListener {
        fun onSuccess(successResponse: RegisterLoginResponse?)

        fun onFailure(errorMessage: String?)
    }

}