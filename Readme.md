# Pokedex Android App

A modern Android application that serves as a Pokedex, built using Kotlin, Jetpack Compose, and Clean Architecture.

## Features

- **Pokemon List**: Browse through a comprehensive list of Pokemon.
- **Detailed Information**: View detailed stats, types, and abilities for each Pokemon.
- **Search and Filter**: Easily find your favorite Pokemon.
- **Offline Support**: Access previously viewed Pokemon data offline.

## Architecture

The project follows Clean Architecture principles and is divided into several modules:

- **`:app`**: The main entry point of the application, containing the UI (Jetpack Compose) and DI setup.
- **`:domain`**: Contains the business logic, use cases, and domain entities. It is a pure Kotlin/Android library with no dependencies on the data layer.
- **`:data`**: Implements the repositories defined in the domain layer. It handles data sourcing from network (API) and local storage (Database).
- **`:shared`**: Contains common utilities, constants, and shared components used across different modules.

## Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Dependency Injection**: Hilt/Dagger (Assumed based on common practices)
- **Networking**: [Retrofit](https://square.github.io/retrofit/) / [OkHttp](https://square.github.io/okhttp/)
- **Local Database**: [Room](https://developer.android.com/training/data-storage/room)
- **Asynchronous Programming**: [Coroutines](https://kotlinlang.org/docs/coroutines-overview.html) & [Flow](https://kotlinlang.org/docs/flow.html)
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/)

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Sync Gradle and build the project.
4. Run the app on an emulator or a physical device.

## License

```text
Copyright 2024 Antony Huaman
```
