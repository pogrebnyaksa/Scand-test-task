package by.scand.exchangerates.exception;

public class GetRatesException extends RuntimeException {
    public GetRatesException() {
        super();
    }

    public GetRatesException(String message) {
        super(message);
    }

    public GetRatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetRatesException(Throwable cause) {
        super(cause);
    }

    protected GetRatesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
