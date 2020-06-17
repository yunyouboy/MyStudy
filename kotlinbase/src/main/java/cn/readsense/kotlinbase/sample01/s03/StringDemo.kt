package cn.readsense.kotlinbase.sample01.s03

// TODO 字符串模板
fun main() {

    // $ 表示一个变量名或者变量值

    // $varName 表示变量值

    // ${varName.fun()} 表示变量的方法返回值

    val name = "张三"

    val age = 28

    val sex = 'M'

    val info = "ABCDEFG"

    println("name:$name,  age:$age,  sex:$sex  info:$info")

    // --- 自己不用关系 \n 换行 ，不用自己调整
    val infoMesage = """
        AAAAAAAAAAA
        BBBBBBBBBBB
        CCCCCCCCCCC
        DDDDDDDDDDD
        EEEEEEEEEEE
    """  // 前置空格
    println(infoMesage)

    val infoMesage2 = """
        AAAAAAAAAAA
        BBBBBBBBBBB
        CCCCCCCCCCC
        DDDDDDDDDDD
        EEEEEEEEEEE
    """.trimIndent()  // 没空格
    println(infoMesage2)

    val infoMesage3 = """
        ?AAAAAAAAAAA
        ?BBBBBBBBBBB
        ?CCCCCCCCCCC
        ?DDDDDDDDDDD
        ?EEEEEEEEEEE
    """.trimMargin("?")  // 没空格 控制|
    println(infoMesage3)

    // 需求：显示 $99999.99
    val price = """
        ${'$'}99999.99
    """.trimIndent()
    println(price)
}