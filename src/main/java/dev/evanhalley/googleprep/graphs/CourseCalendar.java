package dev.evanhalley.googleprep.graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class CourseCalendar {

    public static void main(String[] args) {
        //System.out.println(new Solution().canFinish(2, new int[][]{ {1,0} }));
       //System.out.println(new Solution().canFinish(2, new int[][]{ {0,1} }));
        ///System.out.println(new Solution().canFinish(2, new int[][]{ {0,1}, {1,0} }));
        //System.out.println(new Solution().canFinish(5, new int[][]{ {0,1}, {1,2}, {1,3}, {2,4} }));
        //System.out.println(new Solution().canFinish(5, new int[][]{ {0,1}, {1,2}, {1,0}, {1,3}, {2,4} }));
        //System.out.println(new Solution().canFinish(8, new int[][]{ {0,1}, {1,2}, {1,3}, {2,4} }));
        System.out.println(new Solution().canFinish(5, new int[][]{ {1,4}, {2,4}, {3,1}, {3,2} }));
        //System.out.println(new Solution().canFinish(20, new int[][]{{0,10},{3,18},{5,5},{6,11},{11,14},{13,1},{15,1},{17,4}}));
        //System.out.println(new Solution().canFinish(1, new int[0][0]));

    }

    static class Solution {

        public boolean canFinish(int numCourses, int[][] prerequisites) {

            if (prerequisites.length == 0) {
                return true;
            }

            Map<Integer, Set<Integer>> adjacencyList = toAdjacencyList(numCourses, prerequisites);

            for (Integer course : adjacencyList.keySet()) {
                Set<Integer> visited = new HashSet<>();
                Set<Integer> exploring = new HashSet<>();

                if (!canTakePrerequisites(adjacencyList, visited, exploring, course)) {
                    return false;
                }
            }
            return true;
        }

        public boolean canTakePrerequisites(Map<Integer, Set<Integer>> adjacencyList, Set<Integer> visited,
                                            Set<Integer> exploring, Integer start) {
            Deque<Integer> stack = new ArrayDeque<>();
            stack.push(start);

            while (!stack.isEmpty()) {
                Integer node = stack.pop();

                if (visited.contains(node)) {
                    continue;
                }
                visited.add(node);

                if (adjacencyList.containsKey(node)) {

                    for (Integer neighbor : adjacencyList.get(node)) {

                        if (exploring.contains(neighbor)) {
                            return false;
                        }
                        stack.push(neighbor);
                    }
                }
                exploring.add(node);
            }
            return true;
        }

        public Map<Integer, Set<Integer>> toAdjacencyList(int n, int[][] edges) {
            Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();
            Set<Integer> nodesWithEdges = new HashSet<>();

            for (int[] edge : edges) {
                nodesWithEdges.add(edge[0]);
                nodesWithEdges.add(edge[1]);
                adjacencyList.computeIfAbsent(edge[1], k -> new LinkedHashSet<>()).add(edge[0]);
            }

            // handle nodes with no edges
            for (int i = 0; i < n; i++) {
                if (!nodesWithEdges.contains(i)) {
                    adjacencyList.put(i, new HashSet<>());
                }
            }
            return adjacencyList;
        }
    }

}
