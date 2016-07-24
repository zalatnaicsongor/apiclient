package hu.zalatnai.foodhygieneratings.authorities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Authority {
    private final int id;

    private final String name;

    @JsonCreator
    public Authority(@JsonProperty("LocalAuthorityId") int id, @JsonProperty("Name") String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
