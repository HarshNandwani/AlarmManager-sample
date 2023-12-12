package com.harsh.samples.alarmmanager.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.harsh.samples.alarmmanager.domain.model.ReminderMode
import com.harsh.samples.alarmmanager.domain.repository.ReminderRepository
import com.harsh.samples.alarmmanager.domain.util.Constants.KEY_REMINDER_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.EmptyCoroutineContext

@AndroidEntryPoint
class AlarmReceiver: BroadcastReceiver() {

    @Inject
    lateinit var repository: ReminderRepository

    override fun onReceive(context: Context?, intent: Intent?) {
        val id = intent?.extras?.getInt(KEY_REMINDER_ID) ?: return
        Log.d("AlarmReceiver", "Alarm received with reminder id: $id")
        CoroutineScope(EmptyCoroutineContext).launch(Dispatchers.IO) {
            val reminder = repository.get(id) ?: return@launch

            Toast.makeText(context, reminder.title, Toast.LENGTH_SHORT).show()
            Log.d("AlarmReceiver", "Reminder - ${reminder.title}")

            when(reminder.mode) {
                ReminderMode.NOTIFICATION -> showNotification()
                ReminderMode.NOTIFICATION_SILENT -> showNotification(silent = true)
                ReminderMode.FULLSCREEN -> launchActivity()
                ReminderMode.FULLSCREEN_SILENT -> launchActivity(silent = true)
            }
        }
    }

    private fun showNotification(silent: Boolean = false) {
        // logic to show notification
    }

    private fun launchActivity(silent: Boolean = false) {
        // Launch your activity to show full screen reminder, pass necessary info in a bundle
    }

}
