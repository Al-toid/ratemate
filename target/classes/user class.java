// User class
package ru.ml.rf.models;

public class User {

    private String name;
    private int age;
    private double[] features;
    private int premiumAmount;

    public User(String name, int age, double[] features, int premiumAmount) {
        this.name = name;
        this.age = age;
        this.features = features;
        this.premiumAmount = premiumAmount;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double[] getFeatures() {
        return features;
    }

    public int getPremiumAmount() {
        return premiumAmount;
    }