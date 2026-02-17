package com.nu.dasmarinas.facilities.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nu.dasmarinas.facilities.data.local.converter.Converters

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val email: String,
    val name: String,
    val role: String,
    val organization: String?,
    val idNumber: String?,
    val contactNumber: String?,
    val profileImageUrl: String?
)

@Entity(tableName = "reservations")
@TypeConverters(Converters::class)
data class ReservationEntity(
    @PrimaryKey val id: String,
    val eventTitle: String,
    val eventType: String,
    val description: String,
    val facilityId: String,
    val facilityName: String,
    val date: String, // LocalDate as String
    val startTime: String, // LocalTime as String
    val endTime: String,
    val expectedAttendees: Int,
    val organizerName: String,
    val organizerId: String,
    val organizerEmail: String,
    val organizerContact: String,
    val organizerOrganization: String,
    val organizerAdviser: String?,
    val equipmentIds: String, // Comma-separated IDs
    val status: String,
    val priority: String,
    val documentIds: String, // Comma-separated IDs
    val submittedAt: Long,
    val lastUpdatedAt: Long,
    val rejectionReason: String?
)

@Entity(tableName = "facilities")
@TypeConverters(Converters::class)
data class FacilityEntity(
    @PrimaryKey val id: String,
    val name: String,
    val type: String,
    val capacity: Int,
    val imageUrl: String?,
    val features: String // Comma-separated features
)

@Entity(tableName = "equipment")
data class EquipmentEntity(
    @PrimaryKey val id: String,
    val name: String,
    val category: String,
    val isAvailable: Boolean
)

@Entity(tableName = "audit_logs")
data class AuditLogEntity(
    @PrimaryKey val id: String,
    val action: String,
    val reservationId: String,
    val reservationTitle: String,
    val performedBy: String,
    val performedByRole: String,
    val timestamp: Long,
    val details: String,
    val statusChange: String?
)

@Entity(tableName = "notifications")
data class NotificationEntity(
    @PrimaryKey val id: String,
    val type: String,
    val title: String,
    val message: String,
    val timestamp: Long,
    val isRead: Boolean,
    val reservationId: String?
)
