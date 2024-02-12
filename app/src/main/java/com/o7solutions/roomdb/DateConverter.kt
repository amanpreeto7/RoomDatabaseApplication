package com.o7solutions.roomdb

import androidx.room.TypeConverter
import java.util.Date

/**
 * @Author: 017
 * @Date: 12/02/24
 * @Time: 11:35 am
 */
class DateConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}