package cn.readsense.kotlinbase.sample05.s04

// TODO　Kotlin 单例模式1
class NetManager {

    // 只有一个实例
    object Holder {

        val instance = NetManager()

    }

    // 看不到 static  可以 派生操作
    companion object {

        // 全部都是  相当于 Java static

        fun getInstance(): NetManager {
            return Holder.instance
        }
    }

    fun show(name: String) {
        println("show:$name");
    }

}

// 客户端
fun main() {
    val net = NetManager.getInstance()
    net.show("kt Derry1")
}