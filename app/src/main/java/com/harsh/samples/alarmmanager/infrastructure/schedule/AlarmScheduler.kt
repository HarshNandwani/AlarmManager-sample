package com.harsh.samples.alarmmanager.infrastructure.schedule

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.harsh.samples.alarmmanager.domain.actions.Scheduler
import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.util.ScheduleException
import com.harsh.samples.alarmmanager.domain.util.Constants.KEY_REMINDER_ID
import com.harsh.samples.alarmmanager.presentation.AlarmReceiver

class AlarmScheduler(private val context: Context): Scheduler {

    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    override fun scheduleOnceAccurate(reminder: Reminder) {
        if (reminder.remindTime == null) throw ScheduleException("Cannot schedule reminder, the remind time is null")

        alarmManager.setExactAndAllowWhileIdle(
            if (reminder.wakeScreen) AlarmManager.RTC_WAKEUP else AlarmManager.RTC,
            reminder.remindTime.time,
            getPendingIntent(reminder)
        )
    }

    override fun scheduleOnceFlexible(reminder: Reminder) {
        if (reminder.remindTime == null) throw ScheduleException("Cannot schedule reminder, the remind time is null")

        alarmManager.setAndAllowWhileIdle(
            if (reminder.wakeScreen) AlarmManager.RTC_WAKEUP else AlarmManager.RTC,
            reminder.remindTime.time,
            getPendingIntent(reminder)
        )
    }

    override fun scheduleRepetitiveAccurate(reminder: Reminder) {
        if (reminder.intervalMillis == null) throw ScheduleException("Cannot schedule reminder, the interval is null")

        alarmManager.setRepeating(
            if (reminder.wakeScreen) AlarmManager.RTC_WAKEUP else AlarmManager.RTC,
            System.currentTimeMillis() + reminder.intervalMillis,
            reminder.intervalMillis,
            getPendingIntent(reminder)
        )
    }

    override fun scheduleRepetitiveFlexible(reminder: Reminder) {
        if (reminder.intervalMillis == null) throw ScheduleException("Cannot schedule reminder, the interval is null")

        alarmManager.setInexactRepeating(
            if (reminder.wakeScreen) AlarmManager.RTC_WAKEUP else AlarmManager.RTC,
            System.currentTimeMillis() + reminder.intervalMillis,
            reminder.intervalMillis,
            getPendingIntent(reminder)
        )
    }

    override fun cancel(reminder: Reminder) {
        alarmManager.cancel(getPendingIntent(reminder))
    }

    private fun getPendingIntent(reminder: Reminder): PendingIntent {

        val intent = Intent(context, AlarmReceiver::class.java)
        intent.action = reminder.type.name
        intent.putExtra(KEY_REMINDER_ID, reminder.id)

        return PendingIntent.getBroadcast(
            context,
            reminder.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

}
