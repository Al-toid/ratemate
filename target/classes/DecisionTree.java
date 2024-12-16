package ru.ml.rf.nodes;

import ru.ml.rf.datasets.DataSet;

public class DecisionTree {

    private TreeNode root;

    public void train(DataSet dataset) {
        this.root = buildTree(dataset);
    }

    public int predict(double[] features) {
        return traverseTree(root, features);
    }

    private TreeNode buildTree(DataSet dataset) {
        if (dataset.shouldTerminate()) {
            return new LeafNode(dataset.getMajorityLabel());
        }

        int splitFeature = dataset.findBestSplitFeature();
        double splitValue = dataset.findSplitValue(splitFeature);

        DataSet[] subsets = dataset.split(splitFeature, splitValue);
        DecisionNode node = new DecisionNode(splitFeature, splitValue);
        node.setLeft(buildTree(subsets[0]));
        node.setRight(buildTree(subsets[1]));
        return node;
    }

    private int traverseTree(TreeNode node, double[] features) {
        if (node instanceof LeafNode) {
            return ((LeafNode) node).getLabel();
        }

        DecisionNode decisionNode = (DecisionNode) node;
        if (features[decisionNode.getSplitFeature()] < decisionNode.getSplitValue()) {
            return traverseTree(decisionNode.getLeft(), features);
        } else {
            return traverseTree(decisionNode.getRight(), features);
        }
    }
}
