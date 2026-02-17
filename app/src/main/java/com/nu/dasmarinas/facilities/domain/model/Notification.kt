package com.nu.dasmarinas.facilities.domain.model

enum class NotificationType {
    RESERVATION_STATUS_UPDATE,
    EVENT_REMINDER,
    SYSTEM_ANNOUNCEMENT
}

data class Notification(
    val id: String,
    val type: NotificationType,
    val title: String,
    val message: String,
    val timestamp: Long,
    val isRead: Boolean = false,
    val reservationId: String? = null
)

data class NotificationPreferences(
    val reservationStatusUpdates: Boolean = true,
    val eventReminders: Boolean = true,
    val systemAnnouncements: Boolean = true
)
