package dev.evanhalley.googleprep.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCostConnectPoints {

    public static int minCostConnectPoints(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int size = points.length;
        // our priority queue puts the min cost edges at the top
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x.cost));
        UnionFind uf = new UnionFind(size);

        // add each edge to our PQ
        for (int i = 0; i < size; i++) {

            for (int j = i + 1; j < size; j++) {
                int[] coordinate1 = points[i];
                int[] coordinate2 = points[j];
                int cost = Math.abs(coordinate1[0] - coordinate2[0]) +
                        Math.abs(coordinate1[1] - coordinate2[1]);
                Edge edge = new Edge(i, j, cost);
                pq.add(edge);
            }
        }

        int result = 0;
        int count = size - 1;

        while (!pq.isEmpty() && count > 0) {
            Edge e = pq.poll();

            // make sue they don't for a cycle
            if (!uf.connected(e.point1, e.point2)) {
                uf.union(e.point1, e.point2);
                result += e.cost;
                count--;
            }
        }
        return result;
    }

    static class Edge {
        int point1;
        int point2;
        int cost;

        Edge(int point1, int point2, int cost) {
            this.point1 = point1;
            this.point2 = point2;
            this.cost = cost;
        }
    }
}
