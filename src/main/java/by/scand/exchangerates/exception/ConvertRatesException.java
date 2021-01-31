package by.scand.exchangerates.exception;

public class ConvertRatesException extends RuntimeException {
    public ConvertRatesException() {
        super();
    }

    public ConvertRatesException(String message) {
        super(message);
    }

    public ConvertRatesException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertRatesException(Throwable cause) {
        super(cause);
    }

    protected ConvertRatesException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
