package observer;


/**
 * Subject interface for the Observer design pattern.
 *
 * <p>Classes that manage observers and broadcast notifications implement this
 * interface. Observers can be registered, removed, and notified of events.</p>
 */
public interface Subject {

    /**
     * Registers an observer to receive notifications.
     *
     * @param observer the observer to register
     */
    void addObserver(Observer observer);

    /**
     * Removes an observer from the notification list.
     *
     * @param observer the observer to remove
     */
    void removeObserver(Observer observer);

    /**
     * Notifies all registered observers with the given message.
     *
     * @param message the notification message
     */
    void notifyObservers(String message);
}