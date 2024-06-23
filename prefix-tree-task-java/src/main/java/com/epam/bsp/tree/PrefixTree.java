package com.epam.bsp.tree;

public class PrefixTree {
    public PrefixTreeNode rootNode;

    public PrefixTree() {
        this.rootNode = new PrefixTreeNode(false);
    }

    public void insert(String s) {
        PrefixTreeNode currentNode = rootNode;
        for (char c : s.toCharArray()) {
            currentNode.incrementPrefixCount();
            currentNode = currentNode.getChildren().computeIfAbsent(c, k -> new PrefixTreeNode(false));
        }
        currentNode.setTerminal(true);
        currentNode.incrementWordCount();
    }

    public boolean search(String s) {
        PrefixTreeNode currentNode = rootNode;
        for (char c : s.toCharArray()) {
            currentNode = currentNode.getChildren().get(c);
            if (currentNode == null) {
                return false;
            }
        }
        return currentNode.isTerminal();
    }

    public boolean startsWith(String s) {
        PrefixTreeNode currentNode = rootNode;
        for (char c : s.toCharArray()) {
            currentNode = currentNode.getChildren().get(c);
            if (currentNode == null) {
                return false;
            }
        }
        return true;
    }
}
