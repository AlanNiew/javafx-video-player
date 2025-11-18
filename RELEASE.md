# Release v1.0.0

## JavaFX Video Player

This is the first official release of the JavaFX Video Player application.

## Features

- Support for multiple video sources:
  - Local video files (MP4, AVI, MKV, MOV, WMV, FLV, M3U8, etc.)
  - Online videos (HTTP/HTTPS)
  - M3U8 streaming
  - RTMP streaming
- Full playback controls:
  - Play/Pause
  - Stop
  - Volume control
  - Progress bar with seeking
  - Time display (current time/total duration)
- User-friendly interface:
  - Intuitive file selector
  - Video source type selection dropdown
  - Clear playback control buttons
  - Real-time progress display

## Technical Implementation

### Main Components

1. `VideoPlayerController.java` - FXML controller handling user interactions
2. `VideoPlayer.java` - Core playback logic encapsulation
3. `video-player.fxml` - User interface layout
4. `HelloApplication.java` - Application entry point

### Key Features

- **M3U8 Stream Support**: Implemented through JavaFX native Media API
- **Modular Design**: Complies with Java 9+ module system requirements
- **Error Handling**: Comprehensive exception handling and user notifications
- **Responsive UI**: Real-time updates of playback progress and time display

### Technology Stack

- JavaFX 17.0.6 (including Controls, FXML, Media modules)
- Maven build system
- Java 17

## Installation

To run the application, use the following command:
```bash
mvn javafx:run
```

## Building

To create a custom runtime image using jlink:
```bash
mvn clean compile javafx:jlink
```

The output will be located in the `target/app/bin` directory, containing executable startup scripts.