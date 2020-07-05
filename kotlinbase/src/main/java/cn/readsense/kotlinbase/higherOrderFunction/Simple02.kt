// TODO 最后看

package cn.readsense.kotlinbase.higherOrderFunction

val name: String = "Derry"
val age: Int = 0

fun common() {
    println("我就是方法")
}

fun main() {

    name.let {
        //it.length
        println(it.length)
        it.hashCode()
    }

    // r == 外面那个R
    val r = common().myRun {
        println("AAAA")
        true
        433535.45345

        '男'
        "AAAA"  // == 里面的R泛型
    }

    myWith(name) {
        length
    }

    name.myLet {
        val aaa = length
        length
        println("name length:$length")
    }

    onRun(true) {
        println("执行了..")
    }

    onRun(false) {
        println("执行了222")
    }

    val runValue = Runnable {
        println("我就是Runnabler任务")
    }

    onRun(true, runValue::run)
}

// TODO m: T.() -> R
// T.() == 给T来一个匿名函数
fun <T, R> T.myRun(m: () -> R) : R  = m()  // 调用高阶函数

// 普通函数
fun <T, R> myWith(input:T, mm: T.(Int) -> R): R {
    return input.mm(1) // this
}

fun <T, R> T.myLet(mm: T.(T) -> R): R {
    // T == this   () -> R
    // mm(this) == this     vs    T.(T)  -> R
    return mm(this)
}

// 控制器 如果你是true，我就执行你，否则不执行
inline fun onRun(isRun: Boolean,        mm: () -> Unit) {
    if (isRun) mm()
}
