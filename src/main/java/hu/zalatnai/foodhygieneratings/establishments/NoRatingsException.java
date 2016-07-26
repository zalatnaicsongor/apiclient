package hu.zalatnai.foodhygieneratings.establishments;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NoRatingsException extends RuntimeException {
}
