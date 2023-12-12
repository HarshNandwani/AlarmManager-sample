package com.harsh.samples.alarmmanager.data.util

import androidx.room.TypeConverter
import java.util.Date

class DateTypeConverter {

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toDate(millis: Long): Date {
        return Date(millis)
    }
}
