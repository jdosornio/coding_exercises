package me.jdosornio.structures.trees;

// https://www.hackerrank.com/challenges/tree-huffman-decoding/problem
public class HuffmanTree {

    class Node {
        public int frequency;
        public char data;
        public Node left, right;
    }

    void decode(String s, Node root) {
        char[] encoded = s.toCharArray();
        Node current = root;

        for (char bit : encoded) {
            if (bit == '1') {
                current = current.right;
            } else if (bit == '0') {
                current = current.left;
            }
            if (current.left == null && current.right == null) {
                System.out.print(current.data);
                current = root;
            }
        }
    }
}