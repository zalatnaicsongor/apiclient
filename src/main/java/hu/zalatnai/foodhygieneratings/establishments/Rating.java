package hu.zalatnai.foodhygieneratings.establishments;

import com.fasterxml.jackson.annotation.JsonValue;

/*
Every Rating is mapped to this enum no matter what the SchemeType of the Authority is.
This makes the design a lot simpler as there is no need to load the Authority before retrieving the ratings.
The front-end can easily infer which table (FHRS or FHIS) to render by looking at the data.
Assumption: There are no regions where both FHRS and FHIS SchemeType is used.
 */
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