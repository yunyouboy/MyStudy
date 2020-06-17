package simple05.s03

// TODO  data数据类 ，object单例
fun main() {

    val user = User(99, "lisi", 'M')

    val(myID, myName, mySex) = user.copy()
    println("myID:$myID, myName:$myName, mySex:$mySex")

    // _ 代表不接收
    val(_, myName2, _) = user.copy()
    println("myName2:$myName2")

    // --- 相当于 new 了五次
    MyEngine.m()
    MyEngine.m()
    MyEngine.m()
    MyEngine.m()
    MyEngine.m()
}