package com.epam.bsp;

import com.epam.bsp.tree.PrefixTree;
import com.epam.bsp.tree.PrefixTreeNode;

public class Solution {

    public static int getNumberOfMatches(String text, String[] patterns) {
        PrefixTree trie = new PrefixTree();
        for (String pattern : patterns) {
            trie.insert(pattern);
        }

        int matchCount = 0;
        for (int i = 0; i < text.length(); i++) {
            PrefixTreeNode currentNode = trie.rootNode;
            for (int j = i; j < text.length(); j++) {
                currentNode = currentNode.getChildren().get(text.charAt(j));
                if (currentNode == null) {
                    break;
                }
                if (currentNode.isTerminal()) {
                    matchCount += currentNode.getWordCount();
                }
            }
        }
        return matchCount;
    }
}
