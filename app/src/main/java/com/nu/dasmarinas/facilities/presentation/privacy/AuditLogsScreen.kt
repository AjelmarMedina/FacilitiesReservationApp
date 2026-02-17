package com.nu.dasmarinas.facilities.presentation.privacy

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
import com.nu.dasmarinas.facilities.domain.model.AuditLog
import com.nu.dasmarinas.facilities.domain.model.AuditAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuditLogsScreen(
    auditLogs: List<AuditLog>,
    onBackClick: () -> Unit,
    onExportCsvClick: () -> Unit,
    onExportPdfClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedCategory by remember { mutableStateOf<AuditAction?>(null) }
    var selectedTimePeriod by remember { mutableStateOf("All Time") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "Audit Logs",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "${auditLogs.size} log entries",
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
                    containerColor = Color(0xFF7B1FA2)
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
            // Filters Section
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
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
                                text = "Filters",
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        // Category Filters
                        Text(
                            text = "Category",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            FilterChip(
                                selected = selectedCategory == null,
                                onClick = { selectedCategory = null },
                                label = { Text("All") }
                            )
                            FilterChip(
                                selected = selectedCategory == AuditAction.CREATE,
                                onClick = { selectedCategory = AuditAction.CREATE },
                                label = { Text("Create") }
                            )
                            FilterChip(
                                selected = selectedCategory == AuditAction.UPDATE,
                                onClick = { selectedCategory = AuditAction.UPDATE },
                                label = { Text("Update") }
                            )
                            FilterChip(
                                selected = selectedCategory == AuditAction.APPROVE,
                                onClick = { selectedCategory = AuditAction.APPROVE },
                                label = { Text("Approve") }
                            )
                            FilterChip(
                                selected = selectedCategory == AuditAction.REJECT,
                                onClick = { selectedCategory = AuditAction.REJECT },
                                label = { Text("Reject") }
                            )
                            FilterChip(
                                selected = selectedCategory == AuditAction.VIEW,
                                onClick = { selectedCategory = AuditAction.VIEW },
                                label = { Text("View") }
                            )
                        }

                        // Time Period Filters
                        Text(
                            text = "Time Period",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        )
                        
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("All Time", "Today", "Last 7 Days", "Last 30 Days").forEach { period ->
                                FilterChip(
                                    selected = selectedTimePeriod == period,
                                    onClick = { selectedTimePeriod = period },
                                    label = { Text(period) }
                                )
                            }
                        }
                    }
                }
            }

            // Export Options
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Export Options",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            OutlinedButton(
                                onClick = onExportCsvClick,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Export to CSV")
                            }
                            OutlinedButton(
                                onClick = onExportPdfClick,
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("Export to PDF")
                            }
                        }
                    }
                }
            }

            // Audit Log Entries
            val filteredLogs = auditLogs.filter { log ->
                (selectedCategory == null || log.action == selectedCategory)
            }

            items(filteredLogs) { log ->
                AuditLogEntryCard(log = log)
            }

            // Retention Notice
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF3E5F5)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Audit Log Retention",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF7B1FA2)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "All audit logs are retained indefinitely for compliance and institutional record-keeping purposes. Access to audit logs is restricted to Privacy Officers and authorized FMO administrators.",
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
private fun AuditLogEntryCard(log: AuditLog) {
    val (iconColor, icon, backgroundColor) = when (log.action) {
        AuditAction.CREATE -> Triple(
            Color(0xFF1976D2),
            Icons.Default.Add,
            Color(0xFFE3F2FD)
        )
        AuditAction.UPDATE -> Triple(
            Color(0xFFF57C00),
            Icons.Default.Edit,
            Color(0xFFFFF8E1)
        )
        AuditAction.APPROVE -> Triple(
            Color(0xFF4CAF50),
            Icons.Default.CheckCircle,
            Color(0xFFE8F5E9)
        )
        AuditAction.REJECT -> Triple(
            Color(0xFFD32F2F),
            Icons.Default.Cancel,
            Color(0xFFFFEBEE)
        )
        AuditAction.VIEW -> Triple(
            Color(0xFF00ACC1),
            Icons.Default.Visibility,
            Color(0xFFE0F7FA)
        )
        AuditAction.DELETE -> Triple(
            Color(0xFF757575),
            Icons.Default.Delete,
            Color(0xFFF5F5F5)
        )
    }

    Card(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = iconColor.copy(alpha = 0.2f),
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
                Text(
                    text = "Reservation ${log.action.name.lowercase().capitalize()}",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF212121)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = log.details,
                    fontSize = 13.sp,
                    color = Color(0xFF424242)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Color(0xFF757575)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = log.performedBy,
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Icon(
                        Icons.Default.Schedule,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = Color(0xFF757575)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = formatTimestamp(log.timestamp),
                        fontSize = 12.sp,
                        color = Color(0xFF757575)
                    )
                }
            }
            
            Text(
                text = formatTime(log.timestamp),
                fontSize = 12.sp,
                color = Color(0xFF757575)
            )
        }
    }
}

@Composable
private fun FlowRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    content: @Composable () -> Unit
) {
    // Simple flow row implementation
    Column(modifier = modifier) {
        content()
    }
}

private fun formatTimestamp(timestamp: Long): String {
    // Replace with proper date formatting
    return "Feb 3, 2026"
}

private fun formatTime(timestamp: Long): String {
    // Replace with proper time formatting
    return "06:00 PM"
}
