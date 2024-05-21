package com.epam.bsp.binary.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeNode<E> {
    /** * Data part */
    private E element;
    /** * Links to the left and right nodes */
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

    /**
     * Checks whether two given binary trees are the same.
     * @param treeNode root node of the second given tree.
     * @return whether this tree is equal to the second tree.
     */
    public boolean check(TreeNode<E> treeNode) {
      // Both nodes are null
      if (this == null && treeNode == null) {
          return true;
      }
      // One node is null and the other is not
      if (this == null || treeNode == null) {
          return false;
      }
      // Both nodes are not null, compare the elements and recurse on children
      if (!this.element.equals(treeNode.element)) {
          return false;
      }
      boolean leftCheck = (this.left == null) ? treeNode.left == null : this.left.check(treeNode.left);
      boolean rightCheck = (this.right == null) ? treeNode.right == null : this.right.check(treeNode.right);
      return leftCheck && rightCheck;
  }
  

    /**
     * Returns elements of a given binary tree using inorder traversal.
     * @return List with elements that correspond to inorder nodes traversal.
     */
    public List<E> getInorderTraversal() {
        List<E> result = new ArrayList<>();
        inorderHelper(this, result);
        return result;
    }

    private void inorderHelper(TreeNode<E> node, List<E> result) {
        if (node != null) {
            inorderHelper(node.left, result);
            result.add(node.element);
            inorderHelper(node.right, result);
        }
    }

    /**
     * Returns elements of a given binary tree using postorder traversal.
     * @return List with elements that correspond to postorder nodes traversal.
     */
    public List<E> getPostorderTraversal() {
        List<E> result = new ArrayList<>();
        postorderHelper(this, result);
        return result;
    }

    private void postorderHelper(TreeNode<E> node, List<E> result) {
        if (node != null) {
            postorderHelper(node.left, result);
            postorderHelper(node.right, result);
            result.add(node.element);
        }
    }

    /**
     * Returns elements of a given binary tree using preorder traversal.
     * @return List with elements that correspond to preorder nodes traversal.
     */
    public List<E> getPreorderTraversal() {
        List<E> result = new ArrayList<>();
        preorderHelper(this, result);
        return result;
    }

    private void preorderHelper(TreeNode<E> node, List<E> result) {
        if (node != null) {
            result.add(node.element);
            preorderHelper(node.left, result);
            preorderHelper(node.right, result);
        }
    }

    /**
     * Returns elements of a given binary tree using level order traversal.
     * @return List with elements that correspond to level order nodes traversal.
     */
    public List<E> getLevelOrderTraversal() {
        List<E> result = new ArrayList<>();
        Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(this);
        while (!queue.isEmpty()) {
            TreeNode<E> current = queue.poll();
            result.add(current.element);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
        return result;
    }
}
