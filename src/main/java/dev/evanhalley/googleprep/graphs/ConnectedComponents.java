package dev.evanhalley.googleprep.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConnectedComponents {

    public static void main(String[] args) {

        Map<Integer, Integer[]> graph = new HashMap<>();
        graph.put(0, new Integer[] {8, 1, 5});
        graph.put(1, new Integer[] {0});
        graph.put(5, new Integer[] {0, 8});
        graph.put(8, new Integer[] {0, 5});
        graph.put(2, new Integer[] {3, 4});
        graph.put(3, new Integer[] {2, 4});
        graph.put(4, new Integer[] {3, 2});
        graph.put(7, new Integer[0]);
        int count = 0;

        Set<Integer> visited = new HashSet<>();

        for (Integer node : graph.keySet()) {

            if (depthFirstRecursive(graph, node, visited)) {
                count++;
            }
        }
        System.out.println((count));
    }

    public static boolean depthFirstRecursive(Map<Integer, Integer[]> graph, Integer current, Set<Integer> visited) {

        if (visited.contains(current)) {
            return false;
        }
        visited.add(current);

        for (Integer neighbor : graph.get(current)) {
            depthFirstRecursive(graph, neighbor, visited);
        }
        return true;
    }

}
