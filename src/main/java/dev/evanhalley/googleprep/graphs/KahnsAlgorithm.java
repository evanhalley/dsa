package dev.evanhalley.googleprep.graphs;

import dev.evanhalley.googleprep.util.GraphUtil;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KahnsAlgorithm {

    public static void main(String[] args) {
        //int[][] edges = new int[][] {{2,3,6}, {4}, {6}, {1,4}, {5,8}, {}, {11,7}, {12}, {}, {2,10}, {6}, {12}, {8}, {}};

        int[][] edges = new int[][] {{1,2}, {2}, {3}, {}};

        System.out.println(KahnsAlgorithm.topologicalSort(edges));
    }

    public static List<Integer> topologicalSort(int[][] edges) {
        List<Integer> result = new ArrayList<>(edges.length);

        // 1) build adjacency list
        Map<Integer, Set<Integer>> graph = GraphUtil.toDirectedGraph(edges);

        // 2) calculate the in-degrees (how many nodes point to a given node)
        Map<Integer, Integer> inDegree = GraphUtil.calculateInDegree(edges);

        // 3) create queue to store work
        Deque<Integer> queue = new LinkedList<>();

        // 4) prime queue with zero degree nodes
        addEligibleNodesToQueue(inDegree, queue);

        // 5) BFS
        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            // remove the node from the graph, get the nodes this node points to
            Set<Integer> children = graph.remove(node);

            for (Integer child : children) {
                // decrease the indegree of the children by one
                inDegree.put(child, inDegree.get(child) - 1);
            }

            // add the node to our list storing our topological dort
            result.add(node);

            // add zero degree nodes to the queue
            addEligibleNodesToQueue(inDegree, queue);
        }
        return result;
    }

    public static void addEligibleNodesToQueue(Map<Integer, Integer> incomingDegree, Deque<Integer> queue) {
        for (Map.Entry<Integer, Integer> entry : incomingDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.offer(entry.getKey());
                incomingDegree.put(entry.getKey(), -1);
            }
        }
    }
}
