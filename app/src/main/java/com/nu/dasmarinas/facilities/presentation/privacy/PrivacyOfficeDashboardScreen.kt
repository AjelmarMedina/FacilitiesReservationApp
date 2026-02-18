package com.nu.dasmarinas.facilities.presentation.privacy

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nu.dasmarinas.facilities.data.mock.MockDataProvider
import com.nu.dasmarinas.facilities.domain.model.AuditLog
import com.nu.dasmarinas.facilities.domain.model.User
import com.nu.dasmarinas.facilities.presentation.navigation.Screen

@Preview
@Composable
fun PrivacyOfficeDashboardPreview() {
    PrivacyOfficeDashboardScreen(
        user = MockDataProvider.privacyOfficer,
        totalRecords = 5,
        totalUsers = 4,
        organizations = 4,
        recentActivity = MockDataProvider.auditLogs.take(5),
        onAuditLogsClick = {  },
        onViewAllDataClick = {  },
        onRetentionReportClick = { /* Navigate to retention report */ },
        onComplianceClick = { /* Navigate to compliance */ },
        onViewFullAuditLogClick = {  },
        onBackClick = { },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrivacyOfficeDashboardScreen(
    user: User,
    totalRecords: Int,
    totalUsers: Int,
    organizations: Int,
    recentActivity: List<AuditLog>,
    onAuditLogsClick: () -> Unit,
    onViewAllDataClick: () -> Unit,
    onRetentionReportClick: () -> Unit,
    onComplianceClick: () -> Unit,
    onViewFullAuditLogClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val purpleGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFF7B1FA2),
            Color(0xFF9C27B0)
        )
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Text(
                        "Privacy Office",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClick }) {
                        Icon(
                            Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF7B1FA2)
                )
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFFF5F5F5)),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // User Info Section
            item {
                Text(
                    text = user.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Read-Only Access",
                    fontSize = 14.sp,
                    color = Color(0xFF757575)
                )
            }

            // Statistics Cards
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrivacyStatCard(
                        title = "Total Records",
                        count = totalRecords,
                        icon = Icons.Default.Description,
                        color = Color(0xFF7B1FA2),
                        modifier = Modifier.weight(1f)
                    )
                    PrivacyStatCard(
                        title = "Total Users",
                        count = totalUsers,
                        icon = Icons.Default.People,
                        color = Color(0xFF7B1FA2),
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                PrivacyStatCard(
                    title = "Organizations",
                    count = organizations,
                    icon = Icons.Default.Business,
                    color = Color(0xFF7B1FA2),
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Read-Only Access Notice
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFF9C4)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFFF57C00),
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Read-Only Access",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF212121)
                            )
                            Text(
                                text = "You have view-only access to reservation data and audit logs. You cannot approve, reject, or modify reservations.",
                                fontSize = 12.sp,
                                color = Color(0xFF757575),
                                lineHeight = 16.sp
                            )
                        }
                    }
                }
            }

            // Available Actions
            item {
                Text(
                    text = "Available Actions",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrivacyActionCard(
                        icon = Icons.Default.Assessment,
                        label = "Audit Logs",
                        color = Color(0xFF7B1FA2),
                        onClick = onAuditLogsClick,
                        modifier = Modifier.weight(1f)
                    )
                    PrivacyActionCard(
                        icon = Icons.Default.Visibility,
                        label = "View All Data",
                        color = Color(0xFF00ACC1),
                        onClick = onViewAllDataClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    PrivacyActionCard(
                        icon = Icons.Default.BarChart,
                        label = "Retention Report",
                        color = Color(0xFF1976D2),
                        onClick = onRetentionReportClick,
                        modifier = Modifier.weight(1f)
                    )
                    PrivacyActionCard(
                        icon = Icons.Default.Security,
                        label = "Compliance",
                        color = Color(0xFF388E3C),
                        onClick = onComplianceClick,
                        modifier = Modifier.weight(1f)
                    )
                }
            }

            // Data Privacy Overview
            item {
                Text(
                    text = "Data Privacy Overview",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            item {
                DataPrivacyItem(
                    icon = Icons.Default.Storage,
                    title = "Data Collection",
                    description = "Personal information is collected for facility reservation processing only. All data collection includes explicit consent.",
                    color = Color(0xFF1976D2)
                )
            }

            item {
                DataPrivacyItem(
                    icon = Icons.Default.Security,
                    title = "Data Security",
                    description = "Role-based access controls ensure data is only accessible to authorized personnel. Students can only view their own data.",
                    color = Color(0xFF388E3C)
                )
            }

            item {
                DataPrivacyItem(
                    icon = Icons.Default.Schedule,
                    title = "Data Retention",
                    description = "Reservation data is retained indefinitely for institutional records and audit purposes, as disclosed in the privacy notice.",
                    color = Color(0xFF7B1FA2)
                )
            }

            item {
                DataPrivacyItem(
                    icon = Icons.Default.RemoveRedEye,
                    title = "User Rights",
                    description = "Users can view their submitted data at any time. All reservation submissions require explicit privacy consent.",
                    color = Color(0xFFF57C00)
                )
            }

            // Recent Activity
            item {
                Text(
                    text = "Recent Activity",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            items(recentActivity.take(5)) { log ->
                RecentActivityCard(log = log)
            }

            item {
                Button(
                    onClick = onViewFullAuditLogClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF7B1FA2)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("View Full Audit Log")
                }
            }
        }
    }
}

@Composable
private fun PrivacyStatCard(
    title: String,
    count: Int,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    color: Color,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = 0.1f)
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = color,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    fontSize = 12.sp,
                    color = Color(0xFF757575)
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = count.toString(),
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

@Composable
private fun PrivacyActionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    color: Color,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = color.copy(alpha = 0.1f),
                modifier = Modifier.size(48.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
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
private fun DataPrivacyItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    color: Color
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = color.copy(alpha = 0.1f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = color,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF212121)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    fontSize = 14.sp,
                    color = Color(0xFF757575),
                    lineHeight = 20.sp
                )
            }
        }
    }
}

@Composable
private fun RecentActivityCard(log: AuditLog) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val (iconColor, icon) = when (log.action.name) {
                    "APPROVE" -> Color(0xFF4CAF50) to Icons.Default.CheckCircle
                    "REJECT" -> Color(0xFFD32F2F) to Icons.Default.Cancel
                    "CREATE" -> Color(0xFF1976D2) to Icons.Default.Add
                    else -> Color(0xFF757575) to Icons.Default.Circle
                }
                
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = log.reservationTitle,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "${log.performedBy} • ${log.details}",
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
            }
            Text(
                text = formatDate(log.timestamp),
                fontSize = 12.sp,
                color = Color(0xFF757575)
            )
        }
    }
}

private fun formatDate(timestamp: Long): String {
    // Simple date formatting - replace with proper implementation
    return "Feb 3"
}
