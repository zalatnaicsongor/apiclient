package hu.zalatnai.foodhygieneratings.authorities;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthoritiesServiceTest {
    @Mock
    private AuthoritiesAPIClient apiClient;

    @InjectMocks
    private AuthoritiesService authoritiesService;

    private List<Authority> authorities = new ArrayList<>();

    @Before
    public void setUp() {
        when(apiClient.getAuthorities()).thenReturn(authorities);
    }

    @Test
    public void retrievesTheListOfAuthoritiesFromTheApiClient() {
        authoritiesService.getAuthorities();
        verify(apiClient).getAuthorities();
    }

    @Test
    public void returnsTheRetrievedListOfAuthorities() {
        List<Authority> authorities = authoritiesService.getAuthorities();
        assertEquals(this.authorities, authorities);
    }
}