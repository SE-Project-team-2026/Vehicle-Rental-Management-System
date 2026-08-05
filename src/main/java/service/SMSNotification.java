package service;

import observer.Observer;

/**
 * Notification observer that sends notifications via SMS.
 *
 * <p>Implements the {@link Observer} interface so it can be registered with a
 * {@link observer.Subject} and receive system events (US3.1).</p>
 */
public class SMSNotification implements Observer {

    /** Phone number to which SMS notifications are sent. */
    private String phoneNumber;

    /**
     * Creates a new SMS notification service for the given phone number.
     *
     * @param phoneNumber the recipient phone number
     */
    public SMSNotification(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String message) {
        System.out.println("Sending SMS to " + phoneNumber + ": " + message);
    }
}
