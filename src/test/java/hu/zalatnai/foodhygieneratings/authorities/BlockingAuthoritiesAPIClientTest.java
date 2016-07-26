package hu.zalatnai.foodhygieneratings.authorities;

import java.util.List;

import com.github.restdriver.clientdriver.ClientDriverRule;
import hu.zalatnai.foodhygieneratings.FHRSAPIConfiguration;
import hu.zalatnai.foodhygieneratings.shared.ServiceUnavailableException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import org.springframework.web.client.RestTemplate;

import static com.github.restdriver.clientdriver.RestClientDriver.*;
import static hu.zalatnai.foodhygieneratings.TestResources.AUTHORITIES_RESPONSE;
import static hu.zalatnai.foodhygieneratings.FHRSAPIConfiguration.AUTHORITIES_BASIC_API_ENDPOINT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BlockingAuthoritiesAPIClientTest {
    @Rule
    public ClientDriverRule driver = new ClientDriverRule();

    private BlockingAuthoritiesAPIClient apiClient;

    @Before
    public void setUp() {
        FHRSAPIConfiguration configuration = new FHRSAPIConfiguration();
        configuration.setUri("http://localhost:" + driver.getPort());
        apiClient = new BlockingAuthoritiesAPIClient(configuration, new RestTemplate());
    }

    @Test
    public void theCorrectApiVersionIsSentInTheRequestHeaders() {
        driver.addExpectation(
            onRequestTo(AUTHORITIES_BASIC_API_ENDPOINT).withHeader("x-api-version", "2"),
            giveResponse(AUTHORITIES_RESPONSE, "application/json")
        );

        apiClient.getAuthorities();
    }

    @Test(expected = ServiceUnavailableException.class)
    public void inCaseOfAnyErrorAServiceUnavailableExceptionIsThrown() {
        driver.addExpectation(
            onRequestTo(AUTHORITIES_BASIC_API_ENDPOINT),
            giveEmptyResponse().withStatus(500)
        );

        apiClient.getAuthorities();
    }

    @Test
    public void theDeserializedListOfAuthoritiesIsReturned() {
        driver.addExpectation(
            onRequestTo(AUTHORITIES_BASIC_API_ENDPOINT),
            giveResponse(AUTHORITIES_RESPONSE, "application/json")
        );

        List<Authority> authorities = apiClient.getAuthorities();

        assertThat(
            authorities,
            containsInAnyOrder(new Authority(1, "a"), new Authority(2, "b"), new Authority(3, "c"))
        );
    }
}