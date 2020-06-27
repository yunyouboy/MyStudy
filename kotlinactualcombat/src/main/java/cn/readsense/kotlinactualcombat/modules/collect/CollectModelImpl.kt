package cn.readsense.kotlinactualcombat.modules.collect

import cn.readsense.kotlinactualcombat.data_model.local.LocalRoomRequestManager
import cn.readsense.kotlinactualcombat.database.Student
import cn.readsense.kotlinactualcombat.modules.collect.inter.CollectModel
import cn.readsense.kotlinactualcombat.modules.collect.inter.CollectPresenter

class CollectModelImpl : CollectModel {

    override fun requestInsert(listener: CollectPresenter.OnCollectListener, vararg students: Student) {
        LocalRoomRequestManager.getInstance()?.insertStudents(*students)
        listener.showIUD(true)
    }

    override fun requestUpdate(listener: CollectPresenter.OnCollectListener, vararg students: Student) {
        LocalRoomRequestManager.getInstance()?.updateStudents(*students)
        listener.showIUD(true)
    }

    override fun requestDelete(listener: CollectPresenter.OnCollectListener, vararg students: Student) {
        LocalRoomRequestManager.getInstance()?.deleteStudents(*students)
        listener.showIUD(true)
    }

    override fun requestDeleteAll(listener: CollectPresenter.OnCollectListener) {
        LocalRoomRequestManager.getInstance()?.deleteAllStudent()
        listener.showIUD(true)
    }

    override fun requestQueryAll(listener: CollectPresenter.OnCollectResponseListener) {
        val queryAllStudent = LocalRoomRequestManager.getInstance()?.queryAllStudent()
        listener.showResultSuccess(queryAllStudent)
    }
}