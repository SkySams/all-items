package org.example.pattern.strategy.game;

public class King extends Character {

    public King(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
