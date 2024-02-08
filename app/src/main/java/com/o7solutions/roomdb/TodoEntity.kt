package com.o7solutions.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @Author: 017
 * @Date: 08/02/24
 * @Time: 12:01 pm
 */
@Entity
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,
    var todo: String ?= null
)