package cn.readsense.webviewapp.command

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import cn.readsense.webview.ICallbackFromMainprocessToWebViewProcessInterface
import cn.readsense.webview.command.Command
import cn.readsense.webviewbase.app.BaseApplication
import com.google.auto.service.AutoService

@AutoService(Command::class)
class CommandShowToast : Command {
    override fun name(): String {
        return "showToast"
    }

    override fun execute(parameters: Map<*, *>, callback: ICallbackFromMainprocessToWebViewProcessInterface) {
        val handler = Handler(Looper.getMainLooper())
        handler.post { Toast.makeText(BaseApplication.myApplication, parameters["message"].toString(), Toast.LENGTH_SHORT).show() }
    }
}