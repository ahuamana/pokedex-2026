# Pokedex Android App

A high-performance Android application built with **Clean Architecture**, **MVI (Model-View-Intent)**, and **Jetpack Compose**. This project demonstrates senior-level practices in state management, modularization, and exhaustive testing.

## Features

- **Infinite Scrolling**: Powered by **Paging 3** for seamless data loading and optimized memory usage.
- **MVI Architecture**: Predictable state management using `StateFlow` and `SharedFlow` for UI states and one-time effects.
- **Detailed Stats**: Comprehensive view of stats, types, and abilities with custom UI components.
- **Modularized Structure**: Improved build times and strict separation of concerns.
- 
## Architecture

The project follows Clean Architecture principles and is divided into several modules:

- **`:app`**: The main entry point of the application, containing the UI (Jetpack Compose) and DI setup. **33% Logic Coverage**.
- **`:domain`**: The core "Brain" of the app. Contains pure Kotlin Use Cases and Entities. **100% Logic Coverage**.
- **`:data`**: Implementation of repositories, API services (Retrofit), and Paging Sources. **100% Logic Coverage**.
- **`:shared`**: Contains common utilities, constants, and shared components used across different modules.
- **`:testing`**: Contains test utilities, mock data, and helper functions for unit and integration testing.


## 🧪 Testing Strategy

The testing strategy is designed to ensure high code quality and reliability across all layers of the application. The focus is on achieving comprehensive coverage while maintaining meaningful and effective tests.

### **Coverage Report**

| Module | Coverage | Test Type | focus |
| :--- | :--- | :--- | :--- |
| **`:domain`** | **100%** | Unit Test | Use Cases & Business Rules |
| **`:data`** | **100%** | Unit Test | Data Mappers & Model Transformations |
| **`:app`** | **90%** | Unit Test | ViewModel State transitions & Side Effects |
| **`:app`** | **UI** | AndroidTest | Composable Rendering & Accessibility (a11y) |

### **Testing Tech Stack**
* **MockK**: For advanced mocking of coroutines and functional contracts.
* **Turbine**: Used to verify `StateFlow` and `SharedFlow` emissions in ViewModels without race conditions.
* **Google Truth**: For fluent and highly readable assertions.
* **MainDispatcherRule**: Custom JUnit4 Rule to handle `UnconfinedTestDispatcher` for immediate coroutine execution in tests.

## 🛠 Tech Stack

- **Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Dependency Injection**: Hilt
- **Networking**: Retrofit & Kotlinx Serialization
- **Concurrency**: Coroutines & Flow
- **Pagination**: Paging 3
- **Image Loading**: Coil (with Accessibility support)

## 🏁 Getting Started

### **Running Tests**
To verify the testing suite and coverage, run the following command in the terminal:
```bash
./gradlew test
```

## License

```text
Copyright 2024 Antony Huaman
```
