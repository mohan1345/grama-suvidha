# Grama Suvidha - Village Development Management System

Grama Suvidha is a comprehensive Android application designed to bridge the gap between village administration (Panchayat) and citizens. It facilitates transparent tracking of local development projects, allowing citizens to monitor progress, provide feedback, and report issues directly to the authorities.

## 🚀 Features

### For Citizens
*   **Project Discovery**: Browse through ongoing and completed village development projects (roads, schools, water supply, etc.).
*   **Real-time Progress Tracking**: View detailed project information including budget, timelines, and progress bars.
*   **Visual Evidence**: Compare 'Before' and 'After/Current' status through high-quality images.
*   **Interactive Feedback**: Submit feedback on projects to voice your opinion.
*   **Issue Reporting**: Directly report problems or grievances related to specific projects.
*   **Multi-language Support**: Fully accessible in both English and Kannada.

### For Administrators (Panchayat Officers)
*   **Analytics Dashboard**: Get a birds-eye view of all projects (Total, Ongoing, Completed) and pending issues.
*   **Project Management**: Create, update, and manage details for various village initiatives.
*   **Feedback & Issue Oversight**: Monitor citizen reports and feedback to ensure timely resolutions.

## 🛠 Tech Stack

*   **UI Framework**: Jetpack Compose (Modern declarative UI)
*   **Architecture**: MVVM (Model-View-ViewModel)
*   **Dependency Injection**: Hilt (Dagger)
*   **Database**: Room Persistence Library
*   **Navigation**: Navigation Compose
*   **Network/Image Loading**: Coil & OkHttp
*   **Preferences**: DataStore (for session and setting management)

## 🔧 Recent Improvements & Bug Fixes

*   **Fixed Startup Crashes**: Resolved issues where the app would close immediately on launch due to improper resource loading (Adaptive Icons in Compose).
*   **Resolved Hilt Context Errors**: Fixed a critical `IllegalStateException` where Hilt failed to find the Activity context during ViewModel creation, especially when changing languages.
*   **Network Permission**: Added missing `INTERNET` permission in the Manifest to allow loading project images and future API integrations.
*   **Resource Optimization**: Standardized image loading to use stable raster assets for better performance and reliability.

## 📖 How to Get Started

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/rshashank-r/GRama-suvidha.git
    ```
2.  **Open in Android Studio**: Open the project folder in the latest version of Android Studio (Hedgehog or newer recommended).
3.  **Build & Run**: Use a Pixel 5 or similar emulator/device running API 24 or above.
4.  **Login Credentials**:
    *   **Admin**: `admin@gramasuvidha.gov` / `admin123`
    *   **Citizen**: `citizen@test.com` / `citizen123`

---
Developed to empower rural communities through digital transparency.
