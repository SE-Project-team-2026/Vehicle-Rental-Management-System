package observer;


/**
 * Observer interface for the Observer design pattern.
 *
 * <p>Classes that wish to be notified of system events (e.g. email or SMS
 * notifications) implement this interface and are registered with a
 * {@link Subject}.</p>
 */
public interface Observer {

    /**
     * Called by a subject to deliver a notification message.
     *
     * @param message the notification message
     */
    void update(String message);
}