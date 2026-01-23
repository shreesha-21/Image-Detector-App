# Object Detection Android App

This Android application demonstrates how to capture images using CameraX, upload them to a local Python backend for object detection, and render bounding boxes around the results using Jetpack Compose.

It implements a clean MVVM architecture, separating the UI, business logic, and networking layers. The primary goal is to show how to handle coordinate mapping between a server's detection model and the Android screen resolution using a custom Canvas overlay.

## Features

* **CameraX Integration:** Uses `LifecycleCameraController` for capturing images.
* **Custom Canvas Drawing:** Draws bounding boxes and confidence labels over the image based on server coordinates.
* **MVVM Architecture:** Separates the UI (Compose) from the data handling (Repository/Retrofit).
* **Image Handling:** Automatically handles EXIF rotation to prevent images from appearing sideways.
* **Wired Debugging Support:** Configured to work with `adb reverse` for fast local testing via USB.

## Tech Stack

* **Language:** Kotlin
* **UI:** Jetpack Compose (Material3)
* **Camera:** CameraX
* **Networking:** Retrofit, OkHttp, Gson
* **Asynchronous Processing:** Coroutines & Flow

## API Requirements

The app expects a backend server running locally. By default, it looks for `localhost:8000` at the endpoint `/detect`.

**Request:** `POST` (multipart/form-data)
**Key:** `image`

**Expected Response JSON:**

```json
{
  "detections": [
    {
      "label": "person",
      "confidence": 0.98,
      "bbox": [100.0, 250.0, 400.0, 600.0]
    }
  ]
}

```

*Note: The bbox array format corresponds to [x1, y1, x2, y2].*

## Setup & Installation

### 1. Prerequisites

* Android Studio (Hedgehog or newer).
* A physical Android device (recommended) or Emulator.
* A local backend server running (Python/Node/etc).

### 2. Connection Setup (USB Debugging)

Since the server runs on your computer's `localhost`, you need to bridge the connection to your Android device using ADB.

1. Connect your phone via USB.
2. Run this command in your terminal to map the ports:
```bash
adb reverse tcp:8000 tcp:8000

```


*Replace `8000` with your actual server port if it differs.*

### 3. Build

Sync the project with Gradle files and run the app. Ensure your `minSdk` is set to 24 or higher.

## Usage Guide

1. **Permissions:** Grant camera access when prompted on the first launch.
2. **Capture:** Tap the capture button to snap a photo.
3. **Analysis:** The app will upload the photo. Once the server responds, you will see the image with bounding boxes overlayed.
4. **Retake:** Tap the "Retake" button to delete the temporary image and return to the camera view.

## Troubleshooting

* **Connection Refused:** Double-check that you ran the `adb reverse` command after plugging in your phone. Also, verify that `android:usesCleartextTraffic="true"` is present in the Manifest.
* **Image Rotated:** If the bounding boxes are in the wrong place or the image is sideways, the EXIF rotation helper might be disabled. Ensure `decodeAndRotateBitmap` is being used in the result view.
* **Crashes:** If the app crashes on result, check that your server is returning the `bbox` as a List (Array), not a JSON Object.

## License

This project is licensed under the MIT License - see the LICENSE file for details.
---

