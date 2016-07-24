package hu.zalatnai.foodhygieneratings.shared;

public class ServiceUnavailableException extends APIException {
    @Override
    public int getStatusCode() {
        return 503;
    }
}