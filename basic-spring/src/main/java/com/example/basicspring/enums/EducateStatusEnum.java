package com.example.basicspring.enums;

/**
 * @author: zyh
 * @date: 2023/2/2
 */
public enum EducateStatusEnum {

    /**
     * 学生 学业状态
     */
    IN_STUDYING((short) 1, "在读"),
    UNDERGRADUATE((short) 2, "肆业"),
    SUSPENDED((short) 3, "休学"),
    DROPPED((short) 4, "辍学"),
    QUITED((short) 5, "退学"),
    GRADUATED((short) 6, "已毕业"),


    ;
    public short code;
    public String name;

    EducateStatusEnum(Short code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }

    public static EducateStatusEnum findEnumByCode(Integer code) {

        for (EducateStatusEnum statusEnum : EducateStatusEnum.values()) {
            if (statusEnum.getCode() == code) {
                return statusEnum;
            }

        }
        throw new IllegalArgumentException("code is not support");

    }

    public static EducateStatusEnum findEnumByName(String name) {
        for (EducateStatusEnum statusEnum : EducateStatusEnum.values()) {
            if (statusEnum.getName().equals(name)) {
                return statusEnum;
            }

        }
        throw new IllegalArgumentException("name is not support");
    }


}