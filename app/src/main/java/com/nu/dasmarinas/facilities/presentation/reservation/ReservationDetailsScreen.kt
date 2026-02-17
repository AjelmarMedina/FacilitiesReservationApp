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
import com.nu.dasmarinas.facilities.domain.model.Document

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReservationDetailsScreen(
    reservation: Reservation,
    onBackClick: () -> Unit,
    onApproveClick: () -> Unit,
    onRejectClick: () -> Unit,
    onDocumentClick: (Document) -> Unit,
    canModify: Boolean = false,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "Reservation Details",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "ID: ${reservation.id}",
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
        },
        bottomBar = {
            if (canModify && reservation.status == ReservationStatus.UNDER_REVIEW) {
                BottomAppBar(
                    containerColor = Color.White,
                    contentPadding = PaddingValues(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        OutlinedButton(
                            onClick = onRejectClick,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.outlinedButtonColors(
                                contentColor = Color(0xFFD32F2F)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Close, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Reject")
                        }
                        Button(
                            onClick = onApproveClick,
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF4CAF50)
                            ),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Approve")
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Status Badge
            item {
                val (backgroundColor, textColor) = when (reservation.status) {
                    ReservationStatus.UNDER_REVIEW -> Color(0xFFFFF8E1) to Color(0xFFF57C00)
                    ReservationStatus.APPROVED -> Color(0xFFE8F5E9) to Color(0xFF388E3C)
                    ReservationStatus.REJECTED -> Color(0xFFFFEBEE) to Color(0xFFD32F2F)
                    else -> Color(0xFFE3F2FD) to Color(0xFF1976D2)
                }
                
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = backgroundColor,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = textColor
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Current Status",
                                fontSize = 12.sp,
                                color = textColor.copy(alpha = 0.7f)
                            )
                            Text(
                                text = reservation.status.name.replace("_", " ").uppercase(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = textColor
                            )
                        }
                    }
                }
            }

            // Event Information
            item {
                SectionCard(title = "Event Information") {
                    InfoRow(label = "Event Title", value = reservation.eventTitle)
                    InfoRow(
                        label = "Event Type",
                        value = reservation.eventType.name.replace("_", " ")
                    )
                    InfoRow(label = "Description", value = reservation.description)
                    InfoRow(label = "Date", value = reservation.date.toString())
                    InfoRow(
                        label = "Time",
                        value = "${reservation.startTime} to ${reservation.endTime}"
                    )
                    InfoRow(
                        label = "Expected Attendees",
                        value = "${reservation.expectedAttendees} people"
                    )
                }
            }

            // Facility & Equipment
            item {
                SectionCard(title = "Facility & Equipment") {
                    InfoRow(label = "Facility", value = reservation.facility.name)
                    
                    if (reservation.equipment.isNotEmpty()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Equipment Requested",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF757575)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        reservation.equipment.forEach { equipment ->
                            Row(
                                modifier = Modifier.padding(vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp),
                                    tint = Color(0xFF4CAF50)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = equipment.name,
                                    fontSize = 14.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = equipment.category.name,
                                    fontSize = 12.sp,
                                    color = Color(0xFF757575)
                                )
                            }
                        }
                    }
                }
            }

            // Organizer Information
            item {
                SectionCard(title = "Organizer Information") {
                    InfoRow(label = "Name", value = reservation.organizer.name)
                    InfoRow(label = "ID", value = reservation.organizer.idNumber)
                    InfoRow(label = "Email", value = reservation.organizer.email)
                    InfoRow(label = "Contact Number", value = reservation.organizer.contactNumber)
                    InfoRow(label = "Organization", value = reservation.organizer.organization)
                    reservation.organizer.adviser?.let { adviser ->
                        InfoRow(label = "Adviser", value = adviser)
                    }
                }
            }

            // Attached Documents
            if (reservation.attachedDocuments.isNotEmpty()) {
                item {
                    SectionCard(title = "Attached Documents") {
                        reservation.attachedDocuments.forEach { document ->
                            DocumentItem(
                                document = document,
                                onClick = { onDocumentClick(document) }
                            )
                        }
                    }
                }
            }

            // Submission Info
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Submitted",
                            fontSize = 12.sp,
                            color = Color(0xFF757575)
                        )
                        Text(
                            text = formatTimestamp(reservation.submittedAt),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Last Updated",
                            fontSize = 12.sp,
                            color = Color(0xFF757575)
                        )
                        Text(
                            text = formatTimestamp(reservation.lastUpdatedAt),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
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
                    Icons.Default.Info,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                    tint = Color(0xFF1976D2)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF212121)
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Column(
        modifier = Modifier.padding(vertical = 6.dp)
    ) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF757575)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = value,
            fontSize = 14.sp,
            color = Color(0xFF212121)
        )
    }
}

@Composable
private fun DocumentItem(
    document: Document,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF5F5F5)
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                Icons.Default.Description,
                contentDescription = null,
                tint = Color(0xFF1976D2)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = document.name,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = document.type.name,
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }
            TextButton(onClick = onClick) {
                Text("View")
            }
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    // Replace with proper date formatting
    return "Feb 1, 2026, 06:00 PM"
}
