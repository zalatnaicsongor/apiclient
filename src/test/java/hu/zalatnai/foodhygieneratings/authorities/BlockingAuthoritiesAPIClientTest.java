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
import static hu.zalatnai.foodhygieneratings.authorities.BlockingAuthoritiesAPIClient.AUTHORITIES_BASIC_API_ENDPOINT;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class BlockingAuthoritiesAPIClientTest {
    private static final String response;

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
            giveResponse(response, "application/json")
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
            giveResponse(response, "application/json")
        );

        List<Authority> authorities = apiClient.getAuthorities();

        assertThat(
            authorities,
            containsInAnyOrder(new Authority(1, "a"), new Authority(2, "b"), new Authority(3, "c"))
        );
    }

    //Perhaps I should have extracted this into an external file.
    static {
        response = "{\n" +
            "  \"authorities\": [\n" +
            "    {\n" +
            "      \"LocalAuthorityId\": 1,\n" +
            "      \"LocalAuthorityIdCode\": \"sample string 2\",\n" +
            "      \"Name\": \"a\",\n" +
            "      \"EstablishmentCount\": 4,\n" +
            "      \"SchemeType\": 5,\n" +
            "      \"links\": [\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"LocalAuthorityId\": 2,\n" +
            "      \"LocalAuthorityIdCode\": \"sample string 2\",\n" +
            "      \"Name\": \"b\",\n" +
            "      \"EstablishmentCount\": 4,\n" +
            "      \"SchemeType\": 5,\n" +
            "      \"links\": [\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        }\n" +
            "      ]\n" +
            "    },\n" +
            "    {\n" +
            "      \"LocalAuthorityId\": 3,\n" +
            "      \"LocalAuthorityIdCode\": \"sample string 2\",\n" +
            "      \"Name\": \"c\",\n" +
            "      \"EstablishmentCount\": 4,\n" +
            "      \"SchemeType\": 5,\n" +
            "      \"links\": [\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"rel\": \"sample string 1\",\n" +
            "          \"href\": \"sample string 2\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"meta\": {\n" +
            "    \"dataSource\": \"sample string 1\",\n" +
            "    \"extractDate\": \"2016-07-24T07:09:13.4714549+01:00\",\n" +
            "    \"itemCount\": 3,\n" +
            "    \"returncode\": \"sample string 4\",\n" +
            "    \"totalCount\": 5,\n" +
            "    \"totalPages\": 6,\n" +
            "    \"pageSize\": 7,\n" +
            "    \"pageNumber\": 8\n" +
            "  },\n" +
            "  \"links\": [\n" +
            "    {\n" +
            "      \"rel\": \"sample string 1\",\n" +
            "      \"href\": \"sample string 2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"sample string 1\",\n" +
            "      \"href\": \"sample string 2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"rel\": \"sample string 1\",\n" +
            "      \"href\": \"sample string 2\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";
    }
}