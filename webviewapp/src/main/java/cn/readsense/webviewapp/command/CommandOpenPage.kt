package cn.readsense.webviewapp.command

import android.content.ComponentName
import android.content.Intent
import cn.readsense.webview.ICallbackFromMainprocessToWebViewProcessInterface
import cn.readsense.webview.command.Command
import cn.readsense.webviewbase.app.BaseApplication
import com.google.auto.service.AutoService

/**
 *Author:qyg
 *DATE:2020/8/18 10:21
 *Description：
 **/
@AutoService(Command::class)
class CommandOpenPage : Command {
    override fun name(): String = "openPage"

    override fun execute(parameters: Map<*, *>, callback: ICallbackFromMainprocessToWebViewProcessInterface) {
        val targetClass = parameters["target_class"].toString()
        if (targetClass.isNullOrEmpty()) return
        val intent = Intent()
        intent.run {
            component = ComponentName(BaseApplication.myApplication, targetClass)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        BaseApplication.myApplication.startActivity(intent)
    }
}