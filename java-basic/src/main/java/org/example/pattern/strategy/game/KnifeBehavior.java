package org.example.pattern.strategy.game;


public class KnifeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("使用了刀");
    }
}
