package hu.zalatnai.foodhygieneratings.authorities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthoritiesController {
    private final AuthoritiesService authoritiesService;

    @Autowired
    AuthoritiesController(AuthoritiesService authoritiesService) {
        this.authoritiesService = authoritiesService;
    }

    @RequestMapping(value = "/authorities", method = RequestMethod.GET, produces = "application/json")
    public List<Authority> getAuthorities() {
        return authoritiesService.getAuthorities();
    }
}
