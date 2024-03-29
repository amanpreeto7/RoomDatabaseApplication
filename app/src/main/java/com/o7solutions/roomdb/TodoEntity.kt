package com.o7solutions.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Calendar
import java.util.Date

/**
 * @Author: 017
 * @Date: 08/02/24
 * @Time: 12:01 pm
 */
@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var todoItem: String ?= null,
    var date : Date ?= Calendar.getInstance().time
)