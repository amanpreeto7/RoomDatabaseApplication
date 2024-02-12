package com.o7solutions.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * @Author: 017
 * @Date: 08/02/24
 * @Time: 11:59 am
 */

@Database(entities = [TodoEntity::class], version = 2)
@TypeConverters(DateConverter::class)
abstract class TodoDatabase  : RoomDatabase(){
    abstract fun todoDao() : ToDoDao
    companion object {
        var todoDatabase : TodoDatabase ?= null
        @Synchronized
        fun getDatabaseInstance(context: Context) : TodoDatabase {
            if(todoDatabase == null){
                todoDatabase = Room.databaseBuilder(context,
                    TodoDatabase::class.java,
                    context.resources.getString(R.string.app_name))
                    .allowMainThreadQueries()
                    .build()
            }
            return todoDatabase!!
        }
    }
}