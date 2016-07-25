package hu.zalatnai.foodhygieneratings.authorities;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return id == authority.id &&
            Objects.equals(name, authority.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
