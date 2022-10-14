package org.example.pattern.strategy.game;

public class Queen extends Character {

    public Queen(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }


}
