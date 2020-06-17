package cn.readsense.kotlinbase.sample05.s04

class Test {

    val I = "AAAA"

    // 这个不是一个内部类，所有拿不到外部类的成员
    // 嵌套类 = 可以在类的里面再写一个类，但是这个类和外部类 不交换
    class Sub {

        fun show() {
            println()
        }


        class A {


            class B {


                class C {

                }

            }

        }

    }

    // 这个才是内部类
    inner class Sub2 {

        fun show() {
            println(I)
        }

    }

}

fun main() {
    fun a() {

        fun b() {

            fun c() {

                fun d() {

                }

            }

        }

    }
}
