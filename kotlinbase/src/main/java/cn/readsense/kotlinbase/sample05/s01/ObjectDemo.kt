package cn.readsense.kotlinbase.sample05.s01

// TODO 类与对象
fun main() {

    val person = Person() // 次构造
    val person1 = Person(1,"aaa", 'm')

    val person2 = Person(6465) // 主构造


    Person(464, "Derry") // 次构造
    Person(464, 'M') // 次构造
}