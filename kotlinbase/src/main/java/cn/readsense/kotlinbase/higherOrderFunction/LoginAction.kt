// TODO 2

package cn.readsense.kotlinbase.higherOrderFunction

fun main() {
    /*// 用户
    // 再次模拟登录流程
    loginEngine("Derry", "123456") {
        if (it) println("最终得到的结果是 登录成功") else println("最终得到的结果是 登录失败")
    }

    // 有返回值
    val r = loginTest() {
        true
    }
    println("方法的结果：${r}")*/

    val result = myLoginEngine("qyg", "123456") {
        if (it) println("登录成功") else println("登录失败")

    }
    println(result)
}

private fun myLoginEngine(username: String, password: String, mYresponse: (Boolean) -> Unit): String {
    val DB_USER_NAME = "qyg"
    val DB_USER_PWD = "123456"
    if (username == DB_USER_NAME && password == DB_USER_PWD) {
        mYresponse(true)
    } else {
        mYresponse(false)
    }
    return "qyg登录"
}

/*public fun login(userName: String, userPwd: String,      responseResult: (Boolean)-> Unit) {
    loginEngine(userName, userPwd, responseResult)
}*/

// 内部去完成登录功能
private fun loginEngine(userName: String, userPwd: String, responseResult: (Boolean) -> Unit) {
    val DB_USER_NAME = "Derry"
    val DB_USER_PWD = "123456"

    if (userName == DB_USER_NAME && userPwd == DB_USER_PWD) {
        // TODO 模拟做了很多业务逻辑
        //  ......
        responseResult(true)
    } else {
        // TODO 模拟做了很多业务逻辑
        //  ......
        responseResult(false)
    }
}

fun loginTest(mm: () -> Boolean): Int {
    val result = mm()
    println("result:$result")

    return 99999
}

