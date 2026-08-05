package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link EmailNotification}.
 */
class EmailNotificationTest {

    @Test
    void testUpdateOutputsCorrectMessage() {
        String email = "emanthaher794@gmail.com";
        EmailNotification notification = new EmailNotification(email);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        notification.update("Car rented");

        assertEquals(
                "Sending email to " + email + ": Car rented" + System.lineSeparator(),
                output.toString()
        );
    }
}