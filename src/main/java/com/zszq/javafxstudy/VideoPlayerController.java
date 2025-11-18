package com.zszq.javafxstudy;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class VideoPlayerController implements Initializable {

    @FXML
    private BorderPane mainPane;

    @FXML
    private MediaView mediaView;

    @FXML
    private Slider progressSlider;

    @FXML
    private Label currentTimeLabel;

    @FXML
    private Label totalTimeLabel;

    @FXML
    private Button playButton;

    @FXML
    private TextField urlTextField;

    @FXML
    private ComboBox<String> videoSourceComboBox;

    @FXML
    private Slider volumeSlider;

    private VideoPlayer videoPlayer;
    private boolean isPlaying = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupVideoSources();
        setupEventHandlers();
        videoPlayer = new VideoPlayer(mediaView);
        
        // 初始化音量
        volumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (videoPlayer != null) {
                videoPlayer.setVolume(newVal.doubleValue() / 100.0);
            }
        });
    }

    private void setupVideoSources() {
        videoSourceComboBox.getItems().addAll(
            "本地文件",
            "在线视频 (HTTP/HTTPS)",
            "M3U8流媒体",
            "RTMP流媒体"
        );
        videoSourceComboBox.setValue("在线视频 (HTTP/HTTPS)");
    }

    private void setupEventHandlers() {
        progressSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (progressSlider.isValueChanging() && videoPlayer != null && videoPlayer.getMediaPlayer() != null) {
                videoPlayer.seek(videoPlayer.getMediaPlayer().getTotalDuration().multiply(newVal.doubleValue() / 100.0));
            }
        });
    }

    @FXML
    private void handleFileSelect() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("视频文件", "*.mp4", "*.avi", "*.mkv", "*.mov", "*.wmv", "*.flv", "*.m3u8")
        );
        File selectedFile = fileChooser.showOpenDialog(mainPane.getScene().getWindow());
        if (selectedFile != null) {
            urlTextField.setText(selectedFile.getAbsolutePath());
            videoSourceComboBox.setValue("本地文件");
        }
    }

    @FXML
    private void handleLoadVideo() {
        String url = urlTextField.getText();
        if (url == null || url.isEmpty()) {
            showAlert("请输入视频URL或选择本地文件");
            return;
        }
        
        String sourceType = videoSourceComboBox.getValue();
        videoPlayer.loadVideo(url, sourceType);
        
        // 设置播放器监听器
        videoPlayer.setOnReady(() -> {
            if (videoPlayer.getMediaPlayer() != null) {
                Duration totalDuration = videoPlayer.getMediaPlayer().getTotalDuration();
                totalTimeLabel.setText(formatTime(totalDuration));
            }
        });
        
        videoPlayer.setOnTimeChanged(currentTime -> {
            if (videoPlayer.getMediaPlayer() != null) {
                Duration totalDuration = videoPlayer.getMediaPlayer().getTotalDuration();
                if (totalDuration.greaterThan(Duration.ZERO)) {
                    double progress = currentTime.toMillis() / totalDuration.toMillis() * 100;
                    progressSlider.setValue(progress);
                    currentTimeLabel.setText(formatTime(currentTime));
                }
            }
        });
    }

    @FXML
    private void handlePlayPause() {
        if (videoPlayer.getMediaPlayer() == null) {
            handleLoadVideo(); // 如果还没有加载视频，先加载
        }
        
        if (!isPlaying) {
            videoPlayer.play();
            playButton.setText("暂停");
        } else {
            videoPlayer.pause();
            playButton.setText("播放");
        }
        isPlaying = !isPlaying;
    }

    @FXML
    private void handleStop() {
        videoPlayer.stop();
        playButton.setText("播放");
        isPlaying = false;
        progressSlider.setValue(0);
        currentTimeLabel.setText("00:00");
    }

    @FXML
    private void handleVolumeChange() {
        // 音量已经在slider监听器中处理了
    }

    private String formatTime(Duration duration) {
        if (duration.isUnknown()) {
            return "00:00";
        }
        int seconds = (int) duration.toSeconds();
        int minutes = seconds / 60;
        int hours = minutes / 60;
        seconds = seconds % 60;
        minutes = minutes % 60;
        
        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("警告");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}