package com.harsh.samples.alarmmanager.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.model.ReminderMode
import com.harsh.samples.alarmmanager.domain.model.ReminderType
import java.util.Date

@Entity
data class ReminderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val type: ReminderType,
    val mode: ReminderMode,
    val isEnabled: Boolean,
    val remindTime: Date?,
    val durationMillis: Long?,
    val startTime: Date?,
    val endTime: Date?,
    val intervalMillis: Long?,
    val wakeScreen: Boolean
) {
    companion object {
        fun toEntity(reminder: Reminder) = ReminderEntity(
            reminder.id,
            reminder.title,
            reminder.description,
            reminder.type,
            reminder.mode,
            reminder.isEnabled,
            reminder.remindTime,
            reminder.durationMillis,
            reminder.startTime,
            reminder.endTime,
            reminder.intervalMillis,
            reminder.wakeScreen
        )
    }

    fun toDomain(): Reminder {
        this.apply {
            return Reminder(
                id,
                title,
                description,
                type,
                mode,
                isEnabled,
                remindTime,
                durationMillis,
                startTime,
                endTime,
                intervalMillis,
                wakeScreen
            )
        }
    }

}
