package com.nu.dasmarinas.facilities.domain.model

enum class AuditAction {
    CREATE,
    UPDATE,
    APPROVE,
    REJECT,
    VIEW,
    DELETE
}

data class AuditLog(
    val id: String,
    val action: AuditAction,
    val reservationId: String,
    val reservationTitle: String,
    val performedBy: String,
    val performedByRole: UserRole,
    val timestamp: Long,
    val details: String,
    val statusChange: String? = null
)
