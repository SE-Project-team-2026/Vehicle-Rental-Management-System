package observer;


/**
 * Simple notification observer that prints messages to the console.
 *
 * <p>Used as a lightweight default observer for demonstration purposes.</p>
 */
public class NotificationObserver implements Observer {

    @Override
    public void update(String message) {
        System.out.println("Notification: " + message);
    }
}