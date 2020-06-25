package cn.readsense.kotlinactualcombat.base

interface IBasePresenter {

    fun attachView()

    //销毁时释放presenter持有的Activi？Fragment的引用，避免内存泄漏
    fun unAttachView()
}