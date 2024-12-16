package ratemate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class PrimaryControllerTest {

    @Test
    public void testSwitchToSecondary() {
        try (MockedStatic<App> appMock = Mockito.mockStatic(App.class)) {
            appMock.when(() -> App.setRoot("secondary")).thenReturn(null); // Simulate App.setRoot()

            PrimaryController controller = new PrimaryController();
            assertDoesNotThrow(controller::switchToSecondary);
        }
    }

    @Test
    public void testSwitchToSecondaryIOException() {
        try (MockedStatic<App> appMock = Mockito.mockStatic(App.class)) {
            appMock.when(() -> App.setRoot("secondary"))
                    .thenThrow(new IOException("File not found"));

            PrimaryController controller = new PrimaryController();
            IOException ex = assertThrows(IOException.class, controller::switchToSecondary);
            assertEquals("File not found", ex.getMessage());
        }
    }
}
