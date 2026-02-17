package com.nu.dasmarinas.facilities.presentation.notifications

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
import com.nu.dasmarinas.facilities.domain.model.Notification
import com.nu.dasmarinas.facilities.domain.model.NotificationPreferences

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreen(
    notifications: List<Notification>,
    notificationPreferences: NotificationPreferences,
    onBackClick: () -> Unit,
    onNotificationClick: (String) -> Unit,
    onPreferenceChange: (NotificationPreferences) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "Notifications",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "${notifications.count { !it.isRead }} total notification(s)",
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
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (notifications.isEmpty()) {
                item {
                    EmptyNotificationsState()
                }
            } else {
                items(notifications) { notification ->
                    NotificationCard(
                        notification = notification,
                        onClick = { onNotificationClick(notification.id) }
                    )
                }
            }

            // Notification Preferences Section
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
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
                                Icons.Default.Settings,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Notification Preferences",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        PreferenceItem(
                            title = "Reservation status updates",
                            isEnabled = notificationPreferences.reservationStatusUpdates,
                            onToggle = {
                                onPreferenceChange(
                                    notificationPreferences.copy(
                                        reservationStatusUpdates = it
                                    )
                                )
                            }
                        )

                        PreferenceItem(
                            title = "Event reminders",
                            isEnabled = notificationPreferences.eventReminders,
                            onToggle = {
                                onPreferenceChange(
                                    notificationPreferences.copy(
                                        eventReminders = it
                                    )
                                )
                            }
                        )

                        PreferenceItem(
                            title = "System announcements",
                            isEnabled = notificationPreferences.systemAnnouncements,
                            onToggle = {
                                onPreferenceChange(
                                    notificationPreferences.copy(
                                        systemAnnouncements = it
                                    )
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun NotificationCard(
    notification: Notification,
    onClick: () -> Unit
) {
    val (iconColor, icon, backgroundColor) = when (notification.type) {
        com.nu.dasmarinas.facilities.domain.model.NotificationType.RESERVATION_STATUS_UPDATE -> Triple(
            Color(0xFF1976D2),
            Icons.Default.EventNote,
            if (notification.isRead) Color.White else Color(0xFFE3F2FD)
        )
        com.nu.dasmarinas.facilities.domain.model.NotificationType.EVENT_REMINDER -> Triple(
            Color(0xFFF57C00),
            Icons.Default.Notifications,
            if (notification.isRead) Color.White else Color(0xFFFFF8E1)
        )
        com.nu.dasmarinas.facilities.domain.model.NotificationType.SYSTEM_ANNOUNCEMENT -> Triple(
            Color(0xFF7B1FA2),
            Icons.Default.Campaign,
            if (notification.isRead) Color.White else Color(0xFFF3E5F5)
        )
    }

    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        elevation = CardDefaults.cardElevation(if (notification.isRead) 1.dp else 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = iconColor.copy(alpha = 0.1f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = iconColor,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = notification.title,
                        fontSize = 14.sp,
                        fontWeight = if (notification.isRead) FontWeight.Normal else FontWeight.SemiBold,
                        color = Color(0xFF212121)
                    )
                    if (!notification.isRead) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .padding(start = 4.dp)
                        ) {
                            Surface(
                                shape = androidx.compose.foundation.shape.CircleShape,
                                color = Color(0xFF1976D2),
                                modifier = Modifier.size(8.dp)
                            ) {}
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = notification.message,
                    fontSize = 13.sp,
                    color = Color(0xFF757575),
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = formatTimestamp(notification.timestamp),
                    fontSize = 12.sp,
                    color = Color(0xFF9E9E9E)
                )
            }
        }
    }
}

@Composable
private fun PreferenceItem(
    title: String,
    isEnabled: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            color = Color(0xFF212121)
        )
        Switch(
            checked = isEnabled,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF1976D2),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFBDBDBD)
            )
        )
    }
}

@Composable
private fun EmptyNotificationsState() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            Icons.Default.Notifications,
            contentDescription = null,
            modifier = Modifier.size(64.dp),
            tint = Color(0xFFBDBDBD)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "No notifications yet",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF757575)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "You'll see updates about your reservations here",
            fontSize = 14.sp,
            color = Color(0xFF9E9E9E)
        )
    }
}

private fun formatTimestamp(timestamp: Long): String {
    // Replace with proper date/time formatting
    val now = System.currentTimeMillis()
    val diff = now - timestamp
    val hours = diff / (1000 * 60 * 60)
    
    return when {
        hours < 1 -> "Just now"
        hours < 24 -> "$hours hours ago"
        hours < 48 -> "Yesterday"
        else -> "${hours / 24} days ago"
    }
}
