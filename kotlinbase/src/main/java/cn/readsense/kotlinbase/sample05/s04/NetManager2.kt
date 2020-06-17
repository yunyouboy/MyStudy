package cn.readsense.kotlinbase.sample05.s04

// TODO　kt 单例模式2
class NetManager2 {

    companion object {

        private var instance: NetManager2? = null

        // 返回值：允许你返回null
        fun getInstance(): NetManager2? {
            if (instance == null) {
                instance = NetManager2()
            }

            // 如果是null，也返回回去了
            return instance

            // 第二种补救： 我来负责 instance 肯定不为null
            // return instance!!
        }

    }


    fun show(name: String) {
        println("show:$name");
    }

}

fun main() {
    // 客户端 来做补救措施
    val netManager = NetManager2.getInstance()

    // 如果 netManager == null ，就不执行 .show("AAA")
    netManager?.show("AAA")
}