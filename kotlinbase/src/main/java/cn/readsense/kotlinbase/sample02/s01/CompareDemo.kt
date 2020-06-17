package cn.readsense.kotlinbase.sample02.s01

// TODO 比较两个值
fun main() {

    val name1: String = "张三"
    val name2: String = "张三"

    // --- 比较值本身
    // == 等价 Java的equals
    println(name1.equals(name2))
    println(name1 == name2)


    // ---  比较对象地址
    val test1:Int? =  10000
    val test2:Int? =  10000
    println(test1 === test2) // false
}