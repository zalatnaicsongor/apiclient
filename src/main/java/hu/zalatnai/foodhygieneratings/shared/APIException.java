package hu.zalatnai.foodhygieneratings.shared;

public abstract class APIException extends RuntimeException {
    public abstract int getStatusCode();
}
