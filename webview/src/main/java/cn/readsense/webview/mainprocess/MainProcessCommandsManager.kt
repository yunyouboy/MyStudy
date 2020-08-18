package cn.readsense.webview.mainprocess

import cn.readsense.webview.IWebViewProcessToMainProcessInterface
import cn.readsense.webview.command.Command
import com.google.gson.Gson
import java.util.*

class MainProcessCommandsManager private constructor() : IWebViewProcessToMainProcessInterface.Stub() {

    companion object {
        private var sInstance: MainProcessCommandsManager? = null
        fun getInstance(): MainProcessCommandsManager {
            if (sInstance == null) {
                synchronized(MainProcessCommandsManager::class.java) {
                    sInstance = MainProcessCommandsManager()
                }
            }
            return sInstance!!
        }
    }

    private val mCommands: HashMap<String, Command> = HashMap<String, Command>()

    init {
        val serviceLoader: ServiceLoader<Command> = ServiceLoader.load(Command::class.java)
        for (command in serviceLoader) {
            if (!mCommands.containsKey(command.name())) mCommands[command.name()] = command
        }
    }

    private fun executeCommand(commandName: String, params: Map<*, *>) {
        mCommands[commandName]?.execute(params)
    }

    override fun handleWebCommand(commandName: String, jsonParams: String) {
        getInstance().executeCommand(commandName, Gson().fromJson<Map<*, *>>(jsonParams, MutableMap::class.java))
    }
}