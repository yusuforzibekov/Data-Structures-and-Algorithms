package com.epam.bsp.tree;

public class AugmentedPrefixTree extends PrefixTree {

    public int countWordsEqualTo(String s) {
        PrefixTreeNode currentNode = rootNode;
        for (char c : s.toCharArray()) {
            currentNode = currentNode.getChildren().get(c);
            if (currentNode == null) {
                return 0;
            }
        }
        return currentNode.isTerminal() ? currentNode.getWordCount() : 0;
    }

    public int countWordsStartingWith(String s) {
        PrefixTreeNode currentNode = rootNode;
        for (char c : s.toCharArray()) {
            currentNode = currentNode.getChildren().get(c);
            if (currentNode == null) {
                return 0;
            }
        }
        return currentNode.getPrefixCount() + (currentNode.isTerminal() ? currentNode.getWordCount() : 0);
    }
}
