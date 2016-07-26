package hu.zalatnai.foodhygieneratings;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ConfigurationProperties(prefix = "fhrsapi")
public class FHRSAPIConfiguration {
    public static final String AUTHORITIES_BASIC_API_ENDPOINT = "/Authorities/basic";
    public static final String ESTABLISHMENTS_BASIC_API_ENDPOINT = "/Establishments";

    @NotNull
    private String uri;

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
