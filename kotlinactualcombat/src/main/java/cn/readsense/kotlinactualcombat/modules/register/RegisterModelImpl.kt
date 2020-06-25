package cn.readsense.kotlinactualcombat.modules.register

import android.content.Context
import cn.readsense.kotlinactualcombat.api.WanAndroidAPI
import cn.readsense.kotlinactualcombat.entity.RegisterLoginResponse
import cn.readsense.kotlinactualcombat.modules.register.inter.RegisterModel
import cn.readsense.kotlinactualcombat.modules.register.inter.RegisterPresenter
import cn.readsense.kotlinactualcombat.net.APIClient
import cn.readsense.kotlinactualcombat.net.APIResponse
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RegisterModelImpl : RegisterModel {
    override fun cancleRequest() {

    }

    override fun regist(context: Context, username: String, password: String, rePassword: String, listener: RegisterPresenter.OnRegisterListener) {
        APIClient.instance.instanceRetrofit(WanAndroidAPI::class.java)
                .registerAction(username, password, rePassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : APIResponse<RegisterLoginResponse>(context) {
                    override fun success(data: RegisterLoginResponse?) {
                        listener.onSuccess(data)
                    }

                    override fun failure(errorMsg: String?) {
                        listener.onFailure(errorMsg)
                    }

                })
    }
}