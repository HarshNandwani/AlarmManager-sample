package com.harsh.samples.alarmmanager.domain.repository

import com.harsh.samples.alarmmanager.domain.model.Reminder

interface ReminderRepository {
    suspend fun add(reminder: Reminder)
    suspend fun getAll(): List<Reminder>
    suspend fun get(id: Int): Reminder?
    suspend fun update(reminder: Reminder)
    suspend fun delete(reminder: Reminder)
    suspend fun setEnabled(reminder: Reminder)
    suspend fun setDisabled(reminder: Reminder)
}
