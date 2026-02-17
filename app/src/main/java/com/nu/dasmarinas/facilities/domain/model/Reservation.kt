package com.nu.dasmarinas.facilities.domain.model

import java.time.LocalDate
import java.time.LocalTime

enum class ReservationStatus {
    SUBMITTED,
    UNDER_REVIEW,
    APPROVED,
    REJECTED,
    CANCELLED
}

enum class EventType {
    STUDENT_ORGANIZATION_EVENT,
    ACADEMIC_CLASS,
    UNIVERSITY_EVENT
}

enum class Priority {
    HIGH,      // University Events
    MEDIUM,    // Academic Classes
    NORMAL     // Student Organization Events
}

data class Reservation(
    val id: String,
    val eventTitle: String,
    val eventType: EventType,
    val description: String,
    val facility: Facility,
    val date: LocalDate,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val expectedAttendees: Int,
    val organizer: Organizer,
    val equipment: List<Equipment> = emptyList(),
    val status: ReservationStatus,
    val priority: Priority,
    val attachedDocuments: List<Document> = emptyList(),
    val submittedAt: Long,
    val lastUpdatedAt: Long,
    val rejectionReason: String? = null
)

data class Organizer(
    val name: String,
    val idNumber: String,
    val email: String,
    val contactNumber: String,
    val organization: String,
    val adviser: String? = null
)

data class Facility(
    val id: String,
    val name: String,
    val type: String,
    val capacity: Int,
    val imageUrl: String? = null,
    val features: List<String> = emptyList()
)

data class Equipment(
    val id: String,
    val name: String,
    val category: EquipmentCategory,
    val isAvailable: Boolean = true
)

enum class EquipmentCategory {
    AUDIO,
    VISUAL,
    FURNITURE,
    TECHNOLOGY,
    OTHER
}

data class Document(
    val id: String,
    val name: String,
    val url: String,
    val type: DocumentType
)

enum class DocumentType {
    EVENT_PROPOSAL,
    ACTIVITY_PLAN,
    LETTER_REQUEST,
    OTHER
}
