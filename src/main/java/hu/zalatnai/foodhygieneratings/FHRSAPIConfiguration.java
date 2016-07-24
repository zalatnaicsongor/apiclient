package hu.zalatnai.foodhygieneratings;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "fhrsapi")
public class FHRSAPIConfiguration {
    @NotNull
    private String authoritiesBasicUri;

    @NotNull
    private String establishmentsUri;

    public String getAuthoritiesBasicUri() {
        return authoritiesBasicUri;
    }

    public void setAuthoritiesBasicUri(String authoritiesBasicUri) {
        this.authoritiesBasicUri = authoritiesBasicUri;
    }

    public String getEstablishmentsUri() {
        return establishmentsUri;
    }

    public void setEstablishmentsUri(String establishmentsUri) {
        this.establishmentsUri = establishmentsUri;
    }
}
