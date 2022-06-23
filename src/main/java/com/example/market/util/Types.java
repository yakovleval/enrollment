package com.example.market.util;

public enum Types {
    CATEGORY, OFFER;

    @Override
    public String toString() {
        if (this == CATEGORY)
            return "CATEGORY";
        else
            return "OFFER";
    }
}
