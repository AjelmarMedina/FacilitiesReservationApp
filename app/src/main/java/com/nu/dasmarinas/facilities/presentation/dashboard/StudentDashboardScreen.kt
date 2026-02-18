package com.nu.dasmarinas.facilities.presentation.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nu.dasmarinas.facilities.data.mock.MockDataProvider
import com.nu.dasmarinas.facilities.domain.model.Reservation
import com.nu.dasmarinas.facilities.domain.model.ReservationStatus
import com.nu.dasmarinas.facilities.domain.model.User
import com.nu.dasmarinas.facilities.presentation.navigation.Screen

@Preview
@Composable
fun StudentDashboardPreview() {
    StudentDashboardScreen(
        user = MockDataProvider.studentUser,
        pendingCount = 0,
        approvedCount = 0,
        upcomingCount = MockDataProvider.getApprovedReservations().size,
        recentReservations = MockDataProvider.getMyReservations(MockDataProvider.studentUser.id),
        onNewReservationClick = {  },
        onBackClick = {  },
        onViewCalendarClick = {  },
        onMyReservationsClick = {  },
        onNotificationsClick = {  }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboardScreen(
    user: User,
    pendingCount: Int,
    approvedCount: Int,
    upcomingCount: Int,
    recentReservations: List<Reservation>,
    onBackClick: () -> Unit,
    onNewReservationClick: () -> Unit,
    onViewCalendarClick: () -> Unit,
    onMyReservationsClick: () -> Unit,
    onNotificationsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val blueGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF1976D2),
            Color(0xFF2196F3)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { },
                actions = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.width(256.dp))
                    IconButton(onClick = onNotificationsClick) {
                        BadgedBox(
                            badge = {
                                if (pendingCount > 0) {
                                    Badge { Text("1") }
                                }
                            }
                        ) {
                            Icon(
                                Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White
                            )
                        }
                    }
                    IconButton(onClick = { /* Profile */ }) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Profile",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                ),
                modifier = Modifier.background(blueGradient)
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(bottom = 80.dp)
        ) {
            item {
                // Header Section
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(blueGradient)
                        .padding(24.dp)
                ) {
                    Column {
                        Text(
                            text = "Welcome back,",
                            fontSize = 16.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                        Text(
                            text = user.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Text(
                            text = user.organization ?: "",
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )

                        Spacer(modifier = Modifier.height(24.dp))

                        // Status Cards
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            StatusCard(
                                title = "Pending",
                                count = pendingCount,
                                modifier = Modifier.weight(1f)
                            )
                            StatusCard(
                                title = "Approved",
                                count = approvedCount,
                                modifier = Modifier.weight(1f)
                            )
                            StatusCard(
                                title = "Upcoming",
                                count = upcomingCount,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Quick Actions
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {
                        Text(
                            text = "Quick Actions",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            QuickActionButton(
                                icon = Icons.Default.Add,
                                label = "New Reservation",
                                onClick = onNewReservationClick,
                                modifier = Modifier.weight(1f)
                            )
                            QuickActionButton(
                                icon = Icons.Default.CalendarMonth,
                                label = "View Calendar",
                                onClick = onViewCalendarClick,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }

            // Your Reservations Section
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Your Reservations",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    TextButton(onClick = onMyReservationsClick) {
                        Text(
                            text = "View All",
                            color = Color(0xFF1976D2)
                        )
                    }
                }
            }

            if (recentReservations.isEmpty()) {
                item {
                    EmptyState()
                }
            } else {
                items(recentReservations.take(3)) { reservation ->
                    ReservationCard(
                        reservation = reservation,
                        onClick = { /* Navigate to details */ },
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusCard(
    title: String,
    count: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.2f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = count.toString(),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Composable
private fun QuickActionButton(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFF1976D2),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = label,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF212121)
            )
        }
    }
}

@Composable
private fun ReservationCard(
    reservation: Reservation,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = reservation.eventTitle,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF212121)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = reservation.facility.name,
                        fontSize = 14.sp,
                        color = Color(0xFF757575)
                    )
                }
                
                StatusChip(status = reservation.status.name)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.CalendarToday,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF757575)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = reservation.date.toString(),
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Schedule,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = Color(0xFF757575)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "${reservation.startTime} - ${reservation.endTime}",
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
            }
        }
    }
}

@Composable
private fun StatusChip(status: String) {
    val (backgroundColor, textColor) = when (status.uppercase()) {
        "SUBMITTED" -> Color(0xFFE3F2FD) to Color(0xFF1976D2)
        "UNDER_REVIEW" -> Color(0xFFFFF8E1) to Color(0xFFF57C00)
        "APPROVED" -> Color(0xFFE8F5E9) to Color(0xFF388E3C)
        "REJECTED" -> Color(0xFFFFEBEE) to Color(0xFFD32F2F)
        else -> Color(0xFFF5F5F5) to Color(0xFF757575)
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Text(
            text = status.lowercase().replace("_", " ").capitalize(),
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = textColor
        )
    }
}

@Composable
private fun EmptyState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.EventNote,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color(0xFFBDBDBD)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No reservations yet",
            fontSize = 16.sp,
            color = Color(0xFF757575)
        )
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { /* Create first reservation */ }) {
            Text(
                text = "Create your first reservation",
                color = Color(0xFF1976D2)
            )
        }
    }
}
