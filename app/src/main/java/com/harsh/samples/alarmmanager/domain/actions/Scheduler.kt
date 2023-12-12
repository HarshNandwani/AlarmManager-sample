package com.harsh.samples.alarmmanager.domain.actions

import com.harsh.samples.alarmmanager.domain.model.Reminder

interface Scheduler {
    fun scheduleOnceAccurate(reminder: Reminder)
    fun scheduleOnceFlexible(reminder: Reminder)
    fun scheduleRepetitiveAccurate(reminder: Reminder)
    fun scheduleRepetitiveFlexible(reminder: Reminder)
    fun cancel(reminder: Reminder)
}
