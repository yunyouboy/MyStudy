package cn.readsense.kotlinbase.sample05.s02

class Student : Person() {
    override fun getLayoutID(): Int = 888

    override fun initView() {}

    override fun callbackMethod(): Boolean = false

}