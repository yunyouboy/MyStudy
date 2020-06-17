package cn.readsense.kotlinbase.sample05.s01

// public final class Person 默认就是这样的，不能被继承，  可以加open就可以被人家继承了
open class Person(id: Int) // 主构造
{

    // 次构造
    constructor(id: Int, name: String) : this(id) {

    }

    // 次构造
    constructor(id: Int, sex: Char) : this(id) {

    }

    // 次构造
    constructor() : this(787) {

    }

    // 次构造
    constructor(id: Int, name: String, sex: Char) : this(id,name){

    }

}