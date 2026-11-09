# ROYAL PALACE — Android starter project

Version: 0.1.0 (Stage 1)

This is the initial Android application foundation for ROYAL PALACE. It includes:
- Kotlin + Jetpack Compose interface
- Home, Matches, Analysis and Profile navigation
- Football, Basketball and Tennis sections
- A small Poisson score-probability engine with unit tests
- GitHub Actions workflow that builds a debug APK and publishes it as an artifact

## Important
The match names shown in the app are demonstration content only. No live fixtures, real odds, bookmaker feeds, or AI service are connected yet. Predictions are not guarantees.

## Build on GitHub
1. Upload the contents of this project to the root of your GitHub repository.
2. Open the repository's **Actions** tab.
3. Select **Build ROYAL PALACE APK**.
4. If the workflow has not started automatically, choose **Run workflow**.
5. Open the completed workflow run and download the `royal-palace-debug-apk` artifact.

The APK is a debug build for testing. A signed release build and backend integrations are later stages.

## Technology
- Android Gradle Plugin 8.7.3
- Kotlin 2.0.21
- Gradle 8.9
- Java 17
- Compose BOM 2024.10.01
- minSdk 26 / targetSdk 35
