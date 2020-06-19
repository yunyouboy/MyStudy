package cn.readsense.kotlinactualcombat.net

import android.content.Context
import cn.readsense.kotlinactualcombat.LoadingDialog
import cn.readsense.kotlinactualcombat.entity.LoginResponseWrapper
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 *Author:qyg
 *DATE:2020/6/19 18:38
 *Description：
 **/

abstract class APIResponse<T>(val context: Context) : Observer<LoginResponseWrapper<T>> {

    private var isShow: Boolean = true

    constructor(context: Context, isShow: Boolean) : this(context) {
        this.isShow = isShow;
    }

    override fun onSubscribe(d: Disposable) {
        // 弹出 加载框
        if (isShow) {
            LoadingDialog.show(context)
        }
    }

    override fun onNext(t: LoginResponseWrapper<T>) {
        if (null == t.data) {
            failure(t.errorMsg)
        } else {
            success(t.data)
        }
    }

    override fun onComplete() {
        // 取消加载
        LoadingDialog.cancel()
    }

    override fun onError(e: Throwable) {
        // 取消加载
        LoadingDialog.cancel()
        failure(e.message)
    }

    abstract fun success(data: T?)

    abstract fun failure(errorMsg: String?)
}