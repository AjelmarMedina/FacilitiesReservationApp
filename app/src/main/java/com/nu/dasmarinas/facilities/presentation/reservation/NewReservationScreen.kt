package com.nu.dasmarinas.facilities.presentation.reservation

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nu.dasmarinas.facilities.domain.model.*
import java.time.LocalDate
import java.time.LocalTime

@Preview
@Composable
fun NewReservationPreview() {
    NewReservationScreen(
        onBackClick = {},
        onSubmit = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewReservationScreen(
    onBackClick: () -> Unit,
    onSubmit: (Reservation) -> Unit,
    modifier: Modifier = Modifier
) {
    var currentStep by remember { mutableIntStateOf(0) }
    var eventTitle by remember { mutableStateOf("") }
    var eventType by remember { mutableStateOf<EventType?>(null) }
    var eventDescription by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    var startTime by remember { mutableStateOf<LocalTime?>(null) }
    var endTime by remember { mutableStateOf<LocalTime?>(null) }
    var expectedAttendees by remember { mutableStateOf("") }
    var organization by remember { mutableStateOf("") }
    var adviserName by remember { mutableStateOf("") }
    var contactNumber by remember { mutableStateOf("") }
    var selectedFacility by remember { mutableStateOf<Facility?>(null) }
    var selectedEquipment by remember { mutableStateOf<List<Equipment>>(emptyList()) }

    val steps = listOf("Details", "Facility", "Equipment", "Privacy", "Review")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("New Reservation") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1976D2),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Step Indicator
            StepIndicator(
                steps = steps,
                currentStep = currentStep,
                modifier = Modifier.padding(16.dp)
            )

            Divider()

            // Step Content
            when (currentStep) {
                0 -> EventDetailsStep(
                    eventTitle = eventTitle,
                    onEventTitleChange = { eventTitle = it },
                    eventType = eventType,
                    onEventTypeChange = { eventType = it },
                    eventDescription = eventDescription,
                    onEventDescriptionChange = { eventDescription = it },
                    selectedDate = selectedDate,
                    onDateChange = { selectedDate = it },
                    startTime = startTime,
                    onStartTimeChange = { startTime = it },
                    endTime = endTime,
                    onEndTimeChange = { endTime = it },
                    expectedAttendees = expectedAttendees,
                    onAttendeesChange = { expectedAttendees = it },
                    organization = organization,
                    onOrganizationChange = { organization = it },
                    adviserName = adviserName,
                    onAdviserChange = { adviserName = it },
                    contactNumber = contactNumber,
                    onContactChange = { contactNumber = it },
                    modifier = Modifier.weight(1f)
                )
                1 -> FacilitySelectionStep(
                    selectedFacility = selectedFacility,
                    onFacilitySelect = { selectedFacility = it },
                    modifier = Modifier.weight(1f)
                )
                2 -> EquipmentSelectionStep(
                    selectedEquipment = selectedEquipment,
                    onEquipmentToggle = { equipment ->
                        selectedEquipment = if (selectedEquipment.contains(equipment)) {
                            selectedEquipment - equipment
                        } else {
                            selectedEquipment + equipment
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                3 -> PrivacyConsentStep(
                    modifier = Modifier.weight(1f)
                )
                4 -> ReviewStep(
                    eventTitle = eventTitle,
                    eventType = eventType,
                    facility = selectedFacility,
                    date = selectedDate,
                    startTime = startTime,
                    endTime = endTime,
                    attendees = expectedAttendees.toIntOrNull() ?: 0,
                    modifier = Modifier.weight(1f)
                )
            }

            // Navigation Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedButton(
                    onClick = {
                        if (currentStep > 0) currentStep--
                        else onBackClick()
                    }
                ) {
                    Text(if (currentStep == 0) "Cancel" else "Back")
                }

                Button(
                    onClick = {
                        if (currentStep < steps.size - 1) {
                            currentStep++
                        } else {
                            // Submit reservation
                            // onSubmit(createReservation(...))
                        }
                    },
                    enabled = when (currentStep) {
                        0 -> eventTitle.isNotBlank() && eventType != null
                        1 -> selectedFacility != null
                        else -> true
                    }
                ) {
                    Text(if (currentStep < steps.size - 1) "Next" else "Submit")
                }
            }
        }
    }
}

