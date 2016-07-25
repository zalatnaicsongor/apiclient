package hu.zalatnai.foodhygieneratings;

import java.util.HashMap;

import com.github.restdriver.clientdriver.ClientDriverRule;
import hu.zalatnai.foodhygieneratings.authorities.Authority;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.github.restdriver.clientdriver.RestClientDriver.giveEmptyResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.giveResponse;
import static com.github.restdriver.clientdriver.RestClientDriver.onRequestTo;
import static hu.zalatnai.foodhygieneratings.TestResources.AUTHORITIES_RESPONSE;
import static hu.zalatnai.foodhygieneratings.authorities.BlockingAuthoritiesAPIClient.AUTHORITIES_BASIC_API_ENDPOINT;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FoodHygieneRatingsApplication.class)
@WebIntegrationTest
public class AuthoritiesAPITest {
    @Rule
    public ClientDriverRule driver = new ClientDriverRule(8734);

    @Test
    public void returnsAllTheAuthorities() {
        driver.addExpectation(
            onRequestTo(AUTHORITIES_BASIC_API_ENDPOINT).withHeader("x-api-version", "2"),
            giveResponse(AUTHORITIES_RESPONSE, "application/json")
        );

        get("/authorities").then()
            .statusCode(200)
            .body("name", hasItems("a", "b", "c"))
            .body("id", hasItems(1, 2, 3));
    }

    @Test
    public void returnsThatTheServiceIsUnavailableIfTheFHRSAPIReturnedAnError() {
        driver.addExpectation(
            onRequestTo(AUTHORITIES_BASIC_API_ENDPOINT).withHeader("x-api-version", "2"),
            giveEmptyResponse().withStatus(500)
        );

        get("/authorities").then()
            .statusCode(503);
    }
}
