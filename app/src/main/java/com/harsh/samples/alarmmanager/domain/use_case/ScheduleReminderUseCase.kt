package com.harsh.samples.alarmmanager.domain.use_case

import com.harsh.samples.alarmmanager.domain.actions.Scheduler
import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.model.ReminderType
import com.harsh.samples.alarmmanager.domain.repository.ReminderRepository
import com.harsh.samples.alarmmanager.domain.util.InvalidReminderException

class ScheduleReminderUseCase(
    private val validate: ValidateReminderUseCase,
    private val repository: ReminderRepository,
    private val scheduler: Scheduler
) {
    @Throws(InvalidReminderException::class)
    suspend operator fun invoke(reminder: Reminder) {
        validate(reminder)
        repository.add(reminder)

        when (reminder.type) {
            ReminderType.ONCE_ACCURATE -> scheduler.scheduleOnceAccurate(reminder)
            ReminderType.ONCE_FLEXIBLE -> scheduler.scheduleOnceFlexible(reminder)
            ReminderType.REPETITIVE_ACCURATE -> scheduler.scheduleRepetitiveAccurate(reminder)
            ReminderType.REPETITIVE_FLEXIBLE -> scheduler.scheduleRepetitiveFlexible(reminder)
        }
    }
}
