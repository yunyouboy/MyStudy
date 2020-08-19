package cn.readsense.webview.command

import cn.readsense.webview.ICallbackFromMainprocessToWebViewProcessInterface


interface Command {
    fun name(): String
    fun execute(parameters: Map<*, *>, callback: ICallbackFromMainprocessToWebViewProcessInterface)
}