package org.example.pattern.builder;

/**
 * @author: zyh
 * @date: 2022/12/28
 */
public enum Armor {

    CLOTHES ("clothes"),
    LEATHER ("leather"),
    CHAIN_MAIL ("chain mail"),
    PLATE_MAIL("plate mail")
    ;

    Armor(String title) {
        this.title = title;
    }

    private final String title;

    @Override
    public String toString() {
        return "Armor{" +
                "title='" + title + '\'' +
                '}';
    }
}
