package org.example.pattern.builder;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public class App {

    public static void main(String [] args) {
        Hero riobard = new Hero.Builder(Profession.MAGE,"Riobard")
                .withHairColor(HairColor.BLACK)
                .withWeapon(Weapon.DAGGER)
                .build();
        System.out.println(riobard.toString());

        Hero amberjill = new Hero.Builder(Profession.WARRIOR, "Amberjill")
                .withHairColor(HairColor.BLOND)
                .withHairType(HairType.LONG_CURLY)
                .withArmor(Armor.CHAIN_MAIL)
                .withWeapon(Weapon.SWORD)
                .build();
        System.out.println(amberjill.toString());

        Hero thief = new Hero.Builder(Profession.THIEF, "Desmond")
                .withHairType(HairType.BALD)
                .withWeapon(Weapon.BOW)
                .build();
        System.out.println(thief.toString());

    }

}
