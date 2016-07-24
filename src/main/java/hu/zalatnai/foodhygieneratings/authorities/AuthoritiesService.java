package hu.zalatnai.foodhygieneratings.authorities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {
    private final AuthoritiesAPIClient apiClient;

    @Autowired
    AuthoritiesService(AuthoritiesAPIClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<Authority> getAuthorities() {
        return apiClient.getAuthorities();
    }
}