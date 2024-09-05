package com.epam.bsp;

import com.epam.bsp.node.TreeNode;

import java.util.*;

public class Solution {

    /**
     * Returns the root of the resulting Huffman coding tree.
     *
     * NOTE: the `text` consists of lowercase symbols from English alphabet only.
     * NOTE: `ALPHABET_SIZE` or `A` will be used to denote the number of distinct
     * characters in a given text.
     * NOTE: `N` will be used to denote the size of a given text.
     * 
     * Two steps are required to build a Huffman coding based on a given text:
     * (1) Calculate and order character frequencies/counts
     * (2) Build a tree that represents the resulting Huffman coding
     * 
     * NOTE: The expected time complexity for the first step is O(N + A * log(A)).
     * NOTE: The expected time complexity for the second step is O(A * log(A)).
     * 
     * Example:
     * (0) text="cabbaa"
     * (1) character counts = [('a', 3), ('b', 2), ('c', 1)]
     * 
     * NOTE: if the frequencies for some characters (nodes) are the same,
     * order those characters lexicographically.
     * 
     * (2) ("abc", 5)
     * | \
     * | \
     * | \
     * ("a", 3) ("bc", 3)
     * | \
     * | \
     * ("b", 2) ("c", 1)
     * 
     * 'a' -> "0"
     * 'b' -> "10"
     * 'c' -> "11"
     * 
     * 
     * @param text a given text that should be used to build the Huffman coding.
     * @return the root of the resulting Huffman coding
     */
    public static TreeNode createHuffmanCoding(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        List<TreeNode> nodes = new ArrayList<>();
        for (var entry : frequencyMap.entrySet()) {
            nodes.add(new TreeNode(entry.getKey(), entry.getValue(), null, null));
        }

        while (nodes.size() > 1) {
            nodes.sort((o1, o2) -> {
                int result = Integer.compare(o1.getFrequency(), o2.getFrequency());
                if (result == 0) {
                    result = Character.compare(o2.getCh(), o1.getCh());
                }
                return result;
            });
            TreeNode left = nodes.remove(0);
            TreeNode right = nodes.remove(0);
            int sum = left.getFrequency() + right.getFrequency();
            char ch;
            if (left.getFrequency() > right.getFrequency()) {
                ch = left.getCh();
            } else if (left.getFrequency() < right.getFrequency()) {
                ch = right.getCh();
                TreeNode temp = left;
                left = right;
                right = temp;
            } else {
                if (left.getCh() < right.getCh()) {
                    ch = left.getCh();
                } else {
                    ch = right.getCh();
                    TreeNode temp = left;
                    left = right;
                    right = temp;
                }
            }
            nodes.add(new TreeNode(ch, sum, left, right));
        }

        return nodes.get(0);
    }

    public static void createHuffmanCodingTree(String text) {
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        List<TreeNode> nodes = new ArrayList<>();
        for (var entry : frequencyMap.entrySet()) {
            nodes.add(new TreeNode(entry.getKey(), entry.getValue(), null, null));
        }

        while (nodes.size() > 1) {
            nodes.sort((o1, o2) -> {
                int result = Integer.compare(o1.getFrequency(), o2.getFrequency());
                if (result == 0) {
                    result = Character.compare(o2.getCh(), o1.getCh());
                }
                return result;
            });
            TreeNode left = nodes.remove(0);
            TreeNode right = nodes.remove(0);
            int sum = left.getFrequency() + right.getFrequency();
            char ch;
            if (left.getFrequency() > right.getFrequency()) {
                ch = left.getCh();
            } else if (left.getFrequency() < right.getFrequency()) {
                ch = right.getCh();
                TreeNode temp = left;
                left = right;
                right = temp;
            } else {
                if (left.getCh() < right.getCh()) {
                    ch = left.getCh();
                } else {
                    ch = right.getCh();
                    TreeNode temp = left;
                    left = right;
                    right = temp;
                }
            }
            nodes.add(new TreeNode(ch, sum, left, right));
        }

        printHuffmanTree(nodes.get(0), "");
    }

    private static void printHuffmanTree(TreeNode root, String code) {
        if (root.getLeftNode() == null && root.getRightNode() == null) {
            System.out.println(root.getCh() + " -> " + code);
            return;
        }
        printHuffmanTree(root.getLeftNode(), code + "0");
        printHuffmanTree(root.getRightNode(), code + "1");
    }

    public static void main(String[] args) {
        String text1 = "cabbaa";
        System.out.println("For text = " + text1);
        Solution.createHuffmanCodingTree(text1);
        System.out.println();
        String text2 = "aaaabbbbccdd";
        System.out.println("For text = " + text2);
        Solution.createHuffmanCodingTree(text2);
        System.out.println();
        String text3 = "abcabdedeea";
        System.out.println("For text = " + text3);
        Solution.createHuffmanCodingTree(text3);
        System.out.println();
        String text4 = "aaaa";
        System.out.println("For text = " + text4);
        Solution.createHuffmanCodingTree(text4);
    }

    /**
     * Returns an encoded message using Huffman coding for a given text.
     *
     * NOTE: the `text` consists of lowercase symbols from English alphabet only.
     * 
     * @param text              a given text to encode.
     * @param huffmanCodingRoot the root node that represents a given Huffman
     *                          coding.
     * @return the encoded text.
     */
    public static String encodeHuffman(String text, TreeNode huffmanCodingRoot) {
        Map<Character, String> codeMap = new HashMap<>();
        if (huffmanCodingRoot.getLeftNode() == null && huffmanCodingRoot.getRightNode() == null) {
            codeMap.put(huffmanCodingRoot.getCh(), "0");
        } else {
            encodeHuffmanHelper(huffmanCodingRoot, "", codeMap);
        }
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(codeMap.get(c));
        }
        return sb.toString();
    }

    private static void encodeHuffmanHelper(TreeNode node, String code, Map<Character, String> codeMap) {
        if (node.getLeftNode() == null && node.getRightNode() == null) {
            codeMap.put(node.getCh(), code);
            return;
        }
        if (node.getLeftNode() != null) {
            encodeHuffmanHelper(node.getLeftNode(), code + "0", codeMap);
        }
        if (node.getRightNode() != null) {
            encodeHuffmanHelper(node.getRightNode(), code + "1", codeMap);
        }
    }

    /**
     * Uses Huffman coding to return the decoded message for a given text.
     *
     * NOTE: the `text` consists of lowercase symbols from English alphabet only.
     * 
     * @param text              a given text to decode.
     * @param huffmanCodingRoot the root node that represents a given Huffman
     *                          coding.
     * @return the decoded text.
     */
    public static String decodeHuffman(String text, TreeNode huffmanCodingRoot) {
        StringBuilder sb = new StringBuilder();
        if (huffmanCodingRoot.getLeftNode() == null && huffmanCodingRoot.getRightNode() == null) {
            sb.append(String.valueOf(huffmanCodingRoot.getCh()).repeat(text.length()));
        } else {
            TreeNode currentNode = huffmanCodingRoot;
            for (char c : text.toCharArray()) {
                if (c == '0') {
                    currentNode = currentNode.getLeftNode();
                } else {
                    currentNode = currentNode.getRightNode();
                }
                if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
                    sb.append(currentNode.getCh());
                    currentNode = huffmanCodingRoot;
                }
            }
        }
        return sb.toString();
    }
}