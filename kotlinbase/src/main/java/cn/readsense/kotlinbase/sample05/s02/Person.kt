package cn.readsense.kotlinbase.sample05.s02

import cn.readsense.kotlinbase.sample05.s02.Callback
import cn.readsense.kotlinbase.sample05.s02.Callback2

// 默认是 public final class Person 不能被人家继承的      abstract有open的特征 相当于open
abstract class Person : Callback, Callback2 {

    abstract fun getLayoutID() : Int

    abstract fun initView()

}