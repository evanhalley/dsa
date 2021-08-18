package dev.evanhalley.googleprep.graphs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Traversals {

    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<>();
        graph.put("a", new String[]{ "b", "c" });
        graph.put("b", new String[]{ "d" });
        graph.put("c", new String[]{ "e" });
        graph.put("d", new String[]{ "f" });
        graph.put("e", new String[0]);
        graph.put("f", new String[0]);

        System.out.println("DF: " + depthFirst(graph, "a"));
        System.out.println();
        System.out.print("DF (recursive): ");
        depthFirstRecursive(graph, "a");
        System.out.println(); System.out.println();
        System.out.println("BF: " + breadthFirst(graph, "a"));

        Map<String, String[]> graph2 = new HashMap<>();
        graph2.put("f", new String[]{ "g", "i" });
        graph2.put("g", new String[]{ "h" });
        graph2.put("h", new String[0]);
        graph2.put("i", new String[]{ "g", "k", "f" });
        graph2.put("j", new String[]{ "i" });
        graph2.put("k", new String[0]);
        System.out.println(hasPathBreadthFirst(graph2, "f", "k"));
    }

    public static void depthFirstRecursive(Map<String, String[]> graph, String start) {
        System.out.print(start);

        for (String neighbor : graph.get(start)) {
            System.out.print(" -> ");
            depthFirstRecursive(graph, neighbor);
        }
    }

    public static String depthFirst(Map<String, String[]> graph, String start) {
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            String node = stack.pop();
            sb.append(node);
            String[] neighbors = graph.get(node);

            for (String neighbor : neighbors) {
                stack.push(neighbor);
            }

            if (!stack.isEmpty()) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    public static String breadthFirst(Map<String, String[]> graph, String start) {
        StringBuilder sb = new StringBuilder();
        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            String node = queue.remove();
            sb.append(node);
            String[] neighbors = graph.get(node);

            for (String neighbor : neighbors) {
                queue.add(neighbor);
            }

            if (!queue.isEmpty()) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    public static boolean hasPathBreadthFirst(Map<String, String[]> graph, String start, String dst) {
        Queue<String> queue = new LinkedList<>();
        queue.add(start);
        Set<String> nodesVisited = new HashSet<>();

        while (!queue.isEmpty()) {
            String node = queue.remove();
            nodesVisited.add(node);

            if (node.equals(dst)) {
                return true;
            }
            String[] neighbors = graph.get(node);

            for (String neighbor : neighbors) {

                if (!nodesVisited.contains(neighbor)) {
                    queue.add(neighbor);
                }
            }
        }
        return false;
    }

}
