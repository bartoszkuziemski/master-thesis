package com.example.masterthesisspring.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum OfferType {
    BUY_NOW("BUY_NOW"),
    AUCTION("AUCTION");

    private final String name;
}
