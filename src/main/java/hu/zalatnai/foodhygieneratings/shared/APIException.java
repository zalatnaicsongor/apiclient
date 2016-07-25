package hu.zalatnai.foodhygieneratings.shared;

public abstract class APIException extends RuntimeException {
    public APIException(Throwable cause) {
        super(cause);
    }

    public abstract int getStatusCode();
}
