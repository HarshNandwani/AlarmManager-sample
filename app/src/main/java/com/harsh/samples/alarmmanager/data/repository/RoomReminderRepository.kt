package com.harsh.samples.alarmmanager.data.repository

import com.harsh.samples.alarmmanager.data.dao.ReminderDao
import com.harsh.samples.alarmmanager.data.entity.ReminderEntity
import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.repository.ReminderRepository


class RoomReminderRepository(private val dao: ReminderDao) : ReminderRepository {

    override suspend fun add(reminder: Reminder) {
        dao.add(ReminderEntity.toEntity(reminder))
    }

    override suspend fun getAll(): List<Reminder> {
        return dao.getAll().map { it.toDomain() }
    }

    override suspend fun get(id: Int): Reminder? {
        return dao.get(id)?.toDomain()
    }

    override suspend fun update(reminder: Reminder) {
        dao.update(ReminderEntity.toEntity(reminder))
    }

    override suspend fun delete(reminder: Reminder) {
        dao.delete(ReminderEntity.toEntity(reminder))
    }

    override suspend fun setEnabled(reminder: Reminder) {
        dao.setEnabled(reminder.id, true)
    }

    override suspend fun setDisabled(reminder: Reminder) {
        dao.setEnabled(reminder.id, false)
    }

}
