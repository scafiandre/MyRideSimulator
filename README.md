# MyRideSimulator

MyRideSimulator is a proof-of-concept (POC) native Android application built using Clean Architecture and the MVVM pattern. It consumes a simulated API and demonstrates modern Android development practices.

## Features

- Native Android app.
- Clean Architecture and MVVM design pattern.
- Integration with a simulated API.
- Modern UI with Jetpack Compose.
- Custom Jetpack Compose components for reusable and scalable UI elements.
- Google Maps API integration for location-based features.

## Demonstration

Here is a demonstration of MyRideSimulator in action:

<img src="MYRIDESIMULATOR.gif" alt="MyRideSimulator in Action" width="300">

## Technologies Used

- **Android Jetpack Components:**
  - Activity Compose
  - Material 3
  - Navigation Compose

- **Networking:**
  - Retrofit

- **Dependency Injection:**
  - Dagger Hilt
  - Hilt Navigation Compose

- **Image Loading:**
  - Coil Compose

- **UI Enhancements:**
  - Accompanist Navigation Animation

- **Testing:**
  - JUnit
  - MockK

## How to Use

### Prerequisites

1. Install Android Studio (latest version recommended).
2. Ensure you have the Android SDK and JDK set up.

### Installation

1. Clone this repository:
   ```bash
   git clone https://github.com/scafiandre/MyRideSimulator.git
   ```
2. Open the project in Android Studio.
3. Sync the Gradle files to download dependencies.

### API Key Setup

To use the Google Maps API, add your API key to the following location:

File: `com.scafisystems.myridesimulator.ui.utils.Constants`

Replace the placeholder value with your actual API key:
```
object Constants {
    //Must have your own Google Key for use Google Maps Service
    const val GOOGLE_KEY = ""
}
```

### Running the App

1. Connect an Android device or start an emulator.
2. Build and run the app using Android Studio.

## Contributing

Contributions are welcome! To contribute:

1. Fork the repository.
2. Create a new branch for your feature or bug fix:
   ```bash
   git checkout -b my-feature
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add my feature"
   ```
4. Push to your fork:
   ```bash
   git push origin my-feature
   ```
5. Open a pull request describing your changes.

## License

This project is licensed under the [MIT License](LICENSE). Feel free to use and modify it as needed.

---
