package hu.zalatnai.foodhygieneratings.establishments;

import java.util.List;

import com.github.restdriver.clientdriver.ClientDriverRule;
import hu.zalatnai.foodhygieneratings.FHRSAPIConfiguration;
import hu.zalatnai.foodhygieneratings.shared.ServiceUnavailableException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.web.client.RestTemplate;

import static com.github.restdriver.clientdriver.RestClientDriver.*;
import static hu.zalatnai.foodhygieneratings.FHRSAPIConfiguration.ESTABLISHMENTS_BASIC_API_ENDPOINT;
import static hu.zalatnai.foodhygieneratings.TestResources.ESTABLISHMENTS_RESPONSE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BlockingEstablishmentRatingsAPIClientTest {
    @Rule
    public ClientDriverRule driver = new ClientDriverRule();

    private BlockingEstablishmentRatingsAPIClient apiClient;

    @Before
    public void setUp() {
        FHRSAPIConfiguration configuration = new FHRSAPIConfiguration();
        configuration.setUri("http://localhost:" + driver.getPort());
        apiClient = new BlockingEstablishmentRatingsAPIClient(configuration, new RestTemplate());
    }

    @Test
    public void theCorrectApiVersionIsSentInTheRequestHeadersAlongWithTheLocalAuthorityIdAsAQueryParam() {
        driver.addExpectation(
            onRequestTo(ESTABLISHMENTS_BASIC_API_ENDPOINT).withHeader(
                "x-api-version",
                "2"
            ).withParam("localAuthorityId", "127"),
            giveResponse(ESTABLISHMENTS_RESPONSE, "application/json")
        );

        apiClient.getRatingsForAuthority(127);
    }

    @Test(expected = ServiceUnavailableException.class)
    public void inCaseOfAnyErrorAServiceUnavailableExceptionIsThrown() {
        driver.addExpectation(
            onRequestTo(ESTABLISHMENTS_BASIC_API_ENDPOINT).withParam("localAuthorityId", "127"),
            giveEmptyResponse().withStatus(500)
        );

        apiClient.getRatingsForAuthority(127);
    }

    @Test
    public void theDeserializedListOfRatingsIsReturned() {
        driver.addExpectation(
            onRequestTo(ESTABLISHMENTS_BASIC_API_ENDPOINT).withParam("localAuthorityId", "127"),
            giveResponse(ESTABLISHMENTS_RESPONSE, "application/json")
        );

        List<Rating> ratings = apiClient.getRatingsForAuthority(127);

        assertThat(
            ratings,
            containsInAnyOrder(Rating.FIVE, Rating.FIVE, Rating.ONE, Rating.EXEMPT)
        );
    }
}