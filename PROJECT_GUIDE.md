# NU Dasmariñas Facilities Reservation App - Complete Setup Guide

## Overview

This is a comprehensive Android application for managing facility reservations at National University - Dasmariñas. The app supports three user roles with distinct functionalities:

### User Roles
1. **Student** - Create and manage facility reservations
2. **FMO Admin** - Approve/reject reservations and manage facilities
3. **Privacy Officer** - View audit logs and manage data privacy compliance

## Architecture

### Tech Stack
- **Language**: Kotlin 1.9.20
- **UI Framework**: Jetpack Compose with Material 3
- **Architecture Pattern**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Database**: Room
- **Asynchronous**: Kotlin Coroutines & Flow
- **Navigation**: Jetpack Compose Navigation
- **Network**: Retrofit + OkHttp (ready for backend integration)

### Project Structure

```
app/
├── data/
│   ├── local/
│   │   ├── dao/           # Room DAOs for database operations
│   │   ├── entity/        # Room entities
│   │   ├── converter/     # Type converters for Room
│   │   └── FacilitiesDatabase.kt
│   ├── remote/            # API services (ready for implementation)
│   └── repository/        # Repository implementations
│
├── domain/
│   ├── model/             # Domain models (User, Reservation, etc.)
│   └── repository/        # Repository interfaces
│
├── presentation/
│   ├── auth/              # Login, Registration screens
│   ├── dashboard/         # Role-specific dashboards
│   ├── reservation/       # Reservation creation and management
│   ├── calendar/          # Calendar view
│   ├── navigation/        # Navigation setup
│   └── common/            # Shared components
│
├── di/                    # Dependency injection modules
└── ui/
    └── theme/             # Material 3 theming
```

## Key Features Implemented

### 1. Authentication System
- Login screen with role-based authentication
- Quick demo login for testing different roles
- Email/password validation

### 2. Student Dashboard
- View reservation statistics (Pending, Approved, Upcoming)
- Quick actions for creating reservations and viewing calendar
- List of recent reservations with status indicators

### 3. FMO Dashboard
- Comprehensive statistics (Pending Review, Approved, Total, Active Facilities)
- Quick access to Approval Queue, Calendar, Reports
- Inline approval/rejection of pending reservations
- Upcoming events overview

### 4. New Reservation Flow
Multi-step reservation creation with:
- **Step 1**: Event details (title, type, description, date/time, attendees)
- **Step 2**: Facility selection
- **Step 3**: Equipment selection
- **Step 4**: Privacy consent
- **Step 5**: Review and submit

### 5. Database Schema
Complete Room database with:
- Users table
- Reservations table
- Facilities table
- Equipment table
- Audit logs table
- Notifications table

### 6. Material 3 Design
- Custom color scheme matching NU branding (Blue #1976D2)
- Gradient backgrounds
- Modern card-based UI
- Status chips with color coding
- Responsive layouts

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog (2023.1.1) or later
- JDK 17
- Minimum Android SDK 24
- Target Android SDK 34

### Installation Steps

1. **Extract the project folder**
   - Extract all files to your desired location

2. **Open in Android Studio**
   - File → Open → Select the `FacilitiesReservationApp` folder
   - Wait for Gradle sync to complete

3. **Verify Configuration**
   - Check `build.gradle.kts` files are properly configured
   - Ensure all dependencies are downloaded

4. **Build the Project**
   ```bash
   ./gradlew clean build
   ```

5. **Run the App**
   - Connect an Android device or start an emulator
   - Click Run (or Shift + F10)

## Testing Different User Roles

The app includes quick demo login buttons for testing:

### Student Role
- Can create new reservations
- View personal reservations
- Check facility calendar
- Receive notifications

### FMO Admin Role
- Approve/reject reservations
- View all reservations across the system
- Access approval queue
- Generate reports

### Privacy Officer Role
- View audit logs
- Access data privacy dashboard
- Monitor compliance
- Generate retention reports

## Database Models

### Reservation
```kotlin
- id: String
- eventTitle: String
- eventType: EventType (STUDENT_ORG, ACADEMIC, UNIVERSITY)
- description: String
- facility: Facility
- date: LocalDate
- startTime/endTime: LocalTime
- expectedAttendees: Int
- organizer: Organizer
- equipment: List<Equipment>
- status: ReservationStatus
- priority: Priority
```

### Facility
```kotlin
- id: String
- name: String
- type: String
- capacity: Int
- imageUrl: String?
- features: List<String>
```

### Equipment
```kotlin
- id: String
- name: String
- category: EquipmentCategory (AUDIO, VISUAL, etc.)
- isAvailable: Boolean
```

## UI Color Scheme

### Primary Colors
- Primary: `#1976D2` (NU Blue)
- Primary Container: `#E3F2FD`
- Secondary: `#00BCD4`
- Tertiary: `#4CAF50` (Success/Approved)
- Error: `#D32F2F` (Rejected)

### Status Colors
- **Submitted**: Blue (`#E3F2FD` background, `#1976D2` text)
- **Under Review**: Orange (`#FFF8E1` background, `#F57C00` text)
- **Approved**: Green (`#E8F5E9` background, `#388E3C` text)
- **Rejected**: Red (`#FFEBEE` background, `#D32F2F` text)

## Next Steps for Development

### Backend Integration
1. Implement API services in `data/remote/`
2. Create repository implementations
3. Add authentication tokens
4. Implement error handling

### Additional Features to Implement
1. **Calendar View** - Full calendar with booking visualization
2. **Reservation Details** - Detailed view with documents
3. **Approval Queue** - Filterable list with bulk actions
4. **Notifications** - Real-time push notifications
5. **Audit Logs** - Comprehensive logging system
6. **Reports** - PDF export and analytics
7. **Privacy Dashboard** - GDPR compliance tools

### Recommended Improvements
1. Add unit tests for ViewModels
2. Implement integration tests for database
3. Add UI tests for critical flows
4. Implement proper error handling
5. Add loading states
6. Implement offline-first approach
7. Add data sync mechanism

## File Descriptions

### Core Files
- `MainActivity.kt` - Entry point with Hilt integration
- `FacilitiesApplication.kt` - Application class for Hilt
- `Navigation.kt` - Navigation graph setup
- `FacilitiesDatabase.kt` - Room database configuration

### UI Screens
- `LoginScreen.kt` - Authentication UI
- `StudentDashboardScreen.kt` - Student home screen
- `FMODashboardScreen.kt` - FMO admin home screen
- `NewReservationScreen.kt` - Multi-step reservation form

### Data Layer
- `Entities.kt` - Room database entities
- `Daos.kt` - Database access objects
- `Repositories.kt` - Repository interfaces
- Domain models in `domain/model/`

## Troubleshooting

### Common Issues

**Gradle sync fails**
- Ensure you have JDK 17 installed
- Check internet connection for dependency downloads
- Clear Gradle cache: `./gradlew clean`

**Room schema errors**
- Delete app data and reinstall
- Increment database version in `FacilitiesDatabase.kt`

**Compose preview not showing**
- Rebuild project
- Invalidate caches and restart Android Studio

## Support & Documentation

- **Android Developers**: https://developer.android.com/
- **Jetpack Compose**: https://developer.android.com/jetpack/compose
- **Material 3**: https://m3.material.io/
- **Hilt**: https://dagger.dev/hilt/

## License

© 2026 National University - Dasmariñas
All rights reserved.

## Contributors

This project structure was designed for educational purposes and production use at NU Dasmariñas.

---

**Version**: 1.0.0
**Last Updated**: February 2026
**Minimum SDK**: 24 (Android 7.0)
**Target SDK**: 34 (Android 14)
