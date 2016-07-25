package hu.zalatnai.foodhygieneratings.shared;

public class ServiceUnavailableException extends APIException {
    public ServiceUnavailableException(Throwable cause) {
        super(cause);
    }

    @Override
    public int getStatusCode() {
        return 503;
    }
}