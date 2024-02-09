package com.o7solutions.roomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

/**
 * @Author: 017
 * @Date: 08/02/24
 * @Time: 12:10 pm
 */
@Dao
interface ToDoDao {
    @Insert
    fun insertTodo(todoEntity: TodoEntity)

    @Query("Select * from TodoEntity")
    fun getTodoEntities() : List<TodoEntity>

    @Update
    fun updateTodoEntity(todoEntity: TodoEntity)

    @Delete
    fun deleteTodoEntity(todoEntity: TodoEntity)
}