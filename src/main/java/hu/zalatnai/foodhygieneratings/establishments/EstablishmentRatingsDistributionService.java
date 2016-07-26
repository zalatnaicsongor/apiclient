package hu.zalatnai.foodhygieneratings.establishments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstablishmentRatingsDistributionService {
    private final EstablishmentRatingsAPIClient apiClient;

    @Autowired
    EstablishmentRatingsDistributionService(EstablishmentRatingsAPIClient apiClient) {
        this.apiClient = apiClient;
    }

    /*
    Assumption: The localAuthorityIds are between 1 and 406.
     */
    public RatingsDistribution getRatingsDistributionForLocalAuthority(int authorityId) {
        if (authorityId < 1 || authorityId > 406) {
            throw new InvalidAuthorityIdException();
        }

        return new RatingsDistribution(apiClient.getRatingsForAuthority(authorityId));
    }
}
