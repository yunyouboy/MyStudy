package cn.readsense.kotlinactualcombat.modules.login

import android.content.Context
import cn.readsense.kotlinactualcombat.api.WanAndroidAPI
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse
import cn.readsense.kotlinactualcombat.modules.login.inter.LoginModel
import cn.readsense.kotlinactualcombat.modules.login.inter.LoginPresenter
import cn.readsense.kotlinactualcombat.net.APIClient
import cn.readsense.kotlinactualcombat.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginModelImpl : LoginModel {
    override fun cancelRequest() {

    }

    override fun login(context: Context, username: String, password: String, listener: LoginPresenter.OnLoginListener) {
        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                .loginAction(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : APIResponse<RegisterLoginResponse>(context) {

                    override fun success(data: RegisterLoginResponse?) {
                        listener?.onSuccess(data)
                    }

                    override fun failure(errorMsg: String?) {
                        listener?.onFailure(errorMsg)
                    }

                })
    }
}