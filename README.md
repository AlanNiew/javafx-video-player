# JavaFX Video Player

A JavaFX-based video player application that supports multiple video sources including local files, HTTP/HTTPS streams, M3U8 streams, and RTMP streams.

## Table of Contents

- [Features](#features)
- [Screenshots](#screenshots)
- [Technical Implementation](#technical-implementation)
  - [Main Components](#main-components)
  - [Key Features](#key-features)
  - [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Running the Application](#running-the-application)
- [Usage](#usage)
- [Building and Deployment](#building-and-deployment)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- **Multiple Video Source Support**:
  - Local video files (MP4, AVI, MKV, MOV, WMV, FLV, M3U8, etc.)
  - Online videos (HTTP/HTTPS)
  - M3U8 streaming
  - RTMP streaming

- **Full Playback Controls**:
  - Play/Pause
  - Stop
  - Volume control
  - Progress bar with seeking capability
  - Time display (current time/total duration)

- **User-Friendly Interface**:
  - Intuitive file selector
  - Video source type selection dropdown
  - Clear playback control buttons
  - Real-time progress display

## Screenshots

![Video Player Interface](screenshots/player-interface.png)
*Main video player interface*

## Technical Implementation

### Main Components

1. **VideoPlayerController.java** - FXML controller handling user interactions
2. **VideoPlayer.java** - Core playback logic encapsulation
3. **video-player.fxml** - User interface layout
4. **HelloApplication.java** - Application entry point
5. **module-info.java** - Java module system configuration

### Key Features

- **M3U8 Stream Support**: Implemented through JavaFX native Media API
- **Modular Design**: Complies with Java 9+ module system requirements
- **Error Handling**: Comprehensive exception handling and user notifications
- **Responsive UI**: Real-time updates of playback progress and time display

### Technology Stack

- JavaFX 17.0.6 (including Controls, FXML, Media modules)
- Maven build system
- Java 17

## Getting Started

### Prerequisites

- JDK 17 (Java Development Kit)
- Apache Maven 3.8+
- Git (for version control)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/AlanNiew/javafx-video-player.git
   ```

2. Navigate to the project directory:
   ```bash
   cd javafx-video-player
   ```

3. Compile the project:
   ```bash
   mvn compile
   ```

### Running the Application

To run the application, use the following command:
```bash
mvn javafx:run
```

Alternatively, you can use:
```bash
mvn clean javafx:run
```

## Usage

1. Select video source type from the dropdown (Local File, Online Video, M3U8 Stream, etc.)
2. Enter video URL or click "Select File" button to choose a local file
3. Click "Load" button to load the video
4. Use playback control buttons to control video playback

## Building and Deployment

To create a custom runtime image using jlink:
```bash
mvn clean compile javafx:jlink
```

The output will be located in the `target/app/bin` directory, containing executable startup scripts.

## Project Structure

```
javafx-video-player/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/zszq/javafxstudy/
│   │   │       ├── HelloApplication.java
│   │   │       ├── VideoPlayer.java
│   │   │       └── VideoPlayerController.java
│   │   └── resources/
│   │       └── com/zszq/javafxstudy/
│   │           └── video-player.fxml
├── pom.xml
└── README.md
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.