#s8132684 NIT3213 Final Assignment - Android Application Development Project


##Project Description

This Android application demonstrates user authentication, fetching exercise data from an API, and displaying it on a dashboard.
Users can view detailed information for each exercise.

##Features

-Login screen with API authentication
-Dashboard displaying a list of exercises
-Details screen for individual exercises
-API integration using Retrofit and Moshi
-State management with ViewModel and StateFlow
-Dependency injection with Hilt

##Setup Instructions

-Clone the repository:
git clone <(https://github.com/CarolineYanka/assignment2.git)>
-Open the project in Android Studio.
-Sync Gradle to download dependencies.
-Build and Run
-Run the app from Android Studio
-The login screen will appear. Enter credentials to access the dashboard.
-Tap any exercise to view details.

##API Integration
- *Base URL:* `https://nit3213api.onrender.com/`

##Data Flow
-LoginFragment --POST /auth--> ApiService --returns--> keypass
-DashboardFragment --GET /dashboard/{keypass}--> ApiService --returns--> List<Entity>
-DetailsFragment --display--> selected Entity


##Notes
-Requires Internet and network state permissions (declared in AndroidManifest.xml).
-An active internet connection is needed to fetch dashboard data.
-Safe Args is used to pass data between fragments (keypass and Entity).
