package com.example.dto;


import com.example.model.enums.CategoryType;
import com.example.model.enums.ConditionType;
import com.example.model.enums.OfferType;
import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record ProductDto(
        Long id,
        String name,
        Double price,
        String description,
        Double rating,
        UserDto user,
        CategoryType categoryType,
        ConditionType conditionType,
        OfferType offerType
) {
}
