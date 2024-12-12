package ru.ml.rf.tasks;

import ru.ml.rf.classifier.RandomForest;
import ru.ml.rf.datasets.DataSet;
import ru.ml.rf.datasets.Instance;
import java.io.*;
import java.util.*;

public class RandomForestTask {

    private static final String TRAIN_DATA = // datasets;
    private static final String TEST_DATA = //datasets ;

    /**
     * args[0] = path to train dataset
     * args[1] = path to test dataset
     * args[2] = number of estimators
     * args[3] = max features
     * args[4] = min samples leaf
     */
    public static void main(String[] args) throws IOException {
        RandomForestTask task = new RandomForestTask();

        int estimators = args.length >= 3 ? Integer.parseInt(args[2]) : 100;
        int maxFeatures = args.length >= 4 ? Integer.parseInt(args[3]) : 8;
        int minSamplesLeaf = args.length >= 5 ? Integer.parseInt(args[4]) : 1;

        Pair<List<double[]>, List<Integer>> trainData = task.getData(TRAIN_DATA);
        Pair<List<double[]>, List<Integer>> testData = task.getData(TEST_DATA);

        DataSet train = new DataSet(trainData.first, trainData.second);
        DataSet test = new DataSet(testData.first, testData.second);

        RandomForest rf = new RandomForest(train, estimators, maxFeatures, minSamplesLeaf);

        int correct = 0;
        int total = test.getSize();
        for (int i = 0; i < total; i++) {
            Instance sample = test.getInstance(i);
            int predictedLabel = rf.predictLabel(sample.getFeatureVector());
            if (sample.getLabelIndex() == predictedLabel) {
                correct++;
            }
        }

        System.out.printf("Accuracy: %.2f%%\n", ((double) correct / total) * 100);
    }

    private Pair<List<double[]>, List<Integer>> getData(String path) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            List<double[]> features = new ArrayList<>();
            List<Integer> labels = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                // Assuming the label is the last column
                double[] featureVector = preprocessFeatures(Arrays.copyOf(tokens, tokens.length - 1));
                features.add(featureVector);
                labels.add(Integer.parseInt(tokens[tokens.length - 1]));
            }
            return new Pair<>(features, labels);
        }
    }

    private double[] preprocessFeatures(String[] rawFeatures) {
        double[] processed = new double[rawFeatures.length];

        for (int i = 0; i < rawFeatures.length; i++) {
            try {
                processed[i] = Double.parseDouble(rawFeatures[i]);
            } catch (NumberFormatException e) {
                processed[i] = handleCategoricalFeature(rawFeatures[i]);
            }
        }

        return processed;
    }

    private double handleCategoricalFeature(String feature) {
        Map<String, Double> mappings = new HashMap<>() {{
            put("Male", 0.0);
            put("Female", 1.0);
            put("Yes", 1.0);
            put("No", 0.0);
        }};

        return mappings.getOrDefault(feature, -1.0); // Default to -1 if unknown
    }

    private static class Pair<K, V> {
        K first;
        V second;

        public Pair(K first, V second) {
            this.first = first;
            this.second = second;
        }
    }
}

// TreeNode class
package ru.ml.rf.nodes;

public abstract class TreeNode {

    protected int depth;

    public TreeNode() {
        depth = 0;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}

// LeafNode class
package ru.ml.rf.nodes;

import java.util.List;

public class LeafNode extends TreeNode {

    private double[] labelProbDist;

    public LeafNode(int numLabels, List<Integer> labels) {
        super();
        labelProbDist = new double[numLabels];
        int size = labels.size();
        for (Integer label : labels) {
            labelProbDist[label] += 1.0 / size;
        }
    }

    public double[] getLabelProbDist() {
        return labelProbDist;
    }
}

// DecisionNode class
package ru.ml.rf.nodes;

public class DecisionNode extends TreeNode {

    private int splittingFeatureIndex;
    private double splittingFeatureValue;
    private TreeNode left;
    private TreeNode right;

    public DecisionNode(int splittingFeatureIndex, double splittingFeatureValue) {
        super();
        this.splittingFeatureIndex = splittingFeatureIndex;
        this.splittingFeatureValue = splittingFeatureValue;
    }

    public void setLeftChild(TreeNode left) {
        this.left = left;
    }

    public void setRightChild(TreeNode right) {
        this.right = right;
    }

    public TreeNode getLeftChild() {
        return left;
    }

    public TreeNode getRightChild() {
        return right;
    }

    public int getSplittingFeatureIndex() {
        return splittingFeatureIndex;
    }

    public double getSplittingValue() {
        return splittingFeatureValue;
    }
}

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
}

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
}

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
}

// Display class
package ru.ml.rf.ui;

public class Display {

    public void displayPremiumAmount(int premiumAmount) {
        System.out.println("Premium Amount: " + premiumAmount);
    }

    public void displayPrediction(String prediction) {
        System.out.println("Prediction: " + prediction);
    }

    public void displaySuggestion(String suggestion) {
        System.out.println("Suggestion: " + suggestion);
    }
}
