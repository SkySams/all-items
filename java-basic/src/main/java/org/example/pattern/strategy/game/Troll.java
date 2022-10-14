package org.example.pattern.strategy.game;

public class Troll extends Character {

    public Troll(WeaponBehavior weaponBehavior) {
        super(weaponBehavior);
    }

    @Override
    public void fight() {
        weaponBehavior.useWeapon();
    }
}
