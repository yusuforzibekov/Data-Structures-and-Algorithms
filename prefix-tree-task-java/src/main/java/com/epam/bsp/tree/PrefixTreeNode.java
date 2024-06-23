package com.epam.bsp.tree;

import java.util.HashMap;
import java.util.Map;

public class PrefixTreeNode {
    private Map<Character, PrefixTreeNode> children;
    private boolean terminal;
    private int wordCount;  // New field to keep track of the number of words equal to this node's prefix
    private int prefixCount;  // New field to keep track of the number of words starting with this node's prefix

    public PrefixTreeNode(boolean terminal) {
        this.children = new HashMap<>();
        this.terminal = terminal;
        this.wordCount = 0;
        this.prefixCount = 0;
    }

    public Map<Character, PrefixTreeNode> getChildren() {
        return children;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void incrementWordCount() {
        this.wordCount++;
    }

    public void incrementPrefixCount() {
        this.prefixCount++;
    }

    public int getPrefixCount() {
        return prefixCount;
    }
}
