package hu.zalatnai.foodhygieneratings.establishments;

import java.util.List;

public interface EstablishmentRatingsAPIClient {
    List<Rating> getRatingsForAuthority(int authorityId);
}
