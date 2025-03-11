package org.example;

import java.util.*;

/**
 * Breadth First Traversal for a Graph
 *
 * Breadth First Traversal (or Search) for a graph is similar to Breadth First Traversal of a tree (See method 2 of this post).
 * The only catch here is, unlike trees, graphs may contain cycles, so we may come to the same node again.
 * To avoid processing a node more than once, we use a boolean visited array. For simplicity, it is assumed that all vertices
 * are reachable from the starting vertex.
 * For example, in the following graph, we start traversal from vertex 2. When we come to vertex 0, we look for all adjacent
 * vertices of it. 2 is also an adjacent vertex of 0. If we don’t mark visited vertices, then 2 will be processed again and
 * it will become a non-terminating process. A Breadth First Traversal of the following graph is 2, 0, 3, 1.
 0 --- 1
 |  /
 2 --- 3 < (recursive 3)
 */
public class BreadthFirstSearch {
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

    public static void traverse1(LinkedList<Vertex> adjacents) {
        LinkedList<Vertex> queue = new LinkedList<Vertex>();
        while (adjacents.isEmpty() != true) {
            Vertex adjacent = adjacents.pop();
            if (adjacent.isVisited()) {
                continue;
            }
            System.out.println(adjacent.getValue());
            adjacent.setVisited();
            if (adjacent.getAdjacents().isEmpty() != true) {
                queue.addAll(adjacent.getAdjacents());
            }
        }
        if (queue.isEmpty() != true) {
            traverse1(queue);
        }
    }

    public static void traverse2(Vertex vertex) {
        LinkedList<Vertex> schedule = new LinkedList<Vertex>();
        schedule.add(vertex);
        vertex.setVisited();
        work(schedule);
    }
    private static void work(LinkedList<Vertex> schedule) {
        while (!schedule.isEmpty()) {
            Vertex deqVertex = schedule.poll();
            System.out.println(deqVertex.value);

            for (Vertex a : deqVertex.adjacents) {
                if (!a.isVisited()) {
                    schedule.add(a);
                    a.setVisited();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("traverse1():");
        LinkedList<Vertex> adjacents = new LinkedList<Vertex>();
        adjacents.add(createGraph());
        traverse1(adjacents);

        System.out.println("traverse2():");
        traverse2(createGraph());
    }
}

