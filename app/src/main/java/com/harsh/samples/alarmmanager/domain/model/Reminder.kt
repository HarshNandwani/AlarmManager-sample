package com.harsh.samples.alarmmanager.domain.model

import java.util.Date

data class Reminder(
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
)
