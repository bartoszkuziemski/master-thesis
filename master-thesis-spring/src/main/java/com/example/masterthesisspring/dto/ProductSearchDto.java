package com.example.masterthesisspring.dto;

import com.example.masterthesisspring.model.enums.CategoryType;
import com.example.masterthesisspring.model.enums.ConditionType;
import com.example.masterthesisspring.model.enums.OfferType;

import java.util.Set;

public record ProductSearchDto(
        String name,
        Double priceMin,
        Double priceMax,
        Double ratingMin,
        Set<CategoryType> categoryType,
        Set<ConditionType> conditionType,
        Set<OfferType> offerType
) {
}
