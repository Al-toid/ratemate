package ratemate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class SecondaryControllerTest {

    @Test
    public void testSwitchToPrimary() {
        try (MockedStatic<App> appMock = Mockito.mockStatic(App.class)) {
            appMock.when(() -> App.setRoot("primary")).thenReturn(null);

            SecondaryController controller = new SecondaryController();
            assertDoesNotThrow(controller::switchToPrimary);
        }
    }

    @Test
    public void testSwitchToPrimaryIOException() {
        try (MockedStatic<App> appMock = Mockito.mockStatic(App.class)) {
            appMock.when(() -> App.setRoot("primary"))
                    .thenThrow(new IOException("File not found"));

            SecondaryController controller = new SecondaryController();
            IOException ex = assertThrows(IOException.class, controller::switchToPrimary);
            assertEquals("File not found", ex.getMessage());
        }
    }
}
