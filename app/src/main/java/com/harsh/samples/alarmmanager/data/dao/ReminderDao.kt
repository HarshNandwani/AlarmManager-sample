package com.harsh.samples.alarmmanager.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.harsh.samples.alarmmanager.data.entity.ReminderEntity

@Dao
interface ReminderDao {
    @Insert
    suspend fun add(reminder: ReminderEntity)

    @Query("SELECT * FROM ReminderEntity")
    suspend fun getAll(): List<ReminderEntity>

    @Query("SELECT * FROM ReminderEntity WHERE id=:id")
    suspend fun get(id: Int): ReminderEntity?

    @Update
    suspend fun update(reminder: ReminderEntity)

    @Delete
    suspend fun delete(reminder: ReminderEntity)

    @Query("UPDATE ReminderEntity SET isEnabled=:isEnabled WHERE id=:reminderId")
    suspend fun setEnabled(reminderId: Int, isEnabled: Boolean)
}
