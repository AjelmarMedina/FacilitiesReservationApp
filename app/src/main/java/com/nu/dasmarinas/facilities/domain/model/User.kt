package com.nu.dasmarinas.facilities.domain.model

enum class UserRole {
    STUDENT,
    FMO_ADMIN,
    PRIVACY_OFFICER
}

data class User(
    val id: String,
    val email: String,
    val name: String,
    val role: UserRole,
    val organization: String? = null,
    val idNumber: String? = null,
    val contactNumber: String? = null,
    val profileImageUrl: String? = null
)