@Composable
private fun StepIndicator(
    steps: List<String>,
    currentStep: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        steps.forEachIndexed { index, step ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = RoundedCornerShape(50),
                    color = when {
                        index < currentStep -> Color(0xFF4CAF50)
                        index == currentStep -> Color(0xFF1976D2)
                        else -> Color(0xFFE0E0E0)
                    },
                    modifier = Modifier.size(32.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(
                            text = (index + 1).toString(),
                            color = if (index <= currentStep) Color.White else Color(0xFF757575),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = step,
                    fontSize = 12.sp,
                    color = if (index == currentStep) Color(0xFF1976D2) else Color(0xFF757575)
                )
            }
        }
    }
}

@Composable
private fun EventDetailsStep(
    eventTitle: String,
    onEventTitleChange: (String) -> Unit,
    eventType: EventType?,
    onEventTypeChange: (EventType) -> Unit,
    eventDescription: String,
    onEventDescriptionChange: (String) -> Unit,
    selectedDate: LocalDate?,
    onDateChange: (LocalDate) -> Unit,
    startTime: LocalTime?,
    onStartTimeChange: (LocalTime) -> Unit,
    endTime: LocalTime?,
    onEndTimeChange: (LocalTime) -> Unit,
    expectedAttendees: String,
    onAttendeesChange: (String) -> Unit,
    organization: String,
    onOrganizationChange: (String) -> Unit,
    adviserName: String,
    onAdviserChange: (String) -> Unit,
    contactNumber: String,
    onContactChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Event Details",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            OutlinedTextField(
                value = eventTitle,
                onValueChange = onEventTitleChange,
                label = { Text("Event Title *") },
                placeholder = { Text("e.g., Tech Summit 2026") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        item {
            Text(
                text = "Event Type *",
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                EventType.values().forEach { type ->
                    FilterChip(
                        selected = eventType == type,
                        onClick = { onEventTypeChange(type) },
                        label = { Text(type.name.replace("_", " ")) }
                    )
                }
            }
        }

        item {
            OutlinedTextField(
                value = eventDescription,
                onValueChange = onEventDescriptionChange,
                label = { Text("Event Description") },
                placeholder = { Text("Describe your event...") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = selectedDate?.toString() ?: "",
                    onValueChange = { },
                    label = { Text("Start Date *") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { /* Show date picker */ }) {
                            Icon(Icons.Default.CalendarToday, null)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = selectedDate?.toString() ?: "",
                    onValueChange = { },
                    label = { Text("End Date *") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { /* Show date picker */ }) {
                            Icon(Icons.Default.CalendarToday, null)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = startTime?.toString() ?: "",
                    onValueChange = { },
                    label = { Text("Start Time *") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { /* Show time picker */ }) {
                            Icon(Icons.Default.Schedule, null)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
                OutlinedTextField(
                    value = endTime?.toString() ?: "",
                    onValueChange = { },
                    label = { Text("End Time *") },
                    readOnly = true,
                    trailingIcon = {
                        IconButton(onClick = { /* Show time picker */ }) {
                            Icon(Icons.Default.Schedule, null)
                        }
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            OutlinedTextField(
                value = expectedAttendees,
                onValueChange = onAttendeesChange,
                label = { Text("Number of expected attendees") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        item {
            Text(
                text = "Organizer Information",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            OutlinedTextField(
                value = organization,
                onValueChange = onOrganizationChange,
                label = { Text("Organization *") },
                placeholder = { Text("Computer Science Society") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        item {
            OutlinedTextField(
                value = adviserName,
                onValueChange = onAdviserChange,
                label = { Text("Adviser Name *") },
                placeholder = { Text("Prof. / Dr. Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        item {
            OutlinedTextField(
                value = contactNumber,
                onValueChange = onContactChange,
                label = { Text("Contact Number *") },
                placeholder = { Text("juan.delacruz@nu-dasma.edu.ph") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
        }

        item {

        }
    }
}

@Composable
private fun FacilitySelectionStep(
    selectedFacility: Facility?,
    onFacilitySelect: (Facility) -> Unit,
    modifier: Modifier = Modifier
) {
    // Mock facilities
    val facilities = remember {
        listOf(
            Facility("1", "Main Gymnasium", "Sports", 500),
            Facility("2", "Function Hall A", "Event Space", 200),
            Facility("3", "Room 301", "Classroom", 50),
            Facility("4", "University Auditorium", "Auditorium", 300)
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Select Facility",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(facilities) { facility ->
            FacilityCard(
                facility = facility,
                isSelected = selectedFacility == facility,
                onClick = { onFacilitySelect(facility) }
            )
        }
    }
}

@Composable
private fun FacilityCard(
    facility: Facility,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) Color(0xFFE3F2FD) else Color.White
        ),
        border = if (isSelected) {
            androidx.compose.foundation.BorderStroke(2.dp, Color(0xFF1976D2))
        } else null
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = facility.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "${facility.type} • Capacity: ${facility.capacity}",
                    fontSize = 14.sp,
                    color = Color(0xFF757575)
                )
            }
            if (isSelected) {
                Icon(
                    Icons.Default.CheckCircle,
                    contentDescription = "Selected",
                    tint = Color(0xFF1976D2)
                )
            }
        }
    }
}

@Composable
private fun EquipmentSelectionStep(
    selectedEquipment: List<Equipment>,
    onEquipmentToggle: (Equipment) -> Unit,
    modifier: Modifier = Modifier
) {
    val equipment = remember {
        listOf(
            Equipment("1", "Sound System (Large)", EquipmentCategory.AUDIO),
            Equipment("2", "LCD Projector (HD)", EquipmentCategory.VISUAL),
            Equipment("3", "Microphone Set", EquipmentCategory.AUDIO),
            Equipment("4", "Whiteboard", EquipmentCategory.OTHER)
        )
    }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Equipment Requested",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        items(equipment) { item ->
            EquipmentCheckCard(
                equipment = item,
                isSelected = selectedEquipment.contains(item),
                onToggle = { onEquipmentToggle(item) }
            )
        }
    }
}

@Composable
private fun EquipmentCheckCard(
    equipment: Equipment,
    isSelected: Boolean,
    onToggle: () -> Unit
) {
    Card(
        onClick = onToggle,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = equipment.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = equipment.category.name,
                    fontSize = 14.sp,
                    color = Color(0xFF757575)
                )
            }
            Checkbox(
                checked = isSelected,
                onCheckedChange = { onToggle() }
            )
        }
    }
}

@Composable
private fun PrivacyConsentStep(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Privacy Notice",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF5F5F5)
                )
            ) {
                Text(
                    text = """
                        This system collects and processes personal information for facility reservation purposes only.
                        
                        • All data is stored securely
                        • Access is limited to authorized personnel
                        • You can view your data at any time
                        • Reservations require explicit consent
                    """.trimIndent(),
                    modifier = Modifier.padding(16.dp),
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun ReviewStep(
    eventTitle: String,
    eventType: EventType?,
    facility: Facility?,
    date: LocalDate?,
    startTime: LocalTime?,
    endTime: LocalTime?,
    attendees: Int,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = "Review Reservation",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        item {
            Card {
                Column(modifier = Modifier.padding(16.dp)) {
                    ReviewItem("Event Title", eventTitle)
                    ReviewItem("Event Type", eventType?.name ?: "")
                    ReviewItem("Facility", facility?.name ?: "")
                    ReviewItem("Date", date?.toString() ?: "")
                    ReviewItem("Time", "$startTime - $endTime")
                    ReviewItem("Attendees", attendees.toString())
                }
            }
        }
    }
}

@Composable
private fun ReviewItem(label: String, value: String) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color(0xFF757575)
        )
        Text(
            text = value,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
