package cn.readsense.kotlinbase.sample04

// TODO 循环 与 标签
fun main() {

    // 自定义标签
    tttt@ for (i in 1..20) {

        for (j in 1..20) {
            println("i:$i, j:$j")

            if (i == 5) {
                // break // j循环 给break

                break@tttt // i循环 给break
            }
        }

    }


    // TODO 循环
    var items  = listOf<String>("李四", "张三", "王五")
    for (item in items) {
        println(item)
    }

    items.forEach {
        println(it)
    }

    for (index in items.indices) {
        println("下标：$index,  对应的值：${items[index]}")
    }

}

// 自带的标签
class Derry {

    val I = "AAAA"

    fun show() {
        println(I)
        println(this.I)
        println(this@Derry.I)
    }

}