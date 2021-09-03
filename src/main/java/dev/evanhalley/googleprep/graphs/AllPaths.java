package dev.evanhalley.googleprep.graphs;

import dev.evanhalley.googleprep.util.GraphUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AllPaths {

    public static void main(String[] args) {
        int[][] edges = new int[][] {{4,3,1}, {3,2,4}, {3}, {4}, {}};
        //int[][] edges = new int[][] {{1}, {}};
        //int[][] edges = new int[][] {{1,3}, {2}, {3}, {}};
        Map<Integer, Set<Integer>> adjList = GraphUtil.toDirectedGraph(edges);
        List<List<Integer>> paths = new ArrayList<>();
        allPaths(adjList, edges.length - 1, Collections.singletonList(0), paths);
        System.out.println(paths);
    }

    public static void allPaths(Map<Integer, Set<Integer>> adjList, int dest, List<Integer> currentPath,
                                   List<List<Integer>> paths) {
        int v = currentPath.get(currentPath.size() - 1);

        if (v == dest) {
            paths.add(currentPath);
            return;
        }

        if (!adjList.containsKey(v)) return;

        for (Integer neighbor : adjList.get(v)) {
            List<Integer> nextPath = new ArrayList<>(currentPath);
            nextPath.add(neighbor);
            allPaths(adjList, dest, nextPath, paths);
        }
    }
}
