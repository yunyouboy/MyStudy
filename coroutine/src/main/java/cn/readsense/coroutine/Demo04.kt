package cn.readsense.coroutine

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *Author:qyg
 *DATE:2020/7/2 17:37
 *Description：
 **/
fun main() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }
    coroutineScope { // 创建新的协程作用范围
        launch {
            delay(500L)
            println("Task from nested launch")
        }
        delay(100L)
        println("Task from coroutine scope") // 在嵌套的 launch 之前, 这一行会打印
    }
    println("Coroutine scope is over") // 直到嵌套的 launch 运行结束后, 这一行才会打印
}