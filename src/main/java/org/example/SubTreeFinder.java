package org.example;

import java.util.LinkedList;

public class SubTreeFinder {
    static class Node {
        public String value;
        public LinkedList<Node> childs = new LinkedList<Node>();

        public Node(String value) {
            this.value = value;
        }

        public void addChild(Node child) {
            this.childs.add(child);
        }
    }

    public static boolean isSubTree(Node big, Node small) {
        if (big == null || small == null) {
            return false;
        } else if (!big.value.equals(small.value)) {
            return false;
        } else if (big.childs.size() < small.childs.size()) {
            return false;
        }

        System.out.println(big.value + " == " + small.value);

        for (int i = 0; i < small.childs.size(); i++) {
            Node b = big.childs.get(i);
            Node s = small.childs.get(i);
            if (!isSubTree(b, s)) {
                return false;
            }
        }

        System.out.println("Found sub-tree: start=" + big.value);
        return true;
    }

    public static boolean findSubTree(Node big, Node small) {
        if (big == null || small == null) {
            return false;
        }

        if (isSubTree(big, small)) {
            return true;
        }

        for (int i = 0; i < big.childs.size(); i++) {
            Node b = big.childs.get(i);
            if (findSubTree(b, small)) {
                return true;
            }
        }
        return false;
    }

    public static Node createBigTree() {
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");
        Node n4 = new Node("4");
        Node n5 = new Node("5");
        Node n6 = new Node("6");
        Node n7 = new Node("7");
        Node n8 = new Node("8");

        n1.addChild(n2);
        n1.addChild(n3);

        n2.addChild(n4);
        n2.addChild(n5);

        n3.addChild(n6);
        n3.addChild(n7);

        n5.addChild(n8);

        return n1;
    }

    public static Node createSmallTree() {
        Node n1 = new Node("1");
        Node n2 = new Node("2");
        Node n3 = new Node("3");

        n1.addChild(n2);
        n1.addChild(n3);

        return n1;
    }

    public static void main(String[] args) {
        Node big = createBigTree();
        Node small = createSmallTree();

        System.out.println(findSubTree(big, small));
    }
}
