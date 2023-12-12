package com.harsh.samples.alarmmanager.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.harsh.samples.alarmmanager.domain.model.Reminder
import com.harsh.samples.alarmmanager.domain.model.ReminderMode
import com.harsh.samples.alarmmanager.domain.model.ReminderType
import com.harsh.samples.alarmmanager.domain.use_case.ScheduleReminderUseCase
import com.harsh.samples.alarmmanager.presentation.theme.AlarmManagersampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.time.Duration.Companion.minutes

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var scheduleReminder: ScheduleReminderUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlarmManagersampleTheme {
                Box(modifier = Modifier.padding(24.dp), contentAlignment = Alignment.Center) {
                    Text(text = "This app has no UI, it is meant to provide a code sample to on how to use AlarmManager with clean architecture, please refer source code on github")
                }
            }
        }

        val reminder = Reminder(
            1,
            "Sample 3min repetitive reminder",
            "Runs once every 3 minutes",
            ReminderType.REPETITIVE_ACCURATE,
            ReminderMode.NOTIFICATION_SILENT,
            true,
            null,
            2000L,
            null,
            null,
            3.minutes.inWholeMilliseconds,
            true
        )

        lifecycleScope.launch(Dispatchers.IO) {
            scheduleReminder(reminder)
        }

    }
}
