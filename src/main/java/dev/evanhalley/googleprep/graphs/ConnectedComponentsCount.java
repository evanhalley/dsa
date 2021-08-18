package dev.evanhalley.googleprep.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConnectedComponentsCount {

    public static void main(String[] args) {

        int[][] edges = new int[][] { {0,1},{1,2},{3,4} } ;
        new ConnectedComponentsCount().countComponents(3, edges);
    }

    public int countComponents(int n, int[][] edges) {
        Map<Integer, Set<Integer>> adjacencyList = toAdjacencyList(edges);
        HashSet<Integer> visited = new HashSet<>(n);
        int count = 0;

        for (Integer node : adjacencyList.keySet()) {

            if(depthFirstRecursive(adjacencyList, node, visited)) {
                count++;
            }
        }
        return count;
    }

    public Map<Integer, Set<Integer>> toAdjacencyList(int[][] edges) {
        Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

        for (int[] edge : edges) {
            adjacencyList.computeIfAbsent(edge[0], k -> new HashSet<>()).add(edge[1]);
            adjacencyList.computeIfAbsent(edge[1], k -> new HashSet<>()).add(edge[0]);
        }

        return adjacencyList;
    }

    public boolean depthFirstRecursive(Map<Integer, Set<Integer>> adjacencyList, Integer current, Set<Integer> visited) {

        if (visited.contains(current)) {
            return false;
        }
        visited.add(current);

        for (Integer neighbor : adjacencyList.get(current)) {
            depthFirstRecursive(adjacencyList, neighbor, visited);
        }
        return true;
    }
}
