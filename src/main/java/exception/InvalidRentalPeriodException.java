package exception;

/**
 * Thrown when a rental period is invalid (US2.3).
 *
 * <p>Examples include an end date before the start date or a rental period
 * of zero days.</p>
 */
public class InvalidRentalPeriodException extends RuntimeException {

    /**
     * Creates a new exception with the given message.
     *
     * @param message the detail message
     */
    public InvalidRentalPeriodException(String message) {
        super(message);
    }
}