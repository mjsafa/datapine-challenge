package mojtaba.safaeian.domain.chart;

import mojtaba.safaeian.domain.ApplicationException;

/**
 * @author Mojtaba Safaeian
 *         Created at: 09/27/2017.
 */
public class InvalidQueryException extends ApplicationException {
    public InvalidQueryException() {
    }

    public InvalidQueryException(String message) {
        super(message);
    }

    public InvalidQueryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidQueryException(Throwable cause) {
        super(cause);
    }

    public InvalidQueryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
