package exception;

/**
 * Thrown when a rental is attempted on a vehicle that is not available (US2.2).
 *
 * <p>Used to prevent double booking of vehicles.</p>
 */
public class VehicleNotAvailableException extends RuntimeException {

    /**
     * Creates a new exception with the given message.
     *
     * @param message the detail message
     */
    public VehicleNotAvailableException(String message) {
        super(message);
    }
}