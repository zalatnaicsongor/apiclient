package hu.zalatnai.foodhygieneratings.authorities;

import java.util.List;

public interface AuthoritiesAPIClient {
    /**
     * Retrieves the list of authorities
     * @return list of authorities
     */
    List<Authority> getAuthorities();
}
