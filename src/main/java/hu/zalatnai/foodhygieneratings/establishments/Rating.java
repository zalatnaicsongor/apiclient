package hu.zalatnai.foodhygieneratings.establishments;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Rating {
    FIVE("5"),
    FOUR("4"),
    THREE("3"),
    TWO("2"),
    ONE("1"),
    ZERO("0"),
    EXEMPT("Exempt"),
    PASS("Pass"),
    IMRPOVMENT_REQUIRED("Improvement Required"),
    AWAITING_PUBLICATION("Awaiting Publication"),
    AWAITING_INSPECTION("Awaiting Inspection");

    private String value;

    Rating(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}