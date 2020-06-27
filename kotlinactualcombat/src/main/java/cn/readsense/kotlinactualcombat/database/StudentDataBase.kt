package cn.readsense.kotlinactualcombat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Student::class], version = 1)
abstract class StudentDataBase : RoomDatabase() {
    abstract fun getStudentDao(): StudentDao

    companion object {
        private var INSTANCE: StudentDataBase? = null

        fun getDataBase(context: Context): StudentDataBase? {
            if (null == INSTANCE) {
                INSTANCE = Room.databaseBuilder(context, StudentDataBase::class.java, "student_database.db")
                        .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE
        }

        fun getDataBase(): StudentDataBase? = INSTANCE
    }
}