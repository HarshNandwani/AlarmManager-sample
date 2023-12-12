package com.harsh.samples.alarmmanager.infrastructure.schedule

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.harsh.samples.alarmmanager.domain.repository.ReminderRepository
import com.harsh.samples.alarmmanager.domain.use_case.ScheduleReminderUseCase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class BootReceiver: BroadcastReceiver() {

    @Inject
    lateinit var repository: ReminderRepository

    @Inject
    lateinit var scheduleReminder: ScheduleReminderUseCase

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {

            // Device has rebooted, schedule our reminders/alarms again!

            /*
            * For this example, I am scheduling reminders in onReceive() itself
            * it is not recommended to perform long running tasks in a BroadcastReceiver.
            *
            * Consider launching a background service.
            *
            * */

            CoroutineScope(EmptyCoroutineContext).launch(Dispatchers.IO) {
                repository.getAll().forEach {
                    if (it.isEnabled)
                        scheduleReminder(it)
                }
            }
        }
    }
}
