package service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link SMSNotification}.
 */
class SMSNotificationTest {

    @Test
    void testUpdateOutputsCorrectMessage() {
        String phoneNumber = "+970599999999";
        SMSNotification notification = new SMSNotification(phoneNumber);

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        notification.update("Truck rented");

        assertEquals(
                "Sending SMS to " + phoneNumber + ": Truck rented" + System.lineSeparator(),
                output.toString()
        );
    }
}