package com.nu.dasmarinas.facilities.data.mock

import com.nu.dasmarinas.facilities.domain.model.*
import java.time.LocalDate
import java.time.LocalTime

object MockDataProvider {
    
    // Mock Users
    val studentUser = User(
        id = "student_001",
        email = "juan.delacruz@nu-dasma.edu.ph",
        name = "Juan Dela Cruz",
        role = UserRole.STUDENT,
        organization = "Computer Science Society",
        idNumber = "2021-12345",
        contactNumber = "+63 912 345 6789"
    )
    
    val fmoUser = User(
        id = "fmo_001",
        email = "maria.santos@nu-dasma.edu.ph",
        name = "Maria Santos",
        role = UserRole.FMO_ADMIN,
        organization = "Facilities Management Office"
    )
    
    val privacyOfficer = User(
        id = "privacy_001",
        email = "jose.reyes@nu-dasma.edu.ph",
        name = "Jose Reyes",
        role = UserRole.PRIVACY_OFFICER,
        organization = "Privacy Office"
    )
    
    // Mock Facilities
    val facilities = listOf(
        Facility(
            id = "fac_001",
            name = "Main Gymnasium",
            type = "Sports Facility",
            capacity = 500,
            features = listOf("Sound System", "Air Conditioning", "Stage")
        ),
        Facility(
            id = "fac_002",
            name = "Function Hall A",
            type = "Event Space",
            capacity = 200,
            features = listOf("Projector", "Tables", "Chairs")
        ),
        Facility(
            id = "fac_003",
            name = "Room 301",
            type = "Classroom",
            capacity = 50,
            features = listOf("Whiteboard", "Projector", "Air Conditioning")
        ),
        Facility(
            id = "fac_004",
            name = "University Auditorium",
            type = "Auditorium",
            capacity = 300,
            features = listOf("Stage", "Lights", "Sound System", "Projector")
        ),
        Facility(
            id = "fac_005",
            name = "Basketball Court",
            type = "Sports Facility",
            capacity = 300,
            features = listOf("Outdoor", "Bleachers", "Scoreboard")
        )
    )
    
    // Mock Equipment
    val equipment = listOf(
        Equipment("eq_001", "Sound System (Large)", EquipmentCategory.AUDIO, true),
        Equipment("eq_002", "LCD Projector (HD)", EquipmentCategory.VISUAL, true),
        Equipment("eq_003", "Microphone Set", EquipmentCategory.AUDIO, true),
        Equipment("eq_004", "Whiteboard", EquipmentCategory.OTHER, true),
        Equipment("eq_005", "Laptop", EquipmentCategory.TECHNOLOGY, false),
        Equipment("eq_006", "Speakers (Pair)", EquipmentCategory.AUDIO, true)
    )
    
    // Mock Reservations
    val reservations = listOf(
        Reservation(
            id = "res_001",
            eventTitle = "Tech Summit 2026",
            eventType = EventType.STUDENT_ORGANIZATION_EVENT,
            description = "Annual technology conference for CS students",
            facility = facilities[0], // Main Gymnasium
            date = LocalDate.now().plusDays(15),
            startTime = LocalTime.of(9, 0),
            endTime = LocalTime.of(17, 0),
            expectedAttendees = 150,
            organizer = Organizer(
                name = "Juan Dela Cruz",
                idNumber = "2021-12345",
                email = "juan.delacruz@nu-dasma.edu.ph",
                contactNumber = "+63 912 345 6789",
                organization = "Computer Science Society",
                adviser = "Prof. Maria Garcia"
            ),
            equipment = listOf(equipment[0], equipment[1]),
            status = ReservationStatus.UNDER_REVIEW,
            priority = Priority.NORMAL,
            attachedDocuments = listOf(
                Document("doc_001", "Event Proposal", "https://example.com/proposal.pdf", DocumentType.EVENT_PROPOSAL),
                Document("doc_002", "Activity Plan", "https://example.com/activity.pdf", DocumentType.ACTIVITY_PLAN)
            ),
            submittedAt = System.currentTimeMillis() - 86400000, // 1 day ago
            lastUpdatedAt = System.currentTimeMillis() - 3600000 // 1 hour ago
        ),
        Reservation(
            id = "res_002",
            eventTitle = "General Assembly",
            eventType = EventType.STUDENT_ORGANIZATION_EVENT,
            description = "Monthly general assembly for all members",
            facility = facilities[1], // Function Hall A
            date = LocalDate.now().plusDays(20),
            startTime = LocalTime.of(14, 0),
            endTime = LocalTime.of(16, 0),
            expectedAttendees = 80,
            organizer = Organizer(
                name = "Juan Dela Cruz",
                idNumber = "2021-12345",
                email = "juan.delacruz@nu-dasma.edu.ph",
                contactNumber = "+63 912 345 6789",
                organization = "Computer Science Society",
                adviser = "Prof. Maria Garcia"
            ),
            equipment = listOf(equipment[1]),
            status = ReservationStatus.APPROVED,
            priority = Priority.NORMAL,
            submittedAt = System.currentTimeMillis() - 172800000, // 2 days ago
            lastUpdatedAt = System.currentTimeMillis() - 86400000 // 1 day ago
        ),
        Reservation(
            id = "res_003",
            eventTitle = "Intramurals Opening",
            eventType = EventType.STUDENT_ORGANIZATION_EVENT,
            description = "Opening ceremony for annual intramurals",
            facility = facilities[4], // Basketball Court
            date = LocalDate.now().plusDays(1),
            startTime = LocalTime.of(8, 0),
            endTime = LocalTime.of(12, 0),
            expectedAttendees = 300,
            organizer = Organizer(
                name = "Maria Santos",
                idNumber = "2022-11111",
                email = "maria.santos@nu-dasma.edu.ph",
                contactNumber = "+63 917 123 4567",
                organization = "Student Council",
                adviser = "Prof. Jose Garcia"
            ),
            equipment = listOf(equipment[0], equipment[2]),
            status = ReservationStatus.APPROVED,
            priority = Priority.NORMAL,
            submittedAt = System.currentTimeMillis() - 604800000, // 7 days ago
            lastUpdatedAt = System.currentTimeMillis() - 518400000 // 6 days ago
        ),
        Reservation(
            id = "res_004",
            eventTitle = "Spring Theater Production",
            eventType = EventType.STUDENT_ORGANIZATION_EVENT,
            description = "Annual spring theater production by Drama Club",
            facility = facilities[3], // University Auditorium
            date = LocalDate.now().plusDays(10),
            startTime = LocalTime.of(18, 0),
            endTime = LocalTime.of(21, 0),
            expectedAttendees = 200,
            organizer = Organizer(
                name = "Ana Rodriguez",
                idNumber = "2022-11111",
                email = "ana.rodriguez@nu-dasma.edu.ph",
                contactNumber = "+63 918 234 5678",
                organization = "Drama Club",
                adviser = "Prof. Antonio Cruz"
            ),
            equipment = listOf(equipment[0], equipment[1]),
            status = ReservationStatus.SUBMITTED,
            priority = Priority.NORMAL,
            submittedAt = System.currentTimeMillis() - 259200000, // 3 days ago
            lastUpdatedAt = System.currentTimeMillis() - 259200000
        ),
        Reservation(
            id = "res_005",
            eventTitle = "Review Session - Engineering Math",
            eventType = EventType.ACADEMIC_CLASS,
            description = "Review session for midterm exams",
            facility = facilities[2], // Room 301
            date = LocalDate.now().plusDays(25),
            startTime = LocalTime.of(13, 0),
            endTime = LocalTime.of(15, 0),
            expectedAttendees = 40,
            organizer = Organizer(
                name = "Pedro Cruz",
                idNumber = "2023-22222",
                email = "pedro.cruz@nu-dasma.edu.ph",
                contactNumber = "+63 919 345 6789",
                organization = "Engineering Society",
                adviser = "Prof. Linda Garcia"
            ),
            equipment = listOf(equipment[3]),
            status = ReservationStatus.REJECTED,
            priority = Priority.MEDIUM,
            rejectionReason = "Room 301 is already reserved for a university event on this date. Please check the calendar and select an alternative date or facility.",
            submittedAt = System.currentTimeMillis() - 172800000, // 2 days ago
            lastUpdatedAt = System.currentTimeMillis() - 86400000 // 1 day ago
        )
    )
    
    // Mock Audit Logs
    val auditLogs = listOf(
        AuditLog(
            id = "audit_001",
            action = AuditAction.UPDATE,
            reservationId = "res_005",
            reservationTitle = "Review Session - Engineering Math",
            performedBy = "FMO Admin",
            performedByRole = UserRole.FMO_ADMIN,
            timestamp = System.currentTimeMillis() - 3600000,
            details = "Updated status to \"rejected\" for reservation \"Review Session - Engineering Math\"",
            statusChange = "rejected"
        ),
        AuditLog(
            id = "audit_002",
            action = AuditAction.CREATE,
            reservationId = "res_004",
            reservationTitle = "Spring Theater Production",
            performedBy = "Ana Rodriguez",
            performedByRole = UserRole.STUDENT,
            timestamp = System.currentTimeMillis() - 259200000,
            details = "Created reservation for \"Spring Theater Production\" at University Auditorium",
            statusChange = "submitted"
        ),
        AuditLog(
            id = "audit_003",
            action = AuditAction.CREATE,
            reservationId = "res_001",
            reservationTitle = "Tech Summit 2026",
            performedBy = "Juan Dela Cruz",
            performedByRole = UserRole.STUDENT,
            timestamp = System.currentTimeMillis() - 86400000,
            details = "Created reservation for \"Tech Summit 2026\" at Main Gymnasium",
            statusChange = "submitted"
        ),
        AuditLog(
            id = "audit_004",
            action = AuditAction.UPDATE,
            reservationId = "res_003",
            reservationTitle = "Intramurals Opening",
            performedBy = "FMO Admin",
            performedByRole = UserRole.FMO_ADMIN,
            timestamp = System.currentTimeMillis() - 518400000,
            details = "Updated status to \"approved\" for reservation \"Intramurals Opening\"",
            statusChange = "approved"
        ),
        AuditLog(
            id = "audit_005",
            action = AuditAction.CREATE,
            reservationId = "res_003",
            reservationTitle = "Intramurals Opening",
            performedBy = "Maria Santos",
            performedByRole = UserRole.STUDENT,
            timestamp = System.currentTimeMillis() - 604800000,
            details = "Created reservation for \"Intramurals Opening\" at Basketball Court",
            statusChange = "submitted"
        ),
        AuditLog(
            id = "audit_006",
            action = AuditAction.UPDATE,
            reservationId = "res_002",
            reservationTitle = "General Assembly",
            performedBy = "FMO Admin",
            performedByRole = UserRole.FMO_ADMIN,
            timestamp = System.currentTimeMillis() - 86400000,
            details = "Updated status to \"approved\" for reservation \"General Assembly\"",
            statusChange = "approved"
        ),
        AuditLog(
            id = "audit_007",
            action = AuditAction.CREATE,
            reservationId = "res_002",
            reservationTitle = "General Assembly",
            performedBy = "Juan Dela Cruz",
            performedByRole = UserRole.STUDENT,
            timestamp = System.currentTimeMillis() - 172800000,
            details = "Created reservation for \"General Assembly\" at Function Hall A",
            statusChange = "submitted"
        ),
        AuditLog(
            id = "audit_008",
            action = AuditAction.CREATE,
            reservationId = "res_005",
            reservationTitle = "Review Session - Engineering Math",
            performedBy = "Pedro Cruz",
            performedByRole = UserRole.STUDENT,
            timestamp = System.currentTimeMillis() - 172800000,
            details = "Created reservation for \"Review Session - Engineering Math\" at Room 301",
            statusChange = "submitted"
        )
    )
    
    // Mock Notifications
    val notifications = listOf(
        Notification(
            id = "notif_001",
            type = NotificationType.RESERVATION_STATUS_UPDATE,
            title = "Reservation Approved",
            message = "Your reservation for \"General Assembly\" has been approved by FMO Admin.",
            timestamp = System.currentTimeMillis() - 86400000,
            isRead = false,
            reservationId = "res_002"
        ),
        Notification(
            id = "notif_002",
            type = NotificationType.EVENT_REMINDER,
            title = "Upcoming Event Reminder",
            message = "Your event \"Intramurals Opening\" is happening tomorrow at 8:00 AM.",
            timestamp = System.currentTimeMillis() - 172800000,
            isRead = false,
            reservationId = "res_003"
        ),
        Notification(
            id = "notif_003",
            type = NotificationType.RESERVATION_STATUS_UPDATE,
            title = "Reservation Rejected",
            message = "Your reservation for \"Review Session - Engineering Math\" has been rejected. Reason: Room conflict.",
            timestamp = System.currentTimeMillis() - 86400000,
            isRead = true,
            reservationId = "res_005"
        ),
        Notification(
            id = "notif_004",
            type = NotificationType.SYSTEM_ANNOUNCEMENT,
            title = "System Maintenance",
            message = "The reservation system will undergo maintenance on March 1, 2026 from 12:00 AM to 4:00 AM.",
            timestamp = System.currentTimeMillis() - 259200000,
            isRead = true
        )
    )
    
    fun getPendingReservations() = reservations.filter { 
        it.status == ReservationStatus.UNDER_REVIEW || it.status == ReservationStatus.SUBMITTED 
    }
    
    fun getApprovedReservations() = reservations.filter { it.status == ReservationStatus.APPROVED }
    
    fun getMyReservations(userId: String) = reservations.filter { 
        it.organizer.email.contains("juan.delacruz") 
    }
    
    fun getReservationById(id: String) = reservations.find { it.id == id }
}
