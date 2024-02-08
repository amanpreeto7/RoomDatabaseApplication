package com.o7solutions.roomdb

import androidx.room.Dao
import androidx.room.Insert

/**
 * @Author: 017
 * @Date: 08/02/24
 * @Time: 12:10 pm
 */
@Dao
interface ToDoDao {
    @Insert
    fun insertTodo(todoEntity: TodoEntity)
}