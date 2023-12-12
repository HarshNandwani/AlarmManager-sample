package com.harsh.samples.alarmmanager.domain.use_case

import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.repository.ReminderRepository

class RemoveReminderUseCase(
    private val cancelReminder: CancelReminderUseCase,
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(reminder: Reminder) {
        cancelReminder(reminder)
        repository.delete(reminder)
    }
}