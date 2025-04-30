### Appambit Android Demo📱

## 📋Index

- [Requirements](#requirements)
- [Installation](#installation)
- [Running the demo application](#%EF%B8%8Fhow-to-run-the-demo-application)

## 📌Requirements
- Android SDK minimum: API 26 (Android 8.0 Oreo) 🍪
- Java 11 or higher ☕
- Kotlin 1.8 or higher 🅚

## 📥Installation


[Check library repository to find the file](https://github.com/RoelLeal/appambit)

 
### First Option: Using the AAR file 📦

1. Download the `appambit-debug.aar` or `appambit-release.aar` file.
2. Place the file in the `libs` directory of your project.
3. Add the following dependency in your `build.gradle` file at module level:
```
dependencies {
    implementation(files("../libs/appambit-debug.aar"))
}
```

### Second Option: Using the library module 🧩

1. Add the library module to your project
2. Include the module in your `settings.gradle` file:
```
include ':app', ':appambit'
```
Add the dependency in your `build.gradle` file at module level:
```
dependencies {
    implementation(project(":appambit"))
}
```

## ▶️How to run the demo application

The demo application shows how to use the appambit library in a real project.👨‍💻

### 📌Requirements

- Android Studio Meerkat (2024.3.1) or later 🦊
- Android device with API 26 or higher, or an emulator. 📱
- AGP 8.9.2 🔧

### 🛠️Steps to run the application

1. Clone the repository or download the source code
2. Open the project in Android Studio
3. Make sure that the appambit library is correctly configured as a dependency.
4. Run the application on a device or emulator.


### 🎛️Functionalities of the demo application

- Store Consumer” button: Store a consumer and get a token
- Start Session” button: Starts a session using the obtained token
- Displays the status of the operation and the received responses
