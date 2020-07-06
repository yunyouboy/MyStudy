package cn.readsense.coroutine

import kotlinx.coroutines.*

/**
 *Author:qyg
 *DATE:2020/7/3 13:48
 *Description：
 **/
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val job = GlobalScope.launch(handler) {
        throw AssertionError()
    }
    val deferred = GlobalScope.async(handler) {
        throw ArithmeticException() // 这个异常不会被打印, 由使用者调用 deferred.await() 来得到并处理这个异常
    }
    joinAll(job, deferred)
}