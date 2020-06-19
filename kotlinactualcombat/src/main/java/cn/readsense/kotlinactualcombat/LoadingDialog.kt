package cn.readsense.kotlinactualcombat

import android.app.Dialog
import android.content.Context

/**
 *Author:qyg
 *DATE:2020/6/19 19:06
 *Description：
 **/
object LoadingDialog {

    private var dialog: Dialog? = null

    fun show(context: Context) {
        cancel()
        dialog = Dialog(context)
        dialog?.setContentView(R.layout.dialog_loading)
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.show()
    }

    fun cancel() {
        dialog?.dismiss()
    }
}