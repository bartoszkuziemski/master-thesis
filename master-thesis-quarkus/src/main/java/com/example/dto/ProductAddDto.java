package com.example.dto;


import com.example.model.enums.CategoryType;
import com.example.model.enums.ConditionType;
import com.example.model.enums.OfferType;

public record ProductAddDto(
        String name,
        Double price,
        String description,
        Double rating,
        Long userId,
        CategoryType categoryType,
        ConditionType conditionType,
        OfferType offerType
) {
}
