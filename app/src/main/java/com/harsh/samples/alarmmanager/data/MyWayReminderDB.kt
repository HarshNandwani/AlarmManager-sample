package com.harsh.samples.alarmmanager.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.harsh.samples.alarmmanager.data.dao.ReminderDao
import com.harsh.samples.alarmmanager.data.entity.ReminderEntity
import com.harsh.samples.alarmmanager.data.util.DateTypeConverter

@Database(
    entities = [ReminderEntity::class],
    version = 1
)
@TypeConverters(DateTypeConverter::class)
abstract class MyWayReminderDB : RoomDatabase() {
    abstract val reminderDao: ReminderDao

    companion object {
        const val DATABASE_NAME = "my_way_reminder_db"
    }
}
