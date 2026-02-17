# Facilities Reservation App - NU Dasmariñas

A comprehensive room and facilities reservation system for National University - Dasmariñas built with Kotlin and Jetpack Compose.

## Features

### User Roles
- **Students**: Create and manage reservations
- **FMO Admin**: Approve/reject reservations, manage facilities
- **Privacy Officer**: View audit logs and manage data privacy

### Core Features
- 🏛️ Facility browsing and reservation
- 📅 Calendar view for availability
- ⏰ Real-time notifications
- 📊 Dashboard with statistics
- 🔐 Role-based access control
- 📝 Audit logging system
- 🔒 GDPR-compliant data management

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Local DB**: Room Database
- **Dependency Injection**: Hilt
- **Navigation**: Compose Navigation
- **Async**: Kotlin Coroutines & Flow
- **Network**: Retrofit + OkHttp
- **Image Loading**: Coil

## Project Structure

```
app/
├── data/
│   ├── local/          # Room database, DAOs
│   ├── remote/         # API services
│   ├── repository/     # Repository implementations
│   └── model/          # Data models
├── domain/
│   ├── model/          # Domain models
│   ├── repository/     # Repository interfaces
│   └── usecase/        # Business logic
├── presentation/
│   ├── auth/           # Login, Registration
│   ├── dashboard/      # Role-specific dashboards
│   ├── reservation/    # Reservation flows
│   ├── calendar/       # Calendar view
│   ├── privacy/        # Privacy office
│   └── common/         # Shared components
└── di/                 # Dependency injection modules
```

## Setup Instructions

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17
- Minimum SDK: 24
- Target SDK: 34

### Installation

1. Clone the repository
2. Open in Android Studio
3. Sync Gradle files
4. Run the app

### Build Variants
- `debug`: Development build
- `release`: Production build

## Configuration

Update `local.properties` with your API endpoint:
```properties
api.base.url=https://your-api-endpoint.com/
```

## Testing

Run tests:
```bash
./gradlew test
./gradlew connectedAndroidTest
```

## License

© 2026 National University - Dasmariñas

## Contributors

- Development Team
- Design Team
- QA Team
