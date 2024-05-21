package com.epam.bsp.binary.tree;

public class TreeNode<E extends Comparable<E>> {

    /**
     * Data part
     */
    private E element;
    /**
     * Links to the left and right nodes
     */
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    public TreeNode(E element) {
        this(element, null, null);
    }

    public TreeNode(E element, TreeNode<E> left) {
        this(element, left, null);
    }

    public E getElement() {
        return element;
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }

    public boolean isBalanced() {
        //put your code here
        return getHeight() != -1;
    }

    private int getHeight() {
        int leftHeight = left == null ? 0 : left.getHeight();
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = right == null ? 0 : right.getHeight();
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public boolean isBinarySearchTree() {
        //put your code here
        return isBinarySearchTree(this, null, null);
    }

    private boolean isBinarySearchTree(TreeNode<E> node, E min, E max) {
        if (node == null) {
            return true;
        }

        if ((min != null && node.getElement().compareTo(min) <= 0) || (max != null && node.getElement().compareTo(max) >= 0)) {
            return false;
        }

        return isBinarySearchTree(node.getLeft(), min, node.getElement()) && isBinarySearchTree(node.getRight(), node.getElement(), max);
    }

    public TreeNode<E> searchInBst(E element) {
        //put your code here
        return searchInBst(this, element);
    }

    private TreeNode<E> searchInBst(TreeNode<E> node, E element) {
        if (node == null || node.getElement().compareTo(element) == 0) {
            return node;
        }

        if (node.getElement().compareTo(element) > 0) {
            return searchInBst(node.getLeft(), element);
        } else {
            return searchInBst(node.getRight(), element);
        }
    }

    public TreeNode<E> insertInBst(E element) {
        //put your code here
        return insertInBst(this, element);
    }

    private TreeNode<E> insertInBst(TreeNode<E> node, E element) {
        if (node == null) {
            return new TreeNode<>(element);
        }

        if (element.compareTo(node.getElement()) < 0) {
            node.setLeft(insertInBst(node.getLeft(), element));
        } else {
            node.setRight(insertInBst(node.getRight(), element));
        }

        return node;
    }

}
