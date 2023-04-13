package com.example.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoryType {
    ELECTRONICS("ELECTRONICS"),
    FASHION("FASHION"),
    HOME_AND_GARDEN("HOME_AND_GARDEN"),
    FOOD("FOOD"),
    BEAUTY("BEAUTY"),
    HEALTH("HEALTH"),
    SPORT("SPORT"),
    AUTOMOTIVE("AUTOMOTIVE"),
    ART("ART");

    private final String name;
}
