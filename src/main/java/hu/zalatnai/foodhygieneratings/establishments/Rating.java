package hu.zalatnai.foodhygieneratings.establishments;

import com.fasterxml.jackson.annotation.JsonProperty;

/*
This used to be an enum, but there were so many missing RatingKeys in the output of the Ratings api so that I mapped
the RatingKey to a plain String instead of an Enum
 */
public class Rating {
    /*
    FIVE("fhrs_5_en-gb"),
    FOUR("fhrs_4_en-gb"),
    THREE("fhrs_3_en-gb"),
    TWO("fhrs_2_en-gb"),
    ONE("fhrs_1_en-gb"),
    ZERO("fhrs_0_en-gb"),
    EXEMPT_FHRS("fhrs_exempt_en-gb"),
    EXEMPT_FHIS("fhis_exempt_en-gb"),
    PASS("fhis_pass_en-gb"),
    PASS_AND_EAT_SAFE("fhis_pass_and_eat_safe_en-gb"),
    IMRPOVMENT_REQUIRED("fhis_improvement_required_en-gb"),
    AWAITING_PUBLICATION_FHIS("fhis_awaiting_publication_en-gb"),
    AWAITING_PUBLICATION_FHRS("fhrs_awaiting_publication_en-gb"),
    AWAITING_INSPECTION_FHIS("fhis_awaiting_inspection_en-gb"),
    AWAITING_INSPECTION_FHRS("fhrs_awaitinginspection_en-gb");

    private String value;

    Rating(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
    */

    Rating() {
    }

    public Rating(String ratingKey) {
        this.ratingKey = ratingKey;
    }

    @JsonProperty("RatingKey")
    private String ratingKey;

    public String getRatingKey() {
        return ratingKey;
    }
}