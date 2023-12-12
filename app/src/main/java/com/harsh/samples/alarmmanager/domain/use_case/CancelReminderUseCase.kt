package com.harsh.samples.alarmmanager.domain.use_case

import com.harsh.samples.alarmmanager.domain.actions.Scheduler
import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.repository.ReminderRepository

class CancelReminderUseCase(
    private val scheduler: Scheduler,
    private val repository: ReminderRepository
) {
    suspend operator fun invoke(reminder: Reminder) {
        scheduler.cancel(reminder)
        repository.setDisabled(reminder)
    }
}
