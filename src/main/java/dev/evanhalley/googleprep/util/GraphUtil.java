package dev.evanhalley.googleprep.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GraphUtil {

    public static Map<Integer, Set<Integer>> toUndirectedAdjacencyList(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

        for (int[] edge : edges) {
            adjacencyList.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        // handle nodes with no outgoing edges
        for (int i = 0; i < n; i++) {
            if (!adjacencyList.containsKey(i)) {
                adjacencyList.put(i, new HashSet<>());
            }
        }
        return adjacencyList;
    }

    public static Map<Integer, Set<Integer>> toDirectedAdjacencyList(int[][] edges) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];

            for (int e : edge) {
                adjacencyList.computeIfAbsent(i, k -> new HashSet<>()).add(e);
            }
        }
        return adjacencyList;
    }
}
