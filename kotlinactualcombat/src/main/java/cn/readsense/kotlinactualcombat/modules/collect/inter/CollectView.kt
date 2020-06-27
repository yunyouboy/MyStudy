package cn.readsense.kotlinactualcombat.modules.collect.inter

import cn.readsense.kotlinactualcombat.database.Student

interface CollectView {
    fun showResult(result: Boolean)
    fun showResultSuccess(result: List<Student>?)
}