package org.example.pattern.strategy.game;

public class BowAndArrowBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("使用弓箭");
    }
}
