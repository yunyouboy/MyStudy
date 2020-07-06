package cn.readsense.kotlinbase.collection

/**
 *Author:qyg
 *DATE:2020/7/2 15:50
 *Description：
 **/
fun main() { //sampleStart
    var fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits.filter {
        it.startsWith("a")
    }.sortedBy {
        it
    }.map {
        it.toUpperCase()
    }.forEach {
        println(it)
    }
} //sampleEnd }