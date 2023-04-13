package com.example.masterthesisspring.dto;

import com.example.masterthesisspring.model.enums.CategoryType;
import com.example.masterthesisspring.model.enums.ConditionType;
import com.example.masterthesisspring.model.enums.OfferType;

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
