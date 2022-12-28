package org.example.pattern.builder.test;

import org.example.pattern.builder.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public class HeroTest {

    @Test
    public void testBuildHero() {
        final String heroName = "Sir Lancelot";
        Hero hero = new Hero.Builder(Profession.WARRIOR, heroName)
                .withArmor(Armor.CHAIN_MAIL)
                .withWeapon(Weapon.SWORD)
                .withHairType(HairType.LONG_CURLY)
                .withHairColor(HairColor.BLOND)
                .build();

        assertNotNull(hero);
        assertNotNull(hero.toString());
        assertEquals(Profession.WARRIOR, hero.getProfession());
        assertEquals(heroName, hero.getName());
        assertEquals(Armor.CHAIN_MAIL, hero.getArmor());
        assertEquals(Weapon.SWORD, hero.getWeapon());
        assertEquals(HairType.LONG_CURLY, hero.getHairType());
        assertEquals(HairColor.BLOND, hero.getHairColor());

    }

}
