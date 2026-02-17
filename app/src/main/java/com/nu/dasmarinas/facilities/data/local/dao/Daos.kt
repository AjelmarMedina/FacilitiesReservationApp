package com.nu.dasmarinas.facilities.data.local.dao

import androidx.room.*
import com.nu.dasmarinas.facilities.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteUser(userId: String)
}

@Dao
interface ReservationDao {
    @Query("SELECT * FROM reservations ORDER BY submittedAt DESC")
    fun getAllReservations(): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM reservations WHERE id = :reservationId")
    suspend fun getReservationById(reservationId: String): ReservationEntity?

    @Query("SELECT * FROM reservations WHERE organizerId = :userId ORDER BY submittedAt DESC")
    fun getReservationsByUser(userId: String): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM reservations WHERE status = :status ORDER BY priority DESC, submittedAt ASC")
    fun getReservationsByStatus(status: String): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM reservations WHERE status IN ('SUBMITTED', 'UNDER_REVIEW') ORDER BY priority DESC, submittedAt ASC")
    fun getPendingReservations(): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM reservations WHERE date = :date")
    fun getReservationsByDate(date: String): Flow<List<ReservationEntity>>

    @Query("SELECT * FROM reservations WHERE facilityId = :facilityId AND date = :date")
    fun getReservationsByFacilityAndDate(facilityId: String, date: String): Flow<List<ReservationEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReservation(reservation: ReservationEntity)

    @Update
    suspend fun updateReservation(reservation: ReservationEntity)

    @Delete
    suspend fun deleteReservation(reservation: ReservationEntity)

    @Query("SELECT COUNT(*) FROM reservations WHERE organizerId = :userId AND status = :status")
    suspend fun getReservationCountByStatus(userId: String, status: String): Int
}

@Dao
interface FacilityDao {
    @Query("SELECT * FROM facilities")
    fun getAllFacilities(): Flow<List<FacilityEntity>>

    @Query("SELECT * FROM facilities WHERE id = :facilityId")
    suspend fun getFacilityById(facilityId: String): FacilityEntity?

    @Query("SELECT * FROM facilities WHERE type = :type")
    fun getFacilitiesByType(type: String): Flow<List<FacilityEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacility(facility: FacilityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFacilities(facilities: List<FacilityEntity>)

    @Update
    suspend fun updateFacility(facility: FacilityEntity)

    @Delete
    suspend fun deleteFacility(facility: FacilityEntity)
}

@Dao
interface EquipmentDao {
    @Query("SELECT * FROM equipment")
    fun getAllEquipment(): Flow<List<EquipmentEntity>>

    @Query("SELECT * FROM equipment WHERE id = :equipmentId")
    suspend fun getEquipmentById(equipmentId: String): EquipmentEntity?

    @Query("SELECT * FROM equipment WHERE category = :category")
    fun getEquipmentByCategory(category: String): Flow<List<EquipmentEntity>>

    @Query("SELECT * FROM equipment WHERE isAvailable = 1")
    fun getAvailableEquipment(): Flow<List<EquipmentEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEquipment(equipment: EquipmentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEquipmentList(equipmentList: List<EquipmentEntity>)

    @Update
    suspend fun updateEquipment(equipment: EquipmentEntity)
}

@Dao
interface AuditLogDao {
    @Query("SELECT * FROM audit_logs ORDER BY timestamp DESC")
    fun getAllAuditLogs(): Flow<List<AuditLogEntity>>

    @Query("SELECT * FROM audit_logs WHERE reservationId = :reservationId ORDER BY timestamp DESC")
    fun getAuditLogsByReservation(reservationId: String): Flow<List<AuditLogEntity>>

    @Query("SELECT * FROM audit_logs WHERE `action` = :action ORDER BY timestamp DESC")
    fun getAuditLogsByAction(action: String): Flow<List<AuditLogEntity>>

    @Query("SELECT * FROM audit_logs WHERE timestamp BETWEEN :startTime AND :endTime ORDER BY timestamp DESC")
    fun getAuditLogsByTimeRange(startTime: Long, endTime: Long): Flow<List<AuditLogEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAuditLog(auditLog: AuditLogEntity)

    @Query("DELETE FROM audit_logs WHERE timestamp < :timestamp")
    suspend fun deleteOldLogs(timestamp: Long)
}

@Dao
interface NotificationDao {
    @Query("SELECT * FROM notifications ORDER BY timestamp DESC")
    fun getAllNotifications(): Flow<List<NotificationEntity>>

    @Query("SELECT * FROM notifications WHERE isRead = 0 ORDER BY timestamp DESC")
    fun getUnreadNotifications(): Flow<List<NotificationEntity>>

    @Query("SELECT COUNT(*) FROM notifications WHERE isRead = 0")
    fun getUnreadCount(): Flow<Int>

    @Query("SELECT * FROM notifications WHERE id = :notificationId")
    suspend fun getNotificationById(notificationId: String): NotificationEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotification(notification: NotificationEntity)

    @Update
    suspend fun updateNotification(notification: NotificationEntity)

    @Query("UPDATE notifications SET isRead = 1 WHERE id = :notificationId")
    suspend fun markAsRead(notificationId: String)

    @Query("UPDATE notifications SET isRead = 1")
    suspend fun markAllAsRead()

    @Delete
    suspend fun deleteNotification(notification: NotificationEntity)

    @Query("DELETE FROM notifications WHERE timestamp < :timestamp")
    suspend fun deleteOldNotifications(timestamp: Long)
}
