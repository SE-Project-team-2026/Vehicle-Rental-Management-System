package observer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class NotificationObserverTest {

    @Test
    void testNotificationUpdate() {

        NotificationObserver observer = new NotificationObserver();

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        observer.update("Vehicle returned successfully");

        assertEquals(
                "Notification: Vehicle returned successfully" + System.lineSeparator(),
                output.toString()
        );
    }
}