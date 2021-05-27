package com.coding.zoo.zoo.constants;

import lombok.Getter;

import static java.util.Arrays.stream;

@Getter
public enum AnimalEnum {
    Mammals("MAMMAL", "Mammal"),
    Reptiles("REPTILE", "Reptile"),
    Birds("Bird", "Bird");

    private final String code;
    private final String displayName;

    AnimalEnum(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }

    public static AnimalEnum from(String value) {
        return stream(AnimalEnum.values())
                .filter(val -> val.getDisplayName().equals(value))
                .findFirst()
                .orElse(null);
    }
}
