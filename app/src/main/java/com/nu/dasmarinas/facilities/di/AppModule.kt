package com.nu.dasmarinas.facilities.di

import android.content.Context
import androidx.room.Room
import com.nu.dasmarinas.facilities.data.local.FacilitiesDatabase
import com.nu.dasmarinas.facilities.data.local.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFacilitiesDatabase(
        @ApplicationContext context: Context
    ): FacilitiesDatabase {
        return Room.databaseBuilder(
            context,
            FacilitiesDatabase::class.java,
            "facilities_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideUserDao(database: FacilitiesDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    fun provideReservationDao(database: FacilitiesDatabase): ReservationDao {
        return database.reservationDao()
    }

    @Provides
    fun provideFacilityDao(database: FacilitiesDatabase): FacilityDao {
        return database.facilityDao()
    }

    @Provides
    fun provideEquipmentDao(database: FacilitiesDatabase): EquipmentDao {
        return database.equipmentDao()
    }

    @Provides
    fun provideAuditLogDao(database: FacilitiesDatabase): AuditLogDao {
        return database.auditLogDao()
    }

    @Provides
    fun provideNotificationDao(database: FacilitiesDatabase): NotificationDao {
        return database.notificationDao()
    }
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // Add Retrofit and API service providers here
}

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    // Add repository providers here
}
