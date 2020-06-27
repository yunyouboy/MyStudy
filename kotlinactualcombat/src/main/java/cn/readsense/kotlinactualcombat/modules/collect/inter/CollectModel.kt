package cn.readsense.kotlinactualcombat.modules.collect.inter

import cn.readsense.kotlinactualcombat.database.Student

interface CollectModel {
    fun requestInsert(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestUpdate(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestDelete(listener: CollectPresenter.OnCollectListener, vararg students: Student)
    fun requestDeleteAll(listener: CollectPresenter.OnCollectListener)
    fun requestQueryAll(listener: CollectPresenter.OnCollectResponseListener)
}