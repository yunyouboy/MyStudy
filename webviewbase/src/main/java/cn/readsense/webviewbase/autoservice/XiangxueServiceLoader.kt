package cn.readsense.webviewbase.autoservice

import java.util.*


object WebViewServiceLoader {
    fun <S> load(service: Class<S>): S? {
        val count = ServiceLoader.loadInstalled(service).count()
        ServiceLoader.load(service).iterator().run {
            return if (hasNext()) next() else null
        }
    }
}