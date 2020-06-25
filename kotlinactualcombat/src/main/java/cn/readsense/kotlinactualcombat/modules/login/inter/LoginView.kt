package cn.readsense.kotlinactualcombat.modules.login.inter

import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse

interface LoginView {

    fun onSuccess(successResponse: RegisterLoginResponse?)

    fun onFailure(errorMessage: String?)
}