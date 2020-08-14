package cn.readsense.webviewbase.autoservice

import java.util.*

object WebViewServiceLoader {
    fun <S> load(service: Class<S>): S? {
        return try {
            ServiceLoader.load(service).iterator().next()
        } catch (e: Exception) {
            null
        }
    }
}