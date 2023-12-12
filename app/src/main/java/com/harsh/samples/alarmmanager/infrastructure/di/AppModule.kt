package com.harsh.samples.alarmmanager.infrastructure.di

import android.app.Application
import androidx.room.Room
import com.harsh.samples.alarmmanager.data.MyWayReminderDB
import com.harsh.samples.alarmmanager.data.repository.RoomReminderRepository
import com.harsh.samples.alarmmanager.domain.actions.Scheduler
import com.harsh.samples.alarmmanager.domain.repository.ReminderRepository
import com.harsh.samples.alarmmanager.domain.use_case.CancelReminderUseCase
import com.harsh.samples.alarmmanager.domain.use_case.RemoveReminderUseCase
import com.harsh.samples.alarmmanager.domain.use_case.ScheduleReminderUseCase
import com.harsh.samples.alarmmanager.domain.use_case.ValidateReminderUseCase
import com.harsh.samples.alarmmanager.infrastructure.schedule.AlarmScheduler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("unused")
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMyWayReminderDB(app: Application): MyWayReminderDB {
        return Room.databaseBuilder(
            app,
            MyWayReminderDB::class.java,
            MyWayReminderDB.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideReminderRepository(db: MyWayReminderDB): ReminderRepository {
        return RoomReminderRepository(db.reminderDao)
    }

    @Provides
    @Singleton
    fun provideScheduler(app: Application): Scheduler {
        return AlarmScheduler(app.applicationContext)
    }

    @Provides
    @Singleton
    fun provideValidateReminderUseCase(): ValidateReminderUseCase {
        return ValidateReminderUseCase()
    }

    @Provides
    @Singleton
    fun provideScheduleReminderUseCase(
        validator: ValidateReminderUseCase,
        repository: ReminderRepository,
        scheduler: Scheduler
    ): ScheduleReminderUseCase {
        return ScheduleReminderUseCase(validator, repository, scheduler)
    }

    @Provides
    fun provideCancelReminderUseCase(
        scheduler: Scheduler,
        repository: ReminderRepository
    ): CancelReminderUseCase {
        return CancelReminderUseCase(scheduler, repository)
    }

    @Provides
    fun provideRemoveReminderUseCase(
        cancelReminder: CancelReminderUseCase,
        repository: ReminderRepository
    ): RemoveReminderUseCase {
        return RemoveReminderUseCase(cancelReminder, repository)
    }

}
