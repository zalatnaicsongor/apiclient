package hu.zalatnai.foodhygieneratings.establishments;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.verifyNew;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest(EstablishmentRatingsDistributionService.class)
public class EstablishmentRatingsDistributionServiceTest {
    @Mock
    private EstablishmentRatingsAPIClient apiClient;

    @Mock
    private RatingsDistribution ratingsDistribution;

    @InjectMocks
    private EstablishmentRatingsDistributionService establishmentRatingsDistributionService;

    private List<Rating> ratings = Collections.singletonList(new Rating("Dog"));

    @Before
    public void setUp() throws Exception {
        when(apiClient.getRatingsForAuthority(127)).thenReturn(ratings);
        whenNew(RatingsDistribution.class).withArguments(ratings).thenReturn(ratingsDistribution);
    }

    @Test(expected = InvalidAuthorityIdException.class)
    public void throwsIfTheLocalAuthorityIdIsLessThanTheLowerBound() {
        establishmentRatingsDistributionService.getRatingsDistributionForLocalAuthority(0);
    }

    @Test(expected = InvalidAuthorityIdException.class)
    public void throwsIfTheLocalAuthorityIdIsGreaterThanTheUpperBound() {
        establishmentRatingsDistributionService.getRatingsDistributionForLocalAuthority(407);
    }

    @Test
    public void theRatingsDistributionGeneratedFromTheApiClientsResponseIsReturned() throws Exception {
        RatingsDistribution ratingsDistribution =
            establishmentRatingsDistributionService.getRatingsDistributionForLocalAuthority(127);

        assertEquals(this.ratingsDistribution, ratingsDistribution);
        verify(apiClient).getRatingsForAuthority(127);
        verifyNew(RatingsDistribution.class).withArguments(ratings);
    }
}