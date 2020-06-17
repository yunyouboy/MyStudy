package cn.readsense.kotlinbase.sample05.s01

import cn.readsense.kotlinbase.sample05.s01.Person

class Student(id: Int) : // Person() // 次构造
                Person(id) // 主构造了
{
    // 再Kotlin 全部都是没有默认值的

    // 再Java 成员有默认值，但是方法内部没有默认值

    // lateinit 懒加载  没有赋值 就不能使用，否则报错
    lateinit var name : String
    var age: Int = 0
}