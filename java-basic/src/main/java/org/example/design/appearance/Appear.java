package org.example.design.appearance;

/**
 * @author: zyh
 * @date: 2023/3/23
 */
public class Appear {

    public static void main(String[] args) {
        new HomeTheaterFacade().watchMovie("泰坦尼克号");
    }
}
// 定义一个外观类
class HomeTheaterFacade {
    private Television tv;
    private DVDPlayer dvdPlayer;
    private SoundSystem soundSystem;
    private RemoteController remoteController;

    public HomeTheaterFacade() {
        tv = new Television();
        dvdPlayer = new DVDPlayer();
        soundSystem = new SoundSystem();
        remoteController = new RemoteController(tv, dvdPlayer, soundSystem);
    }

    // 定义一个统一的操作接口，以便于客户端使用
    public void watchMovie(String movieName) {
        remoteController.turnOnTv();
        remoteController.turnOnSoundSystem();
        remoteController.switchToDVDPlayer();
        dvdPlayer.play(movieName);
    }
}

// 定义一个遥控器类，用于控制电视、DVD 和音响
class RemoteController {
    private Television tv;
    private DVDPlayer dvdPlayer;
    private SoundSystem soundSystem;

    public RemoteController(Television tv, DVDPlayer dvdPlayer, SoundSystem soundSystem) {
        this.tv = tv;
        this.dvdPlayer = dvdPlayer;
        this.soundSystem = soundSystem;
    }

    public void turnOnTv() {
        tv.turnOn();
    }

    public void turnOffTv() {
        tv.turnOff();
    }

    public void switchToDVDPlayer() {
        dvdPlayer.turnOn();
        tv.switchToDVDPlayer();
    }

    public void switchToTV() {
        tv.turnOn();
        dvdPlayer.turnOff();
        tv.switchToTV();
    }

    public void turnOnSoundSystem() {
        soundSystem.turnOn();
    }

    public void turnOffSoundSystem() {
        soundSystem.turnOff();
    }
}

// 定义一个电视类
class Television {
    public void turnOn() {
        System.out.println("Turn on the TV.");
    }

    public void turnOff() {
        System.out.println("Turn off the TV.");
    }

    public void switchToDVDPlayer() {
        System.out.println("Switch to DVD mode.");
    }

    public void switchToTV() {
        System.out.println("Switch to TV mode.");
    }
}

// 定义一个 DVD 播放器类
class DVDPlayer {
    public void turnOn() {
        System.out.println("Turn on the DVD player.");
    }

    public void turnOff() {
        System.out.println("Turn off the DVD player.");
    }

    public void play(String movieName) {
        System.out.println("Play the movie: " + movieName);
    }
}

// 定义一个音响类
class SoundSystem {
    public void turnOn() {
        System.out.println("Turn on the sound system.");
    }

    public void turnOff() {
        System.out.println("Turn off the sound system.");
    }
}
