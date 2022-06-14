package me.jdosornio.structures.trees;

import java.util.Stack;

// https://www.hackerrank.com/challenges/self-balancing-tree/problem
public class AVLTree {

    static class Node {
        int val;
        int ht;
        Node left;
        Node right;
    }

    static enum ChildPosition {
        LEFT, RIGHT
    }

    static class VisitedNode {
        Node node;
        ChildPosition pos;

        VisitedNode(Node node, ChildPosition pos) {
            this.node = node;
            this.pos = pos;
        }
    }

    static Node insert(Node root, int val) {
        Stack<VisitedNode> visited = new Stack<>();

        // Asume tree already balanced
        if (root == null) {
            root = new Node();
            root.val = val;
            root.ht = 0;

            return root;
        }
        // Insert new value
        Node curr = root;
        while (curr != null) {
            if (val < curr.val) {
                visited.push(new VisitedNode(curr, ChildPosition.LEFT));
                curr = curr.left;
            } else if (val > curr.val) {
                visited.push(new VisitedNode(curr, ChildPosition.RIGHT));
                curr = curr.right;
            } else {
                // Drop if equal
                return root;
            }
        }
        // Actually insert node
        VisitedNode parent = visited.pop();
        Node child = new Node();

        child.val = val;
        child.ht = 0;

        // Check cases where there's already a child there
        if (parent.pos == ChildPosition.RIGHT) {
            parent.node.right = child;
        } else {
            parent.node.left = child;
        }
        parent.node.ht = getHeight(parent.node);

        Node rotatedNode = null;
        while (visited.size() > 0) {
            parent = visited.pop();
            if (rotatedNode != null) {
                if (parent.pos == ChildPosition.RIGHT) {
                    parent.node.right = rotatedNode;
                } else {
                    parent.node.left = rotatedNode;
                }
                rotatedNode = null;
            }
            parent.node.ht = getHeight(parent.node);
            int factor = getBalanceFactor(parent.node);

            if (factor > 1) {
                if (getBalanceFactor(parent.node.left) == 1) {
                    rotatedNode = rotateRight(parent.node);
                } else {
                    // -1
                    rotatedNode = rotateLeftRight(parent.node);
                }
            } else if (factor < -1) {
                if (getBalanceFactor(parent.node.right) == -1) {
                    rotatedNode = rotateLeft(parent.node);
                } else {
                    // 1
                    rotatedNode = rotateRightLeft(parent.node);
                }
            }
        }
        if (rotatedNode != null) {
            root = rotatedNode;
        }

        return root;
    }

    static Node rotateLeft(Node unbalancedNode) {
        Node rightChild = unbalancedNode.right;
        Node rightLeftChild = rightChild.left;

        rightChild.left = unbalancedNode;
        unbalancedNode.right = rightLeftChild;

        unbalancedNode.ht = getHeight(unbalancedNode);
        rightChild.ht = getHeight(rightChild);

        return rightChild;
    }

    static Node rotateRight(Node unbalancedNode) {
        Node leftChild = unbalancedNode.left;
        Node leftRightChild = leftChild.right;

        leftChild.right = unbalancedNode;
        unbalancedNode.left = leftRightChild;

        unbalancedNode.ht = getHeight(unbalancedNode);
        leftChild.ht = getHeight(leftChild);

        return leftChild;
    }

    static Node rotateLeftRight(Node unbalancedNode) {
        Node leftChild = unbalancedNode.left;
        Node leftRightChild = leftChild.right;
        Node leftRightLeftChild = leftRightChild.left;

        leftRightChild.left = leftChild;
        leftChild.right = leftRightLeftChild;
        unbalancedNode.left = leftRightChild;

        leftChild.ht = getHeight(leftChild);
        leftRightChild.ht = getHeight(leftRightChild);

        return rotateRight(unbalancedNode);
    }

    static Node rotateRightLeft(Node unbalancedNode) {
        Node rightChild = unbalancedNode.right;
        Node rightLeftChild = rightChild.left;
        Node rightLeftRightChild = rightLeftChild.right;

        rightLeftChild.right = rightChild;
        rightChild.left = rightLeftRightChild;
        unbalancedNode.right = rightLeftChild;

        rightChild.ht = getHeight(rightChild);
        rightLeftChild.ht = getHeight(rightLeftChild);

        return rotateLeft(unbalancedNode);
    }

    static int getHeight(Node root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = (root.left != null) ? root.left.ht : -1;
        int rightHeight = (root.right != null) ? root.right.ht : -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    static int getBalanceFactor(Node root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = (root.left != null) ? root.left.ht : -1;
        int rightHeight = (root.right != null) ? root.right.ht : -1;

        return leftHeight - rightHeight;
    }
}