package cn.readsense.kotlinbase.sample01.s02

// TODO 函数  方法

// void == :Unit
fun main(): Unit {

    println(add(1, 1))

    lenMethod(1, 2, 3, 4, 5, 6, 7)

    // lambda表达式函数
    val addMethod : (Int, Int) -> Int = {number1, number2 -> number1 + number2}
    val r= addMethod(9, 9)
    println(r)
}

// 返回类型Int
fun add(number1: Int, number2: Int): Int {
    return number1 + number2
}

// 返回类型  == 类型推导 Int
fun add2(number1: Int, number2: Int) = number1 + number2

// 返回类型  == 类型推导 String
fun add3(number1: Int, number2: Int) = "AAA"

// 可变参数 （可变长 参数函数）
fun lenMethod(vararg value: Int) {
    for (i in value) {
        println(i)
    }
}







