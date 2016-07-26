package hu.zalatnai.foodhygieneratings.establishments;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.*;

public class RatingsDistribution {
    private final Map<Rating, Double> distribution;

    RatingsDistribution(List<Rating> ratings) {
        if (ratings == null || ratings.size() <= 0) {
            throw new NoRatingsException();
        }

        Map<Rating, Long> countByRatings = ratings.stream().collect(groupingBy(identity(), counting()));
        distribution = Collections.unmodifiableMap(countByRatings.entrySet().stream().collect(toMap(
            Map.Entry::getKey,
            entry -> entry.getValue().doubleValue() / ratings.size()
        )));
    }

    public Map<Rating, Double> getDistribution() {
        return distribution;
    }
}
