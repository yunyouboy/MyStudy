package cn.readsense.coroutine

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//阻塞协程  阻塞我们的执行
fun main() :Unit = runBlocking {// 外协程

    //内协程，外协程等待内协程全部完成,才关闭
   launch {
        delay(1000)
        println("1111111111111")
    }

    //全局协程相当于java中守护线程，外协程完成，GlobalScope.launch随之关闭
    GlobalScope.launch {
        delay(2000)
        println("2222222222222")
    }
    // 阻塞式执行的
    println("A")

    // 阻塞式执行的
    delay(300)

    // 阻塞式执行的
    println("B")

    // main 结束
}