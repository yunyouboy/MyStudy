package cn.readsense.kotlinactualcombat.modules.collect

import cn.readsense.kotlinactualcombat.database.Student
import cn.readsense.kotlinactualcombat.modules.collect.inter.CollectModel
import cn.readsense.kotlinactualcombat.modules.collect.inter.CollectPresenter
import cn.readsense.kotlinactualcombat.modules.collect.inter.CollectView

class CollectPresenterImpl(var collectView: CollectView?) : CollectPresenter,
        CollectPresenter.OnCollectListener,
        CollectPresenter.OnCollectResponseListener {

    private val collectModel: CollectModel = CollectModelImpl()

    override fun requestInsert(vararg students: Student) {
        collectModel.requestInsert(this, *students)
    }

    override fun requestUpdate(vararg students: Student) {
        collectModel.requestUpdate(this, *students)
    }

    override fun requestDelete(vararg students: Student) {
        collectModel.requestDelete(this, *students)
    }

    override fun requestDeleteAll() {
        collectModel.requestDeleteAll(this)
    }

    override fun requestQueryAll() {
        collectModel.requestQueryAll(this)
    }

    override fun attachView() {
    }

    override fun unAttachView() {
        collectView = null
    }

    override fun showResultSuccess(result: List<Student>?) {
        collectView?.showResultSuccess(result)
    }

    override fun showIUD(iudResult: Boolean) {
        collectView?.showResult(iudResult)
    }
}