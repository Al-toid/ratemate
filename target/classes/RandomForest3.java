package ru.ml.rf.tasks;

import ru.ml.rf.classifier.RandomForest;
import ru.ml.rf.datasets.DataSet;
import kagglehub;
import java.io.*;
import java.util.*;

public class RandomForestTask {

    public static void main(String[] args) throws IOException {
        // Download dataset using KaggleHub
        String datasetPath = kagglehub.dataset_download("imtkaggleteam/vehicle-insurance-data");
        System.out.println("Path to dataset files: " + datasetPath);

        // File names for the datasets
        String TRAIN_DATA = datasetPath + "/motor_data11-14lats.csv";
        String TEST_DATA = datasetPath + "/motor_data14-2018.csv";

        int estimators = 100;

        DataSet train = loadDataSet(TRAIN_DATA);
        DataSet test = loadDataSet(TEST_DATA);

        // Combine training and testing datasets
        DataSet combinedDataSet = combineDataSets(train, test);

        RandomForest randomForest = new RandomForest(estimators);
        randomForest.train(combinedDataSet);

        evaluateModel(randomForest, test);
    }

    private static DataSet loadDataSet(String filePath) throws IOException {
        List<double[]> features = new ArrayList<>();
        List<Integer> labels = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                double[] featureVector = Arrays.stream(tokens, 0, tokens.length - 1)
                        .mapToDouble(Double::parseDouble)
                        .toArray();
                features.add(featureVector);
                labels.add(Integer.parseInt(tokens[tokens.length - 1]));
            }
        }
        return new DataSet(features, labels);
    }

    private static DataSet combineDataSets(DataSet train, DataSet test) {
        List<double[]> combinedFeatures = new ArrayList<>(train.getFeatures());
        combinedFeatures.addAll(test.getFeatures());

        List<Integer> combinedLabels = new ArrayList<>(train.getLabels());
        combinedLabels.addAll(test.getLabels());

        return new DataSet(combinedFeatures, combinedLabels);
    }

    private static void evaluateModel(RandomForest randomForest, DataSet testSet) {
        int correct = 0;
        for (int i = 0; i < testSet.getSize(); i++) {
            int prediction = randomForest.predict(testSet.getFeatures(i));
            if (prediction == testSet.getLabel(i)) {
                correct++;
            }
        }
        System.out.printf("Accuracy: %.2f%%\n", ((double) correct / testSet.getSize()) * 100);
    }
}

// RandomForest class
package ru.ml.rf.classifier;

import ru.ml.rf.datasets.DataSet;
import ru.ml.rf.nodes.DecisionTree;
import java.util.*;

public class RandomForest {

    private final int numTrees;
    private final List<DecisionTree> trees = new ArrayList<>();

    public RandomForest(int numTrees) {
        this.numTrees = numTrees;
    }

    public void train(DataSet dataset) {
        for (int i = 0; i < numTrees; i++) {
            DataSet bootstrapSample = dataset.bootstrapSample();
            DecisionTree tree = new DecisionTree();
            tree.train(bootstrapSample);
            trees.add(tree);
        }
    }

    public int predict(double[] features) {
        Map<Integer, Integer> votes = new HashMap<>();
        for (DecisionTree tree : trees) {
            int prediction = tree.predict(features);
            votes.put(prediction, votes.getOrDefault(prediction, 0) + 1);
        }
        return votes.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
    }
}


 
// Other TreeNode, DecisionNode, and LeafNode classes remain unchanged
