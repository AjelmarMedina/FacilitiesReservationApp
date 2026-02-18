package com.nu.dasmarinas.facilities.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nu.dasmarinas.facilities.data.mock.MockDataProvider
import com.nu.dasmarinas.facilities.domain.model.*
import com.nu.dasmarinas.facilities.presentation.approval.ApprovalQueueScreen
import com.nu.dasmarinas.facilities.presentation.auth.LoginScreen
import com.nu.dasmarinas.facilities.presentation.calendar.FacilityCalendarScreen
import com.nu.dasmarinas.facilities.presentation.dashboard.FMODashboardScreen
import com.nu.dasmarinas.facilities.presentation.dashboard.StudentDashboardScreen
import com.nu.dasmarinas.facilities.presentation.notifications.NotificationsScreen
import com.nu.dasmarinas.facilities.presentation.privacy.AuditLogsScreen
import com.nu.dasmarinas.facilities.presentation.privacy.PrivacyOfficeDashboardScreen
import com.nu.dasmarinas.facilities.presentation.reservation.AllReservationsScreen
import com.nu.dasmarinas.facilities.presentation.reservation.NewReservationScreen
import com.nu.dasmarinas.facilities.presentation.reservation.ReservationDetailsScreen
import java.time.LocalDate

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object StudentDashboard : Screen("student_dashboard")
    object FMODashboard : Screen("fmo_dashboard")
    object PrivacyDashboard : Screen("privacy_dashboard")
    object NewReservation : Screen("new_reservation")
    object ReservationDetails : Screen("reservation_details/{id}") {
        fun createRoute(id: String) = "reservation_details/$id"
    }
    object MyReservations : Screen("my_reservations")
    object AllReservations : Screen("all_reservations")
    object ApprovalQueue : Screen("approval_queue")
    object Calendar : Screen("calendar")
    object Notifications : Screen("notifications")
    object AuditLogs : Screen("audit_logs")
}

@Preview
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FacilitiesApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    var currentUser by remember { mutableStateOf<User?>(null) }
    
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route,
        modifier = modifier
    ) {
        // Login Screen
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { email, password ->
                    // Simple mock login - in real app, validate credentials
                    currentUser = MockDataProvider.studentUser
                    navController.navigate(Screen.StudentDashboard.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onRegisterClick = { /* Handle registration */ },
                onQuickLoginClick = { role ->
                    currentUser = when (role) {
                        UserRole.STUDENT -> MockDataProvider.studentUser
                        UserRole.FMO_ADMIN -> MockDataProvider.fmoUser
                        UserRole.PRIVACY_OFFICER -> MockDataProvider.privacyOfficer
                    }
                    val destination = when (role) {
                        UserRole.STUDENT -> Screen.StudentDashboard.route
                        UserRole.FMO_ADMIN -> Screen.FMODashboard.route
                        UserRole.PRIVACY_OFFICER -> Screen.PrivacyDashboard.route
                    }
                    navController.navigate(destination) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Student Dashboard
        composable(Screen.StudentDashboard.route) {
            val myReservations = MockDataProvider.getMyReservations(currentUser?.id ?: "")
            StudentDashboardScreen(
                user = currentUser ?: MockDataProvider.studentUser,
                pendingCount = myReservations.count { it.status == ReservationStatus.SUBMITTED },
                approvedCount = myReservations.count { it.status == ReservationStatus.APPROVED },
                onBackClick = { navController.navigateUp() },
                upcomingCount = MockDataProvider.getApprovedReservations().size,
                recentReservations = myReservations,
                onNewReservationClick = { navController.navigate(Screen.NewReservation.route) },
                onViewCalendarClick = { navController.navigate(Screen.Calendar.route) },
                onMyReservationsClick = { navController.navigate(Screen.AllReservations.route) },
                onNotificationsClick = { navController.navigate(Screen.Notifications.route) }
            )
        }
        
        // FMO Dashboard
        composable(Screen.FMODashboard.route) {
            val pendingReservations = MockDataProvider.getPendingReservations()
            FMODashboardScreen(
                user = currentUser ?: MockDataProvider.fmoUser,
                pendingReviewCount = pendingReservations.size,
                approvedCount = MockDataProvider.getApprovedReservations().size,
                totalReservations = MockDataProvider.reservations.size,
                activeFacilities = MockDataProvider.facilities.size,
                pendingReservations = pendingReservations,
                upcomingEvents = MockDataProvider.getApprovedReservations().take(3),
                onApprovalQueueClick = { navController.navigate(Screen.ApprovalQueue.route) },
                onCalendarClick = { navController.navigate(Screen.Calendar.route) },
                onAllReservationsClick = { navController.navigate(Screen.AllReservations.route) },
                onReportsClick = { /* Navigate to reports */ },
                onNotificationsClick = { navController.navigate(Screen.Notifications.route) },
                onReservationClick = { id -> navController.navigate(Screen.ReservationDetails.createRoute(id)) },
                onApproveClick = { id -> /* Handle approve */ },
                onRejectClick = { id -> /* Handle reject */ }
            )
        }
        
        // Privacy Office Dashboard
        composable(Screen.PrivacyDashboard.route) {
            PrivacyOfficeDashboardScreen(
                user = currentUser ?: MockDataProvider.privacyOfficer,
                totalRecords = 5,
                totalUsers = 4,
                organizations = 4,
                recentActivity = MockDataProvider.auditLogs.take(5),
                onAuditLogsClick = { navController.navigate(Screen.AuditLogs.route) },
                onViewAllDataClick = { navController.navigate(Screen.AllReservations.route) },
                onRetentionReportClick = { /* Navigate to retention report */ },
                onComplianceClick = { /* Navigate to compliance */ },
                onViewFullAuditLogClick = { navController.navigate(Screen.AuditLogs.route) }
            )
        }
        
        // New Reservation
        composable(Screen.NewReservation.route) {
            NewReservationScreen(
                onBackClick = { navController.navigateUp() },
                onSubmit = { reservation ->
                    // Handle submission
                    navController.navigateUp()
                }
            )
        }
        
        // Reservation Details
        composable(
            route = Screen.ReservationDetails.route,
            arguments = listOf(navArgument("id") { type = NavType.StringType })
        ) { backStackEntry ->
            val reservationId = backStackEntry.arguments?.getString("id") ?: ""
            val reservation = MockDataProvider.getReservationById(reservationId)
            
            reservation?.let {
                ReservationDetailsScreen(
                    reservation = it,
                    onBackClick = { navController.navigateUp() },
                    onApproveClick = { /* Handle approve */ },
                    onRejectClick = { /* Handle reject */ },
                    onDocumentClick = { /* Handle document view */ },
                    canModify = currentUser?.role == UserRole.FMO_ADMIN
                )
            }
        }
        
        // All Reservations
        composable(Screen.AllReservations.route) {
            AllReservationsScreen(
                reservations = MockDataProvider.reservations,
                onBackClick = { navController.navigateUp() },
                onReservationClick = { id -> 
                    navController.navigate(Screen.ReservationDetails.createRoute(id))
                }
            )
        }
        
        // Approval Queue
        composable(Screen.ApprovalQueue.route) {
            ApprovalQueueScreen(
                pendingReservations = MockDataProvider.getPendingReservations(),
                onBackClick = { navController.navigateUp() },
                onReservationClick = { id -> 
                    navController.navigate(Screen.ReservationDetails.createRoute(id))
                },
                onApproveClick = { id -> /* Handle approve */ },
                onRejectClick = { id -> /* Handle reject */ },
                onRequestChangesClick = { id -> /* Handle request changes */ }
            )
        }
        
        // Calendar
        composable(Screen.Calendar.route) {
            FacilityCalendarScreen(
                selectedFacility = null,
                reservedDates = setOf(
                    LocalDate.now().plusDays(1),
                    LocalDate.now().plusDays(10),
                    LocalDate.now().plusDays(15),
                    LocalDate.now().plusDays(20)
                ),
                onBackClick = { navController.navigateUp() },
                onDateSelected = { date -> /* Handle date selection */ },
                onFacilityFilterChange = { /* Handle filter change */ }
            )
        }
        
        // Notifications
        composable(Screen.Notifications.route) {
            NotificationsScreen(
                notifications = MockDataProvider.notifications,
                notificationPreferences = NotificationPreferences(),
                onBackClick = { navController.navigateUp() },
                onNotificationClick = { id ->
                    val notification = MockDataProvider.notifications.find { it.id == id }
                    notification?.reservationId?.let { resId ->
                        navController.navigate(Screen.ReservationDetails.createRoute(resId))
                    }
                },
                onPreferenceChange = { /* Handle preference change */ }
            )
        }
        
        // Audit Logs
        composable(Screen.AuditLogs.route) {
            AuditLogsScreen(
                auditLogs = MockDataProvider.auditLogs,
                onBackClick = { navController.navigateUp() },
                onExportCsvClick = { /* Handle CSV export */ },
                onExportPdfClick = { /* Handle PDF export */ }
            )
        }
    }
}
