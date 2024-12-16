package ratemate;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SuggestionMakerTest {

    @Test
    void testMakeSuggestionsHighPremium() {
        SuggestionMaker suggestionMaker = new SuggestionMaker();
        double[] features = {2500, 4, 2}; // Example feature array (not used in this logic)
        int premiumAmount = 6000;

        String suggestion = suggestionMaker.makeSuggestions(premiumAmount, features);

        assertEquals("Consider switching to a policy with lower premiums.", suggestion);
    }

    @Test
    void testMakeSuggestionsLowPremium() {
        SuggestionMaker suggestionMaker = new SuggestionMaker();
        double[] features = {1500, 3, 1}; // Example feature array (not used in this logic)
        int premiumAmount = 4000;

        String suggestion = suggestionMaker.makeSuggestions(premiumAmount, features);

        assertEquals("Your current premium is competitive.", suggestion);
    }
}
