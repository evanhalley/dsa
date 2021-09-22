package dev.evanhalley.googleprep.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstrasAlgorithm {

    public static int INFINITY = Integer.MAX_VALUE;

    public static void main(String[] args) {

        Graph graph = new Graph(Arrays.asList(
                new Edge(1, 5, 100),
                new Edge(1, 2, 1),
                new Edge(2, 3, 3),
                new Edge(2, 5, 1),
                new Edge(3, 4, 20),
                new Edge(4, 6, 4),
                new Edge(5, 6, 1)
        ), 6);

        Map<Integer, Integer> distances = dijkstra(graph, 1);
    }

    public static Map<Integer, Integer> dijkstra(Graph graph, int source) {
        Map<Integer, Integer> distance = new HashMap<>();

        for (int i = 1; i <= graph.adjList.size(); i++) distance.put(i, INFINITY);

        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> route = new HashMap<>();
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt((node) -> node.weight));

        queue.add(new Node(source, 0));
        distance.put(source, 0);
        visited.add(source);

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            for (Edge edge : graph.adjList.get(node.vertex)) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;

                // we found a cheaper route to get to v
                if (!visited.contains(v) && (distance.get(u) + weight  < distance.get(v))) {
                    distance.put(v, distance.get(u) + weight);
                    visited.add(u);
                    queue.add(new Node(v, distance.get(v)));
                    route.put(v, u);
                }
            }
        }
        return distance;
    }

    public static class Graph {
        final Map<Integer, List<Edge>> adjList = new HashMap<>();

        public Graph(List<Edge> edges, int numNodes) {

            for (int i = 1; i <= numNodes; i++) {
                adjList.put(i, new ArrayList<>());
            }

            for (Edge edge : edges) {
                adjList.get(edge.source).add(edge);
            }
        }
    }

    public static class Node {
        private final int vertex;
        private final int weight;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public static class Edge {
        private final int source;
        private final int destination;
        private final int weight;

        public Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return String.format("Edge(%d): %d->%d", weight, source, destination);
        }
    }
}
