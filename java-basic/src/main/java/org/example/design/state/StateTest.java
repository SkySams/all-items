package org.example.design.state;

/**
 * @author: zyh
 * @date: 2023/3/23
 */
public class StateTest {

    public static void main(String[] args) {
        TVRemote remote = new TVRemote();

        remote.turnOn(); // Turning TV on
        remote.volumeUp(); // Increasing TV volume
        remote.volumeUp(); // Increasing TV volume
        remote.volumeDown(); // Decreasing TV volume
        remote.turnOff(); // Turning TV off
        remote.volumeDown(); // Cannot decrease volume, TV is off
    }

}

// 状态接口
interface TVState {
    void turnOn();
    void turnOff();
    void volumeUp();
    void volumeDown();
}

// 具体状态类：电视机开启状态
class TVOnState implements TVState {
    @Override
    public void turnOn() {
        System.out.println("TV is already on");
    }

    @Override
    public void turnOff() {
        System.out.println("Turning TV off");
    }

    @Override
    public void volumeUp() {
        System.out.println("Increasing TV volume");
    }

    @Override
    public void volumeDown() {
        System.out.println("Decreasing TV volume");
    }
}

// 具体状态类：电视机关闭状态
class TVOffState implements TVState {
    @Override
    public void turnOn() {
        System.out.println("Turning TV on");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is already off");
    }

    @Override
    public void volumeUp() {
        System.out.println("Cannot increase volume, TV is off");
    }

    @Override
    public void volumeDown() {
        System.out.println("Cannot decrease volume, TV is off");
    }
}

// 电视机遥控器类
class TVRemote {
    private TVState state;

    public TVRemote() {
        state = new TVOffState();
    }

    public void setState(TVState state) {
        this.state = state;
    }

    public void turnOn() {
        state.turnOn();
        setState(new TVOnState());
    }

    public void turnOff() {
        state.turnOff();
        setState(new TVOffState());
    }

    public void volumeUp() {
        state.volumeUp();
    }

    public void volumeDown() {
        state.volumeDown();
    }
}



