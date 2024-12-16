package ratemate;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import javafx.scene.Parent;

public class AppTest {

    @Test
    public void testLoadFXMLValidFile() {
        // Test that a valid FXML file can be loaded (without throwing an exception)
        assertDoesNotThrow(() -> {
            Parent root = App.loadFXML("primary");  // Assuming "primary.fxml" is a valid file
            assertNotNull(root); // Make sure the returned root is not null
        });
    }

    @Test
    public void testLoadFXMLInvalidFile() {
        // Test that loading a non-existent FXML file throws IOException
        assertThrows(IOException.class, () -> App.loadFXML("nonexistent"));
    }

    @Test
    public void testSetRootNullThrowsException() {
        // Test setting root to null
        assertThrows(NullPointerException.class, () -> App.setRoot(null));
    }

    @Test
    public void testLaunchApplication() {
        // Verify that launching the app doesn't throw exceptions
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }
}
