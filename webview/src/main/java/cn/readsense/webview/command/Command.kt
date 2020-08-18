package cn.readsense.webview.command


interface Command {
    fun name(): String
    fun execute(parameters: Map<*, *>)
}