package org.example.pattern.strategy.game;

public class SwordBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("使用了宝剑");
    }
}
