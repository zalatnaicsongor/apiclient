package hu.zalatnai.foodhygieneratings;

import com.github.restdriver.clientdriver.ClientDriverRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.restdriver.clientdriver.RestClientDriver.giveEmptyResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;
import static hu.zalatnai.foodhygieneratings.FHRSAPIConfiguration.ESTABLISHMENTS_BASIC_API_ENDPOINT;
import static hu.zalatnai.foodhygieneratings.TestResources.ESTABLISHMENTS_RESPONSE;
import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FoodHygieneRatingsApplication.class)
@WebIntegrationTest
public class EstablishmentRatingsDistributionAPITest {
    @Rule
    public ClientDriverRule driver = new ClientDriverRule(8734);

    @Test
    public void returnsTheDistributionOfTheRatingsForTheSuppliedLocalAuthority() {
        driver.addExpectation(
            onRequestTo(ESTABLISHMENTS_BASIC_API_ENDPOINT).withParam("localAuthorityId", "127"),
            giveResponse(ESTABLISHMENTS_RESPONSE, "application/json")
        );

        get("/authorities/127/ratings_distribution").then()
            .statusCode(200)
            .body("distribution.fhrs_5_en-gb", is(0.5f))
            .body("distribution.fhrs_1_en-gb", is(0.25f))
            .body("distribution.fhrs_exempt_en-gb", is(0.25f));
    }


    @Test
    public void returnsThatTheServiceIsUnavailableIfTheFHRSAPIReturnedAnError() {
        driver.addExpectation(
            onRequestTo(ESTABLISHMENTS_BASIC_API_ENDPOINT).withParam("localAuthorityId", "127"),
            giveEmptyResponse().withStatus(500)
        );

        get("/authorities/127/ratings_distribution").then()
            .statusCode(503);
    }

    @Test
    public void returnsBadRequestIfTheAuthorityIdIsLessThanTheLowerBound() {
        get("/authorities/0/ratings_distribution").then()
            .statusCode(400);
    }

    @Test
    public void returnsBadRequestIfTheAuthorityIdIsGreaterThanTheUpperBound() {
        get("/authorities/407/ratings_distribution").then()
            .statusCode(400);
    }
}
