package com.example.masterthesisspring.dto;

import com.example.masterthesisspring.model.enums.CategoryType;
import com.example.masterthesisspring.model.enums.ConditionType;
import com.example.masterthesisspring.model.enums.OfferType;

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
