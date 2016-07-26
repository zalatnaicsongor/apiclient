package hu.zalatnai.foodhygieneratings.establishments;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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
        List<Rating> ratings = Arrays.asList(new Rating("Dog"), new Rating("Dog"), new Rating("Cat"), new Rating("Cow"));
        Map<String, Double> distribution = new RatingsDistribution(ratings).getDistribution();
        assertThat(distribution.get("Dog"), is(0.5));
        assertThat(distribution.get("Cat"), is(0.25));
        assertThat(distribution.get("Cow"), is(0.25));
        assertThat(distribution.size(), is(3));
    }
}