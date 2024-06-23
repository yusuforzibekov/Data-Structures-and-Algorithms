package com.epam.bsp;

import com.epam.bsp.node.TreeNode;
import java.util.*;

public class Solution {

    static class NodeWithFrequency implements Comparable<NodeWithFrequency> {
        TreeNode node;
        int frequency;

        NodeWithFrequency(TreeNode node, int frequency) {
            this.node = node;
            this.frequency = frequency;
        }

        @Override
        public int compareTo(NodeWithFrequency o) {
            int frequencyComparison = Integer.compare(this.frequency, o.frequency);
            if (frequencyComparison != 0) {
                return frequencyComparison;
            } else {
                // For non-leaf nodes, treat them as greater if frequencies are equal
                if (this.node.getCh() == '\0' && o.node.getCh() != '\0') {
                    return 1;
                } else if (this.node.getCh() != '\0' && o.node.getCh() == '\0') {
                    return -1;
                }
                // For leaf nodes, compare based on character value
                return Character.compare(this.node.getCh(), o.node.getCh());
            }
        }
    }

    public static TreeNode createHuffmanCoding(String text) {
        // Step 1: Calculate character frequencies
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }
    
        // Step 2: Create a priority queue to build the Huffman tree, now with frequencies
        PriorityQueue<NodeWithFrequency> queue = new PriorityQueue<>();
    
        // Initialize queue with leaf nodes and their frequencies
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            queue.offer(new NodeWithFrequency(new TreeNode(entry.getKey(), null, null), entry.getValue()));
        }
    
        // Step 3: Build the Huffman tree
        while (queue.size() > 1) {
            NodeWithFrequency left = queue.poll();
            NodeWithFrequency right = queue.poll();
    
            // Ensure the node with smaller or equal frequency is always on the left
            // This maintains a consistent structure for the Huffman tree
            if (right.frequency < left.frequency) {
                NodeWithFrequency temp = left;
                left = right;
                right = temp;
            }
    
            // Combine nodes to create a new parent node with combined frequency
            int combinedFrequency = left.frequency + right.frequency;
            TreeNode combinedNode = new TreeNode('\0', left.node, right.node);
    
            // Add combined node back to the queue with updated frequency for sorting
            queue.offer(new NodeWithFrequency(combinedNode, combinedFrequency));
        }
    
        return queue.poll().node; // Return the root of the Huffman tree
    }    

    public static String encodeHuffman(String text, TreeNode huffmanCodingRoot) {
        if (huffmanCodingRoot.getLeftNode() == null && huffmanCodingRoot.getRightNode() == null) {
            // Handle single character edge case
            return "0".repeat(text.length());
        }

        Map<Character, String> huffmanMap = new HashMap<>();
        buildHuffmanMap(huffmanCodingRoot, "", huffmanMap);

        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanMap.get(c));
        }

        return encodedText.toString();
    }

    private static void buildHuffmanMap(TreeNode node, String code, Map<Character, String> huffmanMap) {
        if (node == null)
            return;
        if (node.getLeftNode() == null && node.getRightNode() == null) {
            huffmanMap.put(node.getCh(), code);
        }
        buildHuffmanMap(node.getLeftNode(), code + "0", huffmanMap);
        buildHuffmanMap(node.getRightNode(), code + "1", huffmanMap);
    }

    // decodeHuffman method remains unchanged

    public static String decodeHuffman(String text, TreeNode huffmanCodingRoot) {
        if (huffmanCodingRoot.getLeftNode() == null && huffmanCodingRoot.getRightNode() == null) {
            char ch = huffmanCodingRoot.getCh();
            return String.valueOf(ch).repeat(text.length());
        }

        StringBuilder decodedText = new StringBuilder();
        TreeNode currentNode = huffmanCodingRoot;

        for (char bit : text.toCharArray()) {
            currentNode = (bit == '0') ? currentNode.getLeftNode() : currentNode.getRightNode();

            if (currentNode.getLeftNode() == null && currentNode.getRightNode() == null) {
                decodedText.append(currentNode.getCh());
                currentNode = huffmanCodingRoot;
            }
        }

        return decodedText.toString();
    }
}