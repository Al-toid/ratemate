package ratemate;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.ml.rf.models.User;
class UserTest {

    @Test
    void testUserInitialization() {
        String name = "John Doe";
        int age = 30;
        double[] features = {1.0, 0.5, 2.3};
        int premiumAmount = 4500;

        User user = new User(name, age, features, premiumAmount);

        // Assertion with expected output
        assertEquals(name, user.getName(), "Expected name to be 'John Doe'");
        assertEquals(age, user.getAge(), "Expected age to be 30");
        assertArrayEquals(features, user.getFeatures(), "Expected features to match the input features");
        assertEquals(premiumAmount, user.getPremiumAmount(), "Expected premium amount to be 4500");
    }
}
