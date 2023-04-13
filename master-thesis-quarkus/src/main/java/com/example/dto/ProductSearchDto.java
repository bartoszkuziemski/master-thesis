package com.example.dto;



import com.example.model.enums.CategoryType;
import com.example.model.enums.ConditionType;
import com.example.model.enums.OfferType;

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
