package org.example.pattern.strategy.game;

public class AxeBehavior implements WeaponBehavior{
    @Override
    public void useWeapon() {
        System.out.println("使用了斧头");
    }
}
