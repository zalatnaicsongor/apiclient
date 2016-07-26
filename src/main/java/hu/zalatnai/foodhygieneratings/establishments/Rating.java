package hu.zalatnai.foodhygieneratings.establishments;

import com.fasterxml.jackson.annotation.JsonValue;

/*
Every Rating is mapped to this enum no matter what the SchemeType of the Authority is.
This makes the design a lot simpler as there is no need to load the Authority before retrieving the ratings.
The front-end can easily infer which table (FHRS or FHIS) to render by looking at the data.
Assumption: There are no regions where both FHRS and FHIS SchemeType is used.
 */
public enum Rating {
    FIVE("fhrs_5_en-gb"),
    FOUR("fhrs_4_en-gb"),
    THREE("fhrs_3_en-gb"),
    TWO("fhrs_2_en-gb"),
    ONE("fhrs_1_en-gb"),
    ZERO("fhrs_0_en-gb"),
    EXEMPT_FHRS("fhrs_exempt_en-gb"),
    EXEMPT_FHIS("fhis_exempt_en-gb"),
    PASS("fhis_pass_en-gb"),
    IMRPOVMENT_REQUIRED("fhis_improvement_required_en-gb"),
    AWAITING_PUBLICATION("fhis_awaiting_publication_en-gb"),
    AWAITING_INSPECTION("fhis_awaiting_inspection_en-gb");

    private String value;

    Rating(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}