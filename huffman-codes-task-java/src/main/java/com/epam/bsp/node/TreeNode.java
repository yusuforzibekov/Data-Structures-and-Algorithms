package com.epam.bsp.node;

public class TreeNode implements Comparable<TreeNode> {
    /**
     * Class for tree nodes that will be used to create a Huffman coding.
     */
    private char ch;
    private int frequency;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(char ch, TreeNode leftNode, TreeNode rightNode) {
        this.ch = ch;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public TreeNode(char ch, int frequency, TreeNode leftNode, TreeNode rightNode) {
        this.ch = ch;
        this.frequency = frequency;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public char getCh() {
        return ch;
    }

    public int getFrequency() {
        return frequency;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    @Override
    public int compareTo(TreeNode o) {
        int result = Integer.compare(this.frequency, o.frequency);
        if (result == 0) {
            result = Character.compare(this.ch, o.ch);
        }
        return result;
    }
}