# CleanSweep

**Your Media, Organized. Faster. Smarter. Yours.**

## Introduction

CleanSweep is an innovative Android application designed to streamline the process of organizing your photos and videos.
Tired of cluttered galleries? CleanSweep offers a unique, Tinder-like swiping interface, allowing you to quickly decide the fate of each media item. Completely offline and highly customizable.

## Features

*   **Intuitive Swiping Interface:** A fast and fun way to process your media. Swipe to keep, delete, or perform other actions.
*   **Comprehensive Media Management:**
    *   **Keep/Delete:** Easily retain or remove unwanted media.
    *   **Move to Folders:** Organize media into custom folders.
    *   **Hybrid Deletion Strategy:** Intelligently handles media deletion by utilizing fast MediaStore operations for indexed files and falling back to direct file system access for unindexed content, ensuring robust and efficient clean-up.
    *   **Video-to-Image Extraction:** Convert moments from videos into images by taking a screenshot of a specific frame.
    *   **Duplicate & Similar Media Removal:** Intelligently identify and help you clear out redundant files, freeing up valuable storage.
        *   **Adjustable Similarity Level:** Configure how strictly 'similar' media is defined (Strict, Balanced, Loose), empowering you to fine-tune results and minimize false positives.
        *   **Advanced Video Similarity:** Utilizes sophisticated algorithms that analyze multiple frames (e.g., 10% and 50% of duration) for superior accuracy in identifying similar video content, even if trimmed or edited.
*   **Smart & Proactive Media Indexing:**
    *   **Just-in-Time Indexing:** Automatically identifies and requests MediaStore scans for unindexed media files within selected folders during swiping sessions, ensuring all relevant files are immediately discoverable and manageable.
    *   **Proactive Background Indexing:** A low-priority, periodic WorkManager task continuously scans and indexes all unknown media on your device, keeping the MediaStore up-to-date and optimizing overall app performance.
*   **Optimized Duplicate Scanning:** Features a multi-stage scanning process that efficiently filters out hidden and known unreadable files, coupled with re-balanced progress reporting for a smoother and more accurate user experience.
*   **Highly Customizable:** Tailor the app to your preferences with extensive customization options, including enhanced theming and accent color choices.
*   **AMOLED Theme Support:** Enjoy a battery-friendly and aesthetically pleasing dark mode for devices with AMOLED screens.
*   **Completely Offline:** All processing and organization happens directly on your device, ensuring privacy and no reliance on an internet connection.
*   **Full Onboarding Tutorial:** A comprehensive tutorial guides new users through all the app's features and functionalities.

## Screenshots/Demo

*(Placeholder: Add screenshots or a GIF demonstrating the app's UI and key features here.)*

## Technologies Used

CleanSweep is built with a modern Android development approach, leveraging the following key technologies and libraries:

*   **Language:** Kotlin
*   **UI Toolkit:** Jetpack Compose with Material 3 for a beautiful and modern user experience.
*   **Dependency Injection:** Hilt for robust and scalable architecture.
*   **Local Database:** Room Persistence Library for efficient data storage of file hashes, perceptual hashes, and paths of unreadable files to significantly speed up future scans. (The app does not save media files themselves).
*   **Image Loading:** Coil for fast and efficient image and video loading.
*   **Media Playback:** ExoPlayer (Media3) for seamless video playback.
*   **Permissions:** Accompanist Permissions for simplified runtime permission handling.
*   **Data Persistence:** DataStore Preferences for flexible and asynchronous storage of all user settings and preferences.
*   **File Operations:** Utilizes a hybrid approach for efficient file management, combining `ContentResolver` for MediaStore-indexed files and direct `java.io.File` API calls for full control over unindexed media.
*   **Background Processing:** WorkManager for reliable, low-priority background tasks like proactive media indexing.

## Requirements

To run CleanSweep, your device must be running **Android 10 (API Level 29)** or higher.

## Permissions

CleanSweep requires the following permissions to function correctly:

*   **`android.permission.MANAGE_EXTERNAL_STORAGE` (All Files Access):** Required for Android 11 (API 30) and above to allow the app to access, modify, and manage media files across the entire external storage, crucial for its hybrid deletion strategy and comprehensive file operations.
*   **`android.permission.WRITE_EXTERNAL_STORAGE` (maxSdkVersion 29):** For older Android versions (10 and below) to read and write to external storage.
*   **`android.permission.READ_EXTERNAL_STORAGE`:** For older Android versions to read media files.
*   **`android.permission.READ_MEDIA_IMAGES`:** For Android 13+ to read image files.
*   **`android.permission.READ_MEDIA_VIDEO`:** For Android 13+ to read video files.
*   **`android.permission.READ_MEDIA_VISUAL_USER_SELECTED`:** Declares support for Android 14's partial media selection, enhancing user privacy.
*   **`android.permission.FOREGROUND_SERVICE` & `android.permission.FOREGROUND_SERVICE_DATA_SYNC`:** Required for long-running tasks like duplicate scanning and proactive media indexing in the background.
*   **`android.permission.POST_NOTIFICATIONS`:** To display notifications for background tasks on Android 13 and above.

## Usage

Upon first launch, CleanSweep provides a full onboarding tutorial that will guide you through its core functionalities and how to interact with the swiping interface and other media organization tools. Simply follow the on-screen instructions to get started.

## Contributing

While there are no specific guidelines yet, feel free to open issues for bug reports or feature requests, or submit pull requests with your proposed changes.

**Note:** Feature requests from donating users will be prioritized.

## Donating


## License

This project is licensed under the [GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html).
