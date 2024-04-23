package com.example.picobotella.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.picobotella.model.Reto
import com.example.picobotella.utils.Constants.NAME_DB

@Database(entities = [Reto:: class], version = 1, exportSchema = false)
abstract class DBReto: RoomDatabase() {
    abstract fun daoReto(): DaoReto

    companion object {
        @Volatile
        private var INSTANCE: DBReto? = null

        fun getDataBase(context: Context): DBReto {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DBReto::class.java,
                    NAME_DB
                )
                    //.createFromAsset("database/${NAME_DB}")
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}