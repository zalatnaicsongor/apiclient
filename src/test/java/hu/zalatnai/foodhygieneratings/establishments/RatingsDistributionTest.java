package hu.zalatnai.foodhygieneratings.establishments;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import hu.zalatnai.foodhygieneratings.shared.NoRatingsException;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RatingsDistributionTest {
    @Test(expected = NoRatingsException.class)
    public void throwsIfTryingToCalculateTheDistributionWhenNoRatingsAreSupplied() {
        new RatingsDistribution(null);
    }

    @Test(expected = NoRatingsException.class)
    public void throwsIfTryingToCalculateTheDistributionWhenAnEmptyListOfRatingsIsSupplied() {
        new RatingsDistribution(Collections.emptyList());
    }

    @Test
    public void calculatesTheDistributionCorrectlyOfTheRatings() {
        List<Rating> ratings = Arrays.asList(Rating.FIVE, Rating.FIVE, Rating.ONE, Rating.EXEMPT);
        Map<Rating, Double> distribution = new RatingsDistribution(ratings).getDistribution();
        assertThat(distribution.get(Rating.FIVE), is(0.5));
        assertThat(distribution.get(Rating.ONE), is(0.25));
        assertThat(distribution.get(Rating.EXEMPT), is(0.25));
        assertThat(distribution.size(), is(3));
    }
}