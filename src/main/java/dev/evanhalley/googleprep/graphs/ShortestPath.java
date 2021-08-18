package dev.evanhalley.googleprep.graphs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ShortestPath {

    public static class Node {
        final int distance;
        final int val;

        public Node(int val, int distance) {
            this.distance = distance;
            this.val = val;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return val == node.val;
        }

        @Override
        public int hashCode() {
            return val;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "distance=" + distance +
                    ", val=" + val +
                    '}';
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][] {{1,2}, {2,3}, {3,4}, {4,6}, {1,5}, {5,6}, {2,5}};
        Map<Integer, Set<Integer>> adjacencyList = toAdjacencyList(6, edges);
        System.out.printf("Shortest Path: %d%n", getShortestPath(adjacencyList, 1, 0));
    }

    public static int getShortestPath(Map<Integer, Set<Integer>> adjacencyList, Integer src, Integer dst) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(src, 0));
        Set<Node> visitedNodes = new HashSet<>();

        while (!queue.isEmpty()) {
            Node node = queue.remove();

            if (visitedNodes.contains(node)) {
                continue;
            }

            if (node.val == dst) {
                return node.distance;
            }
            visitedNodes.add(node);

            for (Integer neighbor : adjacencyList.get(node.val)) {
                queue.add(new Node(neighbor, node.distance + 1));
            }
        }
        return -1;
    }

    public static Map<Integer, Set<Integer>> toAdjacencyList(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

        for (int[] edge : edges) {
            adjacencyList.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        // handle nodes with no edges
        for (int i = 0; i < n; i++) {
            if (!adjacencyList.containsKey(i)) {
                adjacencyList.put(i, new HashSet<>());
            }
        }
        return adjacencyList;
    }


}
