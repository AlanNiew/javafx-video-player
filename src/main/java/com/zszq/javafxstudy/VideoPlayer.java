package com.zszq.javafxstudy;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.util.function.Consumer;

public class VideoPlayer {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Runnable onReadyCallback;
    private Consumer<Duration> onTimeChangedCallback;

    public VideoPlayer(MediaView mediaView) {
        this.mediaView = mediaView;
    }

    public void loadVideo(String sourceUrl, String sourceType) {
        try {
            // 停止当前播放
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.dispose();
            }

            String mediaUrl = sourceUrl;

            // 根据源类型处理URL
            switch (sourceType) {
                case "本地文件":
                    File file = new File(sourceUrl);
                    mediaUrl = file.toURI().toString();
                    break;
                case "M3U8流媒体":
                    // M3U8需要特殊处理，JavaFX原生支持有限
                    mediaUrl = processM3U8Url(sourceUrl);
                    break;
                case "RTMP流媒体":
                    mediaUrl = processRTMPUrl(sourceUrl);
                    break;
                // 在线视频直接使用URL
            }

            // 创建Media对象
            Media media = new Media(mediaUrl);

            // 创建MediaPlayer
            mediaPlayer = new MediaPlayer(media);
            mediaView.setMediaPlayer(mediaPlayer);

            setupMediaPlayerListeners();

        } catch (Exception e) {
            e.printStackTrace();
            showError("加载视频失败: " + e.getMessage());
        }
    }

    private String processM3U8Url(String url) {
        // 对于M3U8流，确保URL格式正确
        // JavaFX对M3U8的支持取决于平台和编解码器
        if (!url.toLowerCase().endsWith(".m3u8")) {
            // 如果不是标准.m3u8结尾，可能需要特殊处理
            System.out.println("检测到M3U8流媒体: " + url);
        }
        return url;
    }

    private String processRTMPUrl(String url) {
        // RTMP流处理
        if (!url.startsWith("rtmp://")) {
            url = "rtmp://" + url;
        }
        return url;
    }

    private void setupMediaPlayerListeners() {
        mediaPlayer.setOnReady(() -> {
            System.out.println("媒体加载完成");
            if (onReadyCallback != null) {
                onReadyCallback.run();
            }
        });

        mediaPlayer.setOnPlaying(() -> {
            System.out.println("开始播放");
        });

        mediaPlayer.setOnError(() -> {
            System.out.println("播放错误: " + mediaPlayer.getError().getMessage());
            showError("播放错误: " + mediaPlayer.getError().getMessage());
        });

        // 进度监听
        mediaPlayer.currentTimeProperty().addListener((obs, oldTime, newTime) -> {
            if (onTimeChangedCallback != null) {
                onTimeChangedCallback.accept(newTime);
            }
        });
    }

    public void play() {
        if (mediaPlayer != null) {
            mediaPlayer.play();
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public void seek(Duration time) {
        if (mediaPlayer != null) {
            mediaPlayer.seek(time);
        }
    }

    public void setVolume(double volume) {
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(volume);
        }
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setOnReady(Runnable callback) {
        this.onReadyCallback = callback;
    }

    public void setOnTimeChanged(Consumer<Duration> callback) {
        this.onTimeChangedCallback = callback;
    }

    private void showError(String message) {
        // 显示错误消息
        System.err.println(message);
    }

    public void dispose() {
        if (mediaPlayer != null) {
            mediaPlayer.dispose();
        }
    }
}