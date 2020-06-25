package cn.readsense.kotlinactualcombat.modules.register.inter

import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse

interface RegisterView {
    fun onSuccess(registerLoginResponse: RegisterLoginResponse?)

    fun onFailure(errorMsg: String?)
}