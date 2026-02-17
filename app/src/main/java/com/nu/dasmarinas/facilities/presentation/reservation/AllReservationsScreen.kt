package com.nu.dasmarinas.facilities.presentation.reservation

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nu.dasmarinas.facilities.domain.model.Reservation
import com.nu.dasmarinas.facilities.domain.model.ReservationStatus

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllReservationsScreen(
    reservations: List<Reservation>,
    onBackClick: () -> Unit,
    onReservationClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedStatus by remember { mutableStateOf<ReservationStatus?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "All Reservations",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "${reservations.size} reservation(s) found",
                            fontSize = 12.sp,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2)
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search Bar
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Search by event or facility...") },
                    leadingIcon = {
                        Icon(Icons.Default.Search, contentDescription = null)
                    },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { searchQuery = "" }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
            }

            // Status Filter
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(2.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.FilterList,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Filter by Status",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedStatus == null,
                            onClick = { selectedStatus = null },
                            label = { Text("All") }
                        )
                        FilterChip(
                            selected = selectedStatus == ReservationStatus.SUBMITTED,
                            onClick = { selectedStatus = ReservationStatus.SUBMITTED },
                            label = { Text("submitted") }
                        )
                        FilterChip(
                            selected = selectedStatus == ReservationStatus.UNDER_REVIEW,
                            onClick = { selectedStatus = ReservationStatus.UNDER_REVIEW },
                            label = { Text("under review") }
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = selectedStatus == ReservationStatus.APPROVED,
                            onClick = { selectedStatus = ReservationStatus.APPROVED },
                            label = { Text("approved") }
                        )
                        FilterChip(
                            selected = selectedStatus == ReservationStatus.REJECTED,
                            onClick = { selectedStatus = ReservationStatus.REJECTED },
                            label = { Text("rejected") }
                        )
                        FilterChip(
                            selected = selectedStatus == ReservationStatus.CANCELLED,
                            onClick = { selectedStatus = ReservationStatus.CANCELLED },
                            label = { Text("cancelled") }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Reservations List
            val filteredReservations = reservations.filter { reservation ->
                (searchQuery.isEmpty() || 
                 reservation.eventTitle.contains(searchQuery, ignoreCase = true) ||
                 reservation.facility.name.contains(searchQuery, ignoreCase = true)) &&
                (selectedStatus == null || reservation.status == selectedStatus)
            }

            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(filteredReservations) { reservation ->
                    ReservationListCard(
                        reservation = reservation,
                        onClick = { onReservationClick(reservation.id) }
                    )
                }

                if (filteredReservations.isEmpty()) {
                    item {
                        EmptyReservationsList()
                    }
                }
            }
        }
    }
}

@Composable
private fun ReservationListCard(
    reservation: Reservation,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header
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
                    Text(
                        text = "By: ${reservation.organizer.name} (${reservation.organizer.organization})",
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
                
                StatusBadge(status = reservation.status)
            }

            Spacer(modifier = Modifier.height(12.dp))
            Divider(color = Color(0xFFE0E0E0))
            Spacer(modifier = Modifier.height(12.dp))

            // Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DetailItem(
                    icon = Icons.Default.CalendarToday,
                    text = reservation.date.toString()
                )
                DetailItem(
                    icon = Icons.Default.Schedule,
                    text = "${reservation.startTime} - ${reservation.endTime}"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DetailItem(
                    icon = Icons.Default.Event,
                    text = reservation.eventType.name.replace("_", " ")
                )
                DetailItem(
                    icon = Icons.Default.People,
                    text = "${reservation.expectedAttendees} attendees"
                )
            }

            // Rejection Reason (if applicable)
            if (reservation.status == ReservationStatus.REJECTED && reservation.rejectionReason != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEBEE)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Rejection Reason:",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFFD32F2F)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = reservation.rejectionReason,
                            fontSize = 12.sp,
                            color = Color(0xFF757575),
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun StatusBadge(status: ReservationStatus) {
    val (backgroundColor, textColor, icon) = when (status) {
        ReservationStatus.SUBMITTED -> Triple(
            Color(0xFFE3F2FD),
            Color(0xFF1976D2),
            Icons.Default.HourglassEmpty
        )
        ReservationStatus.UNDER_REVIEW -> Triple(
            Color(0xFFFFF8E1),
            Color(0xFFF57C00),
            Icons.Default.Schedule
        )
        ReservationStatus.APPROVED -> Triple(
            Color(0xFFE8F5E9),
            Color(0xFF388E3C),
            Icons.Default.CheckCircle
        )
        ReservationStatus.REJECTED -> Triple(
            Color(0xFFFFEBEE),
            Color(0xFFD32F2F),
            Icons.Default.Cancel
        )
        ReservationStatus.CANCELLED -> Triple(
            Color(0xFFF5F5F5),
            Color(0xFF757575),
            Icons.Default.Block
        )
    }

    Surface(
        shape = RoundedCornerShape(12.dp),
        color = backgroundColor
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(14.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = status.name.lowercase().replace("_", " "),
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}

@Composable
private fun DetailItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    text: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(16.dp),
            tint = Color(0xFF757575)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = text,
            fontSize = 13.sp,
            color = Color(0xFF757575)
        )
    }
}

@Composable
private fun EmptyReservationsList() {
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
            text = "No reservations found",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF757575)
        )
        Text(
            text = "Try adjusting your filters",
            fontSize = 14.sp,
            color = Color(0xFF9E9E9E)
        )
    }
}
