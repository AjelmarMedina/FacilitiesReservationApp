package com.nu.dasmarinas.facilities.presentation.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nu.dasmarinas.facilities.domain.model.Reservation
import com.nu.dasmarinas.facilities.domain.model.ReservationStatus
import com.nu.dasmarinas.facilities.domain.model.User
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * ViewModel for Student Dashboard Screen
 * 
 * Manages UI state and business logic for the student dashboard,
 * including reservation statistics and recent reservations list.
 */
class StudentDashboardViewModel : ViewModel() {

    // UI State
    private val _uiState = MutableStateFlow(StudentDashboardUiState())
    val uiState: StateFlow<StudentDashboardUiState> = _uiState.asStateFlow()

    init {
        loadDashboardData()
    }

    /**
     * Load all dashboard data including counts and recent reservations
     */
    private fun loadDashboardData() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            try {
                // Get current user's ID (would come from auth repository in real app)
                val userId = getCurrentUserId()
                
                // In production, combine flows from repository
                // For now, just update with empty state
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        recentReservations = emptyList(),
                        pendingCount = 0,
                        approvedCount = 0,
                        upcomingCount = 0,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.update { 
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Unknown error occurred"
                    )
                }
            }
        }
    }

    /**
     * Refresh dashboard data
     */
    fun refresh() {
        loadDashboardData()
    }

    /**
     * Navigate to create new reservation
     */
    fun onCreateReservation() {
        // Navigation would be handled by the screen
        _uiState.update { it.copy(navigationEvent = NavigationEvent.CreateReservation) }
    }

    /**
     * Navigate to reservations list
     */
    fun onViewAllReservations() {
        _uiState.update { it.copy(navigationEvent = NavigationEvent.ViewAllReservations) }
    }

    /**
     * Navigate to calendar
     */
    fun onViewCalendar() {
        _uiState.update { it.copy(navigationEvent = NavigationEvent.ViewCalendar) }
    }

    /**
     * Clear navigation event after handling
     */
    fun clearNavigationEvent() {
        _uiState.update { it.copy(navigationEvent = null) }
    }

    /**
     * Get current user ID from authentication
     */
    private suspend fun getCurrentUserId(): String {
        // In real app, get from AuthRepository
        return "current_user_id"
    }

    /**
     * Calculate upcoming reservations count
     */
    private fun getUpcomingCount(reservations: List<Reservation>): Int {
        val now = System.currentTimeMillis()
        return reservations.count { 
            it.status == ReservationStatus.APPROVED && 
            it.submittedAt > now 
        }
    }
}

/**
 * UI State for Student Dashboard
 */
data class StudentDashboardUiState(
    val isLoading: Boolean = false,
    val recentReservations: List<Reservation> = emptyList(),
    val pendingCount: Int = 0,
    val approvedCount: Int = 0,
    val upcomingCount: Int = 0,
    val error: String? = null,
    val navigationEvent: NavigationEvent? = null
)

/**
 * Navigation events for the dashboard
 */
sealed class NavigationEvent {
    object CreateReservation : NavigationEvent()
    object ViewAllReservations : NavigationEvent()
    object ViewCalendar : NavigationEvent()
    data class ViewReservationDetails(val reservationId: String) : NavigationEvent()
}
