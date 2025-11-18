# JavaFX Video Player

A JavaFX-based video player application that supports multiple video sources including local files, HTTP/HTTPS streams, M3U8 streams, and RTMP streams.

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

## Usage

1. Select video source type (Local File, Online Video, M3U8 Stream, etc.)
2. Enter video URL or click "Select File" button to choose a local file
3. Click "Load" button to load the video
4. Use playback control buttons to control video playback

The application has been successfully compiled and tested, verifying the correct implementation of all features.