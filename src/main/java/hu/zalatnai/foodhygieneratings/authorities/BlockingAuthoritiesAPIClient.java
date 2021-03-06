package hu.zalatnai.foodhygieneratings.authorities;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import hu.zalatnai.foodhygieneratings.FHRSAPIConfiguration;
import hu.zalatnai.foodhygieneratings.shared.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
class BlockingAuthoritiesAPIClient implements AuthoritiesAPIClient {
    private final FHRSAPIConfiguration configuration;
    private final RestTemplate restTemplate;

    @Autowired
    BlockingAuthoritiesAPIClient(FHRSAPIConfiguration configuration, RestTemplate restTemplate) {
        this.configuration = configuration;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Authority> getAuthorities() {
        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("x-api-version", "2");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        try {
            return restTemplate.exchange(
                configuration.getUri() + FHRSAPIConfiguration.AUTHORITIES_BASIC_API_ENDPOINT,
                HttpMethod.GET,
                entity,
                Authorities.class
            ).getBody().authorities;
        } catch (RestClientException e) {
            //The client should be made aware that the service is unavailable in the case of the API returning
            //a non-200 status code.
            throw new ServiceUnavailableException(e);
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Authorities {
        @JsonProperty("authorities")
        List<Authority> authorities;
    }
}