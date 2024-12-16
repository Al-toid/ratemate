package ratemate;

public class DisplayClassTest {

    public static void main(String[] args) {
        // Create an instance of the Display class
        Display display = new Display();

        // Test displayPremiumAmount method
        System.out.println("Testing displayPremiumAmount...");
        int testPremiumAmount = 5000;
        display.displayPremiumAmount(testPremiumAmount);
        System.out.println("Expected Output: Premium Amount: 5000\n");

        // Test displayPrediction method
        System.out.println("Testing displayPrediction...");
        String testPrediction = "High Risk";
        display.displayPrediction(testPrediction);
        System.out.println("Expected Output: Prediction: High Risk\n");

        // Test displaySuggestion method
        System.out.println("Testing displaySuggestion...");
        String testSuggestion = "Consider choosing a lower coverage option.";
        display.displaySuggestion(testSuggestion);
        System.out.println("Expected Output: Suggestion: Consider choosing a lower coverage option.\n");

        // Indicate the end of testing
        System.out.println("All tests completed. Verify outputs manually.");
    }
}