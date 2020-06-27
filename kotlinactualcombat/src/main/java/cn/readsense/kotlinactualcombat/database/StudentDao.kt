package cn.readsense.kotlinactualcombat.database

import androidx.room.*

@Dao
interface StudentDao {

    @Insert
    fun insertStudents(vararg students: Student)

    @Update
    fun updateStudents(vararg students: Student)

    //条件删除
    @Delete
    fun deleteStudents(vararg students: Student)

    //删除全部
    @Query("DELETE FROM student")
    fun deleteAllStudents()

    //查询全部
    @Query("SELECT * FROM student ORDER BY ID DESC")
    fun queryAllStudents(): List<Student>
}