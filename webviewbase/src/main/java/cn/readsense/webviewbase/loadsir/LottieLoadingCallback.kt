package cn.readsense.webviewbase.loadsir

import android.content.Context
import android.view.View
import cn.readsense.webviewbase.R
import com.kingja.loadsir.callback.Callback

/**
 * Description:TODO
 * Create Time:2017/9/4 10:22
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
class LottieLoadingCallback : Callback() {
    override fun onCreateView(): Int {
        return R.layout.layout_lottie_loading
    }

    override fun onReloadEvent(context: Context, view: View): Boolean {
        return true
    }
}