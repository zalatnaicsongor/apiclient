package hu.zalatnai.foodhygieneratings.establishments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstablishmentRatingsDistributionController {
    private final EstablishmentRatingsDistributionService establishmentRatingsDistributionService;

    @Autowired
    EstablishmentRatingsDistributionController(EstablishmentRatingsDistributionService establishmentRatingsDistributionService) {
        this.establishmentRatingsDistributionService = establishmentRatingsDistributionService;
    }

    @RequestMapping(value = "/authorities/{authorityId}/ratings_distribution", method = RequestMethod.GET, produces = "application/json")
    public RatingsDistribution getEstablishmentRatingsDistribution(@PathVariable("authorityId") int authorityId) {
        return establishmentRatingsDistributionService.getRatingsDistributionForLocalAuthority(authorityId);
    }
}
