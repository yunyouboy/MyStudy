package cn.readsense.coroutine

import kotlinx.coroutines.*

/**
 *Author:qyg
 *DATE:2020/7/3 10:52
 *Description：
 **/
fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            withContext(NonCancellable) {
                println("I'm running finally")
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L)
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消 job, 并等待它结束
    println("main: Now I can quit.")
}