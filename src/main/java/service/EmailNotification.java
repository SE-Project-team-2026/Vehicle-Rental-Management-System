package service;

import observer.Observer;

/**
 * Notification observer that sends notifications via email.
 *
 * <p>Implements the {@link Observer} interface so it can be registered with a
 * {@link observer.Subject} and receive system events (US3.1).</p>
 */
public class EmailNotification implements Observer {

    /** Email address to which notifications are sent. */
    private String email;

    /**
     * Creates a new email notification service for the given address.
     *
     * @param email the recipient email address
     */
    public EmailNotification(String email) {
        this.email = email;
    }

    @Override
    public void update(String message) {
        System.out.println("Sending email to " + email + ": " + message);
    }
}
