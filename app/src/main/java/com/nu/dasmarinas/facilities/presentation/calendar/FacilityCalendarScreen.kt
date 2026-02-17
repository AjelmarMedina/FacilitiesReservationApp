package com.nu.dasmarinas.facilities.presentation.calendar

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.YearMonth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FacilityCalendarScreen(
    selectedFacility: String? = null,
    reservedDates: Set<LocalDate> = emptySet(),
    onBackClick: () -> Unit,
    onDateSelected: (LocalDate) -> Unit,
    onFacilityFilterChange: (String?) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentMonth by remember { mutableStateOf(YearMonth.now()) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Column {
                        Text(
                            "Facility Calendar",
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            "View facility availability across campus",
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
                .background(Color(0xFFF5F5F5))
        ) {
            // Facility Filter
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
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Filter by Facility",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = selectedFacility ?: "",
                        onValueChange = onFacilityFilterChange,
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = { Text("Select facility...") },
                        leadingIcon = {
                            Icon(Icons.Default.LocationOn, contentDescription = null)
                        },
                        singleLine = true
                    )
                }
            }

            // Calendar Card
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
                    // Month Navigation
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = { currentMonth = currentMonth.minusMonths(1) }
                        ) {
                            Icon(
                                Icons.Default.ChevronLeft,
                                contentDescription = "Previous month"
                            )
                        }
                        Text(
                            text = "${currentMonth.month.name} ${currentMonth.year}",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        IconButton(
                            onClick = { currentMonth = currentMonth.plusMonths(1) }
                        ) {
                            Icon(
                                Icons.Default.ChevronRight,
                                contentDescription = "Next month"
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Day Headers
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        listOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat").forEach { day ->
                            Text(
                                text = day,
                                modifier = Modifier.weight(1f),
                                textAlign = TextAlign.Center,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF757575)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Calendar Grid
                    CalendarGrid(
                        yearMonth = currentMonth,
                        reservedDates = reservedDates,
                        selectedDate = selectedDate,
                        onDateSelected = {
                            selectedDate = it
                            onDateSelected(it)
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Legend
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "Privacy Notice:",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1976D2)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "This calendar shows approved and pending facility reservations. Organizer details are hidden based on your role.",
                        fontSize = 12.sp,
                        color = Color(0xFF757575),
                        lineHeight = 16.sp
                    )
                    
                    Spacer(modifier = Modifier.height(12.dp))
                    
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        LegendItem(Color(0xFFE3F2FD), "Today")
                        LegendItem(Color(0xFFFFF8E1), "Has reservations")
                        LegendItem(Color(0xFF1976D2), "Selected")
                    }
                }
            }
        }
    }
}

@Composable
private fun CalendarGrid(
    yearMonth: YearMonth,
    reservedDates: Set<LocalDate>,
    selectedDate: LocalDate?,
    onDateSelected: (LocalDate) -> Unit
) {
    val firstDayOfMonth = yearMonth.atDay(1)
    val firstDayOfWeek = firstDayOfMonth.dayOfWeek.value % 7
    val daysInMonth = yearMonth.lengthOfMonth()
    val today = LocalDate.now()

    LazyVerticalGrid(
        columns = GridCells.Fixed(7),
        modifier = Modifier.height(300.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        // Empty cells for days before the first day of month
        items(firstDayOfWeek) {
            Box(modifier = Modifier.aspectRatio(1f))
        }

        // Days of the month
        items(daysInMonth) { day ->
            val date = yearMonth.atDay(day + 1)
            val isToday = date == today
            val isSelected = date == selectedDate
            val hasReservations = reservedDates.contains(date)

            CalendarDayCell(
                day = day + 1,
                isToday = isToday,
                isSelected = isSelected,
                hasReservations = hasReservations,
                onClick = { onDateSelected(date) }
            )
        }
    }
}

@Composable
private fun CalendarDayCell(
    day: Int,
    isToday: Boolean,
    isSelected: Boolean,
    hasReservations: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = when {
        isSelected -> Color(0xFF1976D2)
        isToday -> Color(0xFFE3F2FD)
        hasReservations -> Color(0xFFFFF8E1)
        else -> Color.Transparent
    }

    val textColor = when {
        isSelected -> Color.White
        else -> Color(0xFF212121)
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(backgroundColor, CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = day.toString(),
                fontSize = 14.sp,
                color = textColor,
                fontWeight = if (isSelected || isToday) FontWeight.Bold else FontWeight.Normal
            )
            if (hasReservations && !isSelected) {
                Box(
                    modifier = Modifier
                        .size(4.dp)
                        .background(Color(0xFFF57C00), CircleShape)
                )
            }
        }
    }
}

@Composable
private fun LegendItem(color: Color, label: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
                .background(color, CircleShape)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF757575)
        )
    }
}
