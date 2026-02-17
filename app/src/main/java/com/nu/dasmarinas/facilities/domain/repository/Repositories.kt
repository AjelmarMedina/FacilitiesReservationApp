package com.nu.dasmarinas.facilities.domain.repository

import com.nu.dasmarinas.facilities.domain.model.*
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(email: String, password: String, name: String, role: UserRole): Result<User>
    suspend fun logout(): Result<Unit>
    suspend fun getCurrentUser(): User?
    fun isLoggedIn(): Flow<Boolean>
}

interface ReservationRepository {
    fun getAllReservations(): Flow<List<Reservation>>
    fun getReservationById(id: String): Flow<Reservation?>
    fun getMyReservations(userId: String): Flow<List<Reservation>>
    fun getPendingReservations(): Flow<List<Reservation>>
    fun getReservationsByStatus(status: ReservationStatus): Flow<List<Reservation>>
    fun getReservationsByDate(date: java.time.LocalDate): Flow<List<Reservation>>
    suspend fun createReservation(reservation: Reservation): Result<Reservation>
    suspend fun updateReservation(reservation: Reservation): Result<Reservation>
    suspend fun approveReservation(id: String, approverId: String): Result<Reservation>
    suspend fun rejectReservation(id: String, approverId: String, reason: String): Result<Reservation>
    suspend fun cancelReservation(id: String): Result<Unit>
    suspend fun getReservationCounts(userId: String): Map<ReservationStatus, Int>
}

interface FacilityRepository {
    fun getAllFacilities(): Flow<List<Facility>>
    suspend fun getFacilityById(id: String): Facility?
    fun getFacilitiesByType(type: String): Flow<List<Facility>>
    suspend fun checkFacilityAvailability(
        facilityId: String,
        date: java.time.LocalDate,
        startTime: java.time.LocalTime,
        endTime: java.time.LocalTime
    ): Boolean
}

interface EquipmentRepository {
    fun getAllEquipment(): Flow<List<Equipment>>
    suspend fun getEquipmentById(id: String): Equipment?
    fun getEquipmentByCategory(category: EquipmentCategory): Flow<List<Equipment>>
    fun getAvailableEquipment(): Flow<List<Equipment>>
}

interface AuditLogRepository {
    fun getAllAuditLogs(): Flow<List<AuditLog>>
    fun getAuditLogsByReservation(reservationId: String): Flow<List<AuditLog>>
    fun getAuditLogsByAction(action: AuditAction): Flow<List<AuditLog>>
    suspend fun logAction(
        action: AuditAction,
        reservationId: String,
        reservationTitle: String,
        performedBy: String,
        performedByRole: UserRole,
        details: String,
        statusChange: String? = null
    )
}

interface NotificationRepository {
    fun getAllNotifications(): Flow<List<Notification>>
    fun getUnreadNotifications(): Flow<List<Notification>>
    fun getUnreadCount(): Flow<Int>
    suspend fun markAsRead(notificationId: String)
    suspend fun markAllAsRead()
    suspend fun createNotification(notification: Notification)
    suspend fun deleteNotification(notificationId: String)
}
