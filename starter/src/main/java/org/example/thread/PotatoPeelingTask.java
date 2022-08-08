package org.example.thread;

/**
 * @author: zyh
 * @date: 2022/8/8
 */
public class PotatoPeelingTask extends Task {
    private static final int TIME_PER_POTATO = 200;

    public PotatoPeelingTask(int timeMs) {
        super(timeMs);
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), super.toString());
    }
}
