package cn.readsense.kotlinactualcombat.modules.collect.inter

import cn.readsense.kotlinactualcombat.base.IBasePresenter
import cn.readsense.kotlinactualcombat.database.Student

interface CollectPresenter : IBasePresenter {

    // ← 请求去
    fun requestInsert(vararg students: Student)
    fun requestUpdate(vararg students: Student)
    fun requestDelete(vararg students: Student)
    fun requestDeleteAll() // 删除全部
    fun requestQueryAll() // 查询全部

    // → 回来处
    interface OnCollectResponseListener {
        fun showResultSuccess(result: List<Student>?)
    }

    // → 回来处
    interface OnCollectListener {
        fun showIUD(iudResult: Boolean)
    }
}