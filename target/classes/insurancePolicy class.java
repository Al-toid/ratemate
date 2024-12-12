// InsurancePolicy class
package ru.ml.rf.models;

import ru.ml.rf.classifier.RandomForest;

public class InsurancePolicy {

    private RandomForest randomForest;

    public InsurancePolicy(RandomForest randomForest) {
        this.randomForest = randomForest;
    }

    public String predictPremiumCategory(double[] features) {
        int label = randomForest.predictLabel(features);
        return label == 1 ? "High" : "Low";
    }