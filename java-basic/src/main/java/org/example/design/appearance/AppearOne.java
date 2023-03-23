package org.example.design.appearance;

/**
 * @author: zyh
 * @date: 2023/3/23
 */
public class AppearOne {

    public static void main(String[] args) {
        System.out.println(1.0 / 0.0);
    }

}

// 定义外观类 MultimediaPlayer，封装了所有子系统的复杂性
 class MultimediaPlayer {
    private VideoPlayer videoPlayer;
    private AudioPlayer audioPlayer;
    private SubtitleProcessor subtitleProcessor;

    public MultimediaPlayer() {
        videoPlayer = new VideoPlayerImpl();
        audioPlayer = new AudioPlayerImpl();
        subtitleProcessor = new SubtitleProcessorImpl();
    }

    public void play(String videoFile, String audioFile, String subtitleFile) {
        videoPlayer.load(videoFile);
        audioPlayer.load(audioFile);
        subtitleProcessor.load(subtitleFile);

        videoPlayer.play();
        audioPlayer.play();
        subtitleProcessor.display();
    }
}

// 定义视频播放器接口及其实现类
 interface VideoPlayer {
    void load(String videoFile);
    void play();
}
 class VideoPlayerImpl implements VideoPlayer {
    @Override
    public void load(String videoFile) {
        System.out.println("Loading video file: " + videoFile);
    }

    @Override
    public void play() {
        System.out.println("Playing video...");
    }
}

// 定义音频播放器接口及其实现类
 interface AudioPlayer {
    void load(String audioFile);
    void play();
}
 class AudioPlayerImpl implements AudioPlayer {
    @Override
    public void load(String audioFile) {
        System.out.println("Loading audio file: " + audioFile);
    }

    @Override
    public void play() {
        System.out.println("Playing audio...");
    }
}

// 定义字幕处理器接口及其实现类
 interface SubtitleProcessor {
    void load(String subtitleFile);
    void display();
}
class SubtitleProcessorImpl implements SubtitleProcessor {
    @Override
    public void load(String subtitleFile) {
        System.out.println("Loading subtitle file: " + subtitleFile);
    }

    @Override
    public void display() {
        System.out.println("Displaying subtitle...");
    }
}

