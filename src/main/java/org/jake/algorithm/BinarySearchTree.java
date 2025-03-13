package org.jake.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Case 1)
 *         50
 *        /  \
 *      25    75
 *     /  \     \
 *    15   30    85
 *
 * inOrderTraverse: 15 25 30 50 75 85
 * preorderTraverse: 50 25 15 30 75 85
 * postorderTraverse: 15 30 25 85 75 50
 *
 * Case 2)
 * 8 3 2 9 -8 10 8 0 6
 *
 *          8
 *        /   \
 *      3       9
 *     /  \    /  \
 *    2    6  8    10
 *   /
 *  -8
 *    \
 *     0
 *
 * inOrderTraverse: -8 0 2 3 6 8 8 9 10
 * preorderTraverse: 8 3 2 -8 0 6 9 8 10
 * postorderTraverse: 0 -8 2 6 3 8 10 9 8
 * Vertical Order:
 * -8
 * 2 0
 * 3
 * 8 6 8
 * 9
 * 10
 *
 * Horizental Order:
 * 8
 * 3 9
 * 2 6 8 10
 * -8
 * 0
 *
 * Found: 8
 * Found: 6
 * Not found: 7
 * Found: 0
 * Found: -8
 * Not found: -6
 */
public class BinarySearchTree {
    private Node root = null;

    class Node {
        public Node(int value) {
            this.value = value;
        }
        public final int value;
        public Node left = null;
        public Node right = null;
    }

    public void insertBulk(String numbers) {
        if (numbers == null || "".equals(numbers)) return;

        String[] strArray = numbers.split(" ");
        for (int i = 0; i < strArray.length; i++) {
            insert(Integer.parseInt(strArray[i]));
        }
    }

    public void insert(int value) {
        if (root == null) {
            root = new Node(value);
        } else {
            insertTo(root, value);
        }
    }

    private void insertTo(Node node, int value) {
        if (node.value > value) {
            if (node.left != null) {
                insertTo(node.left, value);
            } else {
                node.left = new Node(value);
            }
        } else {
            if (node.right != null) {
                insertTo(node.right, value);
            } else {
                node.right = new Node(value);
            }
        }
    }

    public void inOrderTraverse() {
        inOrderTraverseFrom(root);
        System.out.println();
    }

    private void inOrderTraverseFrom(Node node) {
        if (node == null) return;
        inOrderTraverseFrom(node.left);
        System.out.print(node.value + " ");
        inOrderTraverseFrom(node.right);
    }
    public void preorderTraverse() {
        preorderTraverseFrom(root);
        System.out.println();
    }
    private void preorderTraverseFrom(Node node) {
        if (node == null) return;
        System.out.print(node.value + " ");
        preorderTraverseFrom(node.left);
        preorderTraverseFrom(node.right);
    }
    public void postorderTraverse() {
        postorderTraverseFrom(root);
        System.out.println();
    }
    private void postorderTraverseFrom(Node node) {
        if (node == null) return;
        postorderTraverseFrom(node.left);
        postorderTraverseFrom(node.right);
        System.out.print(node.value + " ");
    }
    private int minHD = 0;
    private int maxHD = 0;
    public void verticalOrderTraverse() {
        if (root == null) return;
        minHD = 0;
        maxHD = 0;
        HashMap<Integer, List<Node>> hdMap = new HashMap<Integer, List<Node>>();
        markHD(root, hdMap, 0);
        for (int i = minHD; i <= maxHD; i++) {
            List<Node> list = hdMap.get(i);
            if (list != null && list.size() > 0) {
                for (Node node : list) {
                    System.out.print(node.value + " ");
                }
                System.out.println();
            }
        }
    }
    private void markHD(Node node, HashMap<Integer, List<Node>> hdMap, int hd) {
        if (node == null) return;
        List<Node> list = hdMap.get(hd);
        if (list == null) {
            list = new LinkedList<Node>();
            hdMap.put(hd, list);
        }
        list.add(node);
        if (minHD > hd) {
            minHD = hd;
        } else if (maxHD < hd) {
            maxHD = hd;
        }
        markHD(node.left, hdMap, hd - 1);
        markHD(node.right, hdMap, hd + 1);
    }
    int maxDepth = 0;
    public void horizentalOrderTraverse() {
        if (root == null) return;
        maxDepth = 0;
        HashMap<Integer, List<Node>> depthMap = new HashMap<Integer, List<Node>>();
        markDepth(root, depthMap, 0);
        for (int i = 0; i <= maxDepth; i++) {
            List<Node> list = depthMap.get(i);
            if (list != null && list.size() > 0) {
                for (Node node : list) {
                    System.out.print(node.value + " ");
                }
                System.out.println();
            }
        }
    }
    private void markDepth(Node node, HashMap<Integer, List<Node>> depthMap, int depth) {
        if (node == null) return;
        List<Node> list = depthMap.get(depth);
        if (list == null) {
            list = new LinkedList<Node>();
            depthMap.put(depth, list);
        }
        list.add(node);
        if (maxDepth < depth) {
            maxDepth = depth;
        }
        markDepth(node.left, depthMap, depth + 1);
        markDepth(node.right, depthMap, depth + 1);
    }
    public void search(int value) {
        Node curr = root;
        if (root != null) {
            while (curr != null && curr.value != value) {
                if (curr.value > value) {
                    curr = curr.left;
                } else {
                    curr = curr.right;
                }
            }
        }
        if (curr != null) {
            System.out.println("Found: " + value);
        } else {
            System.out.println("Not found: " + value);
        }
    }
    public void remove(int value) {
        Node parent = null;
        Node curr = root;
        boolean isLeft = true;
        while (curr != null && curr.value != value) {
            if (curr.value > value) {
                parent = curr;
                isLeft = true;
                curr = curr.left;
            } else if (curr.value < value) {
                parent = curr;
                isLeft = false;
                curr = curr.right;
            }
        }
        if (curr != null) {
            if (curr.left == null && curr.right == null) {
                if (curr == root) {
                    root = null;
                } else {
                    if (isLeft) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                }
            } else if (curr.right == null) {
                if (curr == root) {
                    root = curr.left;
                } else {
                    if (isLeft) {
                        parent.left = curr.left;
                    } else {
                        parent.right = curr.left;
                    }
                }
            } else if (curr.left == null) {
                if (curr == root) {
                    root = curr.right;
                } else {
                    if (isLeft) {
                        parent.left = curr.right;
                    } else {
                        parent.right = curr.right;
                    }
                }
            } else {
                Node replacement = getReplacement(curr.right);
                if (curr == root) {
                    root = replacement;
                } else {
                    if (isLeft) {
                        parent.left = replacement;
                    } else {
                        parent.right = replacement;
                    }
                }
                replacement.left = curr.left;
            }
        }
    }
    private Node getReplacement(Node node) {
        Node parent = null;
        Node ret = node;
        while (ret.left != null) {
            parent = ret;
            ret = ret.left;
        }
        if (parent != null) {
            parent.left = ret.right;
            ret.right = node;
        }
        return ret;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insertBulk("8 3 2 9 -8 10 8 0 6");
        //bst.insertBulk("8 3 2 9 10 0 6");

        System.out.println("\nIn Order: ");
        bst.inOrderTraverse();

        System.out.println("\nPreorder: ");
        bst.preorderTraverse();

        System.out.println("\nPostorder: ");
        bst.postorderTraverse();

        System.out.println("\nVertical Order: ");
        bst.verticalOrderTraverse();

        System.out.println("\nHorizental Order: ");
        bst.horizentalOrderTraverse();

        System.out.println("\nSearch: ");
        bst.search(8);
        bst.search(6);
        bst.search(7);
        bst.search(0);
        bst.search(-8);
        bst.search(-6);
    }
}

