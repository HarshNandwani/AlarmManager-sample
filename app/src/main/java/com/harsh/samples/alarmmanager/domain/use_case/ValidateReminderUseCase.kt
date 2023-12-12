package com.harsh.samples.alarmmanager.domain.use_case

import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.model.ReminderType
import com.harsh.samples.alarmmanager.domain.util.InvalidReminderException
import java.util.Date
import kotlin.time.Duration.Companion.minutes

class ValidateReminderUseCase {
    @Throws(InvalidReminderException::class)
    operator fun invoke(reminder: Reminder): Boolean {
        reminder.apply {
            if (title.isBlank())
                throw InvalidReminderException("Title cannot be empty")
            if (description.isBlank())
                throw InvalidReminderException("Description cannot be empty")

            if (type == ReminderType.ONCE_ACCURATE || type == ReminderType.ONCE_FLEXIBLE) {
                if (remindTime == null)
                    throw InvalidReminderException("Remind time is needed for one time reminders")

                if (remindTime < Date(System.currentTimeMillis() + 1000))
                    throw InvalidReminderException("Remind time cannot be before 1 min from now")
            } else {
                if (intervalMillis == null)
                    throw InvalidReminderException("Interval between reminders is needed for repetitive reminders")

                if (intervalMillis < 2.minutes.inWholeMilliseconds)
                    throw InvalidReminderException("Reminder intervals must be at least 2 minutes apart")
            }

            return true
        }

    }
}
