package cn.readsense.kotlinbase.sample01.s05

// TODO 区间
fun main() {

    // 1 到 9
    for (i in 1..9) {
        // println(i)
    }

    // 不会输出
    for (i in 9..1) {
        // println(i)
    }

    // 大 到 小
    for (i in 9 downTo 1) {
        // println(i)
    }

    // 用区间做判断
    val value = 88
        if (value in 1..100) {
        // println("包了 1 到 100")
    }

    // 步长指定
    for (i in 1..20 step 2) {
        // 1 3 5 7 ...
        // println(i)
    }

    // 排除 最后元素
    for (i in 1 until 10) {
        println(i)
    }
}