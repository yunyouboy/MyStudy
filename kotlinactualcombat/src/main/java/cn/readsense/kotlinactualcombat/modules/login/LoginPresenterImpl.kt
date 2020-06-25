package cn.readsense.kotlinactualcombat.modules.login

import android.content.Context
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse
import cn.readsense.kotlinactualcombat.modules.login.inter.LoginPresenter
import cn.readsense.kotlinactualcombat.modules.login.inter.LoginView

class LoginPresenterImpl(var loginView: LoginView?) : LoginPresenter, LoginPresenter.OnLoginListener {

    private val loginModel: LoginModelImpl = LoginModelImpl()

    override fun lognAction(context: Context, username: String, password: String) {
        loginModel.login(context, username, password, this)
    }

    override fun onSuccess(successResponse: RegisterLoginResponse?) {
        loginView?.onSuccess(successResponse)
    }

    override fun onFailure(errorMessage: String?) {
        loginView?.onFailure(errorMessage)
    }

    override fun attachView() {
        TODO("Not yet implemented")
    }

    override fun unAttachView() {
        loginView = null
        loginModel.cancelRequest()
    }
}