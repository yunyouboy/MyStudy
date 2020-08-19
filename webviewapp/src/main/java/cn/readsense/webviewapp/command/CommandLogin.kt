package cn.readsense.webviewapp.command

import android.os.Handler
import android.os.Looper
import android.util.Log
import cn.readsense.webview.ICallbackFromMainprocessToWebViewProcessInterface
import cn.readsense.webview.command.Command
import cn.readsense.webviewbase.autoservice.WebViewServiceLoader
import cn.readsense.webviewcommon.autoservice.IUserCenterService
import com.google.auto.service.AutoService
import com.google.gson.Gson
import eventbus.LoginEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import java.util.*

/**
 *Author:qyg
 *DATE:2020/8/18 11:48
 *Description：
 **/
@AutoService(Command::class)
class CommandLogin : Command {

    var callback: ICallbackFromMainprocessToWebViewProcessInterface? = null
    var callbackNameFromNativeJs: String? = null

    init {
        EventBus.getDefault().register(this@CommandLogin)
    }

    override fun name(): String = "login"

    override fun execute(parameters: Map<*, *>, callback: ICallbackFromMainprocessToWebViewProcessInterface) {
        val handler = Handler(Looper.getMainLooper())
        handler.post {
            WebViewServiceLoader.load(IUserCenterService::class.java)?.run {
                if (!isLogined()) {
                    login()
                    this@CommandLogin.callback = callback
                    this@CommandLogin.callbackNameFromNativeJs = parameters["callbackname"].toString()
                }
            }
        }
    }

    @Subscribe
    fun onMessageEvent(event: LoginEvent) {
        Log.d("CommandLogin", event.userName)
        val map = HashMap<Any, Any>()
        map["accountName"] = event.userName
        callback?.run {
            onResult(callbackNameFromNativeJs, Gson().toJson(map))
        }
    }
}