// SuggestionMaker class
package ru.ml.rf.models;

public class SuggestionMaker {

    public String makeSuggestions(int premiumAmount, double[] features) {
        if (premiumAmount > 5000) {
            return "Consider switching to a policy with lower premiums.";
        } else {
            return "Your current premium is competitive.";
        }
    }