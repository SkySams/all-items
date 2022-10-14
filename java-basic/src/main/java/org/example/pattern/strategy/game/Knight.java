package org.example.pattern.strategy.game;

public class Knight extends Character {

    public Knight(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
