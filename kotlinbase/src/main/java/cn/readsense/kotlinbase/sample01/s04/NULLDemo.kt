package cn.readsense.kotlinbase.sample01.s04

// TODO NULL检查机制
fun main() {
    // Kotlin的空安全设计对于声明可为空的参数，在使用时要进行空判断处理，
    // 有两种处理方式，字段后加!!像Java一样抛出空异常，另一种字段后加?

    var info: String? = null

    // println(info?.length)  // 第一种补救：? 如果info是null，就不执行 .length

    // println(info!!.length) // 第2种补救： !! 我自己负责info 不会为null ==  (不管null不null必须执行)  ==  Java

    /*if (info != null)           // 第3种补救：  ==  Java
        println(info.length)*/

    // println(testMethod("AAAAA"))

    // 刚那个提问的呢?
    // ?:  如果你一定要输出null  就让你  “你很牛逼”
    println(info?.length           ?: "你很牛逼")
}

// : Int? === 允许返回null
fun testMethod(name: String) : Int? {
    if (name == "zs") {
        return 99999
    }
    return null
}