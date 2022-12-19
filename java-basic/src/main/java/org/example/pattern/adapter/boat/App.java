package org.example.pattern.adapter.boat;

/**
 * @author: zyh
 * @date: 2022/12/19
 */
public class App {

    private App(){}

    public static void main(String[] args) {
        Captain captain = new Captain(new FishingBoatAdapter());
        captain.row();
    }


}
