package cn.readsense.kotlinactualcombat.data_model.local

import cn.readsense.kotlinactualcombat.database.Student
import cn.readsense.kotlinactualcombat.database.StudentDao
import cn.readsense.kotlinactualcombat.database.StudentDataBase

class LocalRoomRequestManager : ILocalRequest, IDatabaseRequest {

    var studentDao: StudentDao? = null

    init {
        studentDao = StudentDataBase.getDataBase()?.getStudentDao()
    }

    companion object {
        private var INSTANCE: LocalRoomRequestManager? = null
        fun getInstance(): LocalRoomRequestManager? {
            if (null == INSTANCE) {
                synchronized(LocalRoomRequestManager::class.java) {
                    if (null == INSTANCE) {
                        INSTANCE = LocalRoomRequestManager()
                    }
                }
            }
            return INSTANCE!!
        }
    }

    override fun insertStudents(vararg students: Student) {
        studentDao?.insertStudents(*students)
    }

    override fun updateStudents(vararg students: Student) {
        studentDao?.updateStudents(*students)
    }

    override fun deleteStudents(vararg students: Student) {
        studentDao?.deleteStudents(*students)
    }

    override fun deleteAllStudent() {
        studentDao?.deleteAllStudents()
    }

    override fun queryAllStudent(): List<Student>? {
        return studentDao?.queryAllStudents()
    }
}