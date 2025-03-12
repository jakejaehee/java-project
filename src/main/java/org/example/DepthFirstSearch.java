package org.example;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Depth First Traversal (or Search) for a graph is similar to Depth First Traversal of a tree
 * The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again.
 * To avoid processing a node more than once, we use a boolean visited array.
 * For example, in the following graph, we start traversal from vertex 2.
 * When we come to vertex 0, we look for all adjacent vertices of it. 2 is also an adjacent vertex of 0.
 * If we don’t mark visited vertices, then 2 will be processed again and it will become a non-terminating process.
 * A Depth First Traversal of the following graph is 2, 0, 1, 3.
 *
 * https://onedrive.live.com/view.aspx?resid=18A61F0DDFB0335C%21s17451c1119764ba98d596e1f55aeade4&id=documents&wd=target%28Career%2FAlgorithms.one%7CA9196CA2-0B6C-8241-94E0-D755A3D5C590%2FDepth%20First%20Search%20-%20jakejaehee%7C45FD1AA1-A3AC-5645-A5B3-A7645CD879B2%2F%29onenote:https://d.docs.live.net/18a61f0ddfb0335c/문서/1_Project/Career/Algorithms.one#Depth%20First%20Search%20-%20jakejaehee&section-id={A9196CA2-0B6C-8241-94E0-D755A3D5C590}&page-id={45FD1AA1-A3AC-5645-A5B3-A7645CD879B2}&end
 */
public class DepthFirstSearch {
    static class Vertex {
        private final int value;
        private LinkedList<Vertex> adjacents = new LinkedList<Vertex>();
        private boolean visited = false;

        public Vertex(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void addAdjacent(Vertex vertex) {
            adjacents.add(vertex);
        }

        public LinkedList<Vertex> getAdjacents() {
            return adjacents;
        }

        public void setVisited() {
            this.visited = true;
        }

        public boolean isVisited() {
            return visited;
        }
    }

    private static Vertex createGraph() {
        Vertex v2 = new Vertex(2);
        Vertex v0 = new Vertex(0);
        Vertex v3 = new Vertex(3);
        Vertex v1 = new Vertex(1);

        v2.addAdjacent(v0);
        v2.addAdjacent(v3);
        v0.addAdjacent(v2);
        v0.addAdjacent(v1);
        v1.addAdjacent(v0);
        v1.addAdjacent(v2);
        v3.addAdjacent(v3);
        v3.addAdjacent(v2);

        return v2;
    }

    public static void traverse1(Vertex vertex) {
        if (vertex.isVisited()) {
            return;
        }

        System.out.println(vertex.getValue());
        vertex.setVisited();

        for (int i = 0; i < vertex.getAdjacents().size(); i++) {
            Vertex adjacent = vertex.getAdjacents().get(i);
            traverse2(adjacent);
        }
    }

    public static void traverse2(Vertex vertex) {
        Stack<Vertex> stack = new Stack<Vertex>();

        System.out.println(vertex.getValue());
        stack.push(vertex);
        vertex.setVisited();

        while (vertex != null) {
            Vertex adjacent = null;
            for (int i = 0; i < vertex.getAdjacents().size(); i++) {
                adjacent = vertex.getAdjacents().get(i);
                if (adjacent.isVisited()) {
                    continue;
                }
                System.out.println(adjacent.getValue());
                stack.push(adjacent);
                adjacent.setVisited();
                break;
            }

            Vertex previousVertex = null;
            while (stack.isEmpty() != true) {
                previousVertex = stack.pop();
                if (previousVertex.getAdjacents().isEmpty() == true) {
                    continue;
                }
                break;
            }

            if (previousVertex == null) {
                break;
            }

            vertex = previousVertex;
        }
    }

    public static void main(String[] args) {
        System.out.println("traverse1():");
        traverse1(createGraph());

        System.out.println("traverse2():");
        traverse2(createGraph());
    }
}

