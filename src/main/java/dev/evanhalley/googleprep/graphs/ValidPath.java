package dev.evanhalley.googleprep.graphs;

import dev.evanhalley.googleprep.util.GraphUtil;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class ValidPath {

    public static void main(String[] args) {
        System.out.println(validPath(6, new int[][] {{0,1},{0,2},{1,5},{5,4},{4,3}}, 0, 5));
    }

    public static boolean validPath(int n, int[][] edges, int start, int end) {
        Map<Integer, Set<Integer>> adjList = GraphUtil.toUndirectedAdjacencyList(n, edges);
        Deque<Integer> stack = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            Integer v = stack.pop();

            if (v == end) {
                return true;
            }

            if (visited.contains(v)) {
                continue;
            }
            visited.add(v);

            for (Integer neighbor : adjList.get(v)) {
                stack.push(neighbor);
            }
        }
        return false;
    }
}
