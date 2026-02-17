package com.nu.dasmarinas.facilities.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nu.dasmarinas.facilities.data.local.converter.Converters
import com.nu.dasmarinas.facilities.data.local.dao.*
import com.nu.dasmarinas.facilities.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        ReservationEntity::class,
        FacilityEntity::class,
        EquipmentEntity::class,
        AuditLogEntity::class,
        NotificationEntity::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class FacilitiesDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun reservationDao(): ReservationDao
    abstract fun facilityDao(): FacilityDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun auditLogDao(): AuditLogDao
    abstract fun notificationDao(): NotificationDao
}
