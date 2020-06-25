package cn.readsense.kotlinactualcombat.modules.register

import android.content.Context
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse
import cn.readsense.kotlinactualcombat.modules.register.inter.RegisterPresenter
import cn.readsense.kotlinactualcombat.modules.register.inter.RegisterView

class RegisterPresenterImpl(var registerView: RegisterView?) : RegisterPresenter, RegisterPresenter.OnRegisterListener {

    private val registerModel = RegisterModelImpl()

    override fun registerAction(context: Context, username: String, password: String, rePassword: String) {
        registerModel.regist(context, username, password, rePassword, this)
    }

    override fun attachView() {
        TODO("Not yet implemented")
    }

    override fun unAttachView() {
        registerView = null
        registerModel.cancleRequest()
    }

    override fun onSuccess(registerLoginResponse: RegisterLoginResponse?) {
        registerView?.onSuccess(registerLoginResponse)
    }

    override fun onFailure(errorMsg: String?) {
        registerView?.onFailure(errorMsg)
    }
}