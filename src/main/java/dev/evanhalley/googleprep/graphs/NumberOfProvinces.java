package dev.evanhalley.googleprep.graphs;

import java.util.HashSet;
import java.util.Set;

public class NumberOfProvinces {

    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][] {{1,1,0} , {1,1,0} , {0,0,1}}));
        System.out.println(findCircleNum(new int[][] {{1,0,0} , {0,1,0} , {0,0,1}}));
    }

    public static int findCircleNum(int[][] isConnected) {
        UnionFind unionFind = buildDisjointSet(isConnected);
        Set<Integer> uniqueRootNodes = new HashSet<>();

        for (int i = 0; i < isConnected.length; i++) {
            uniqueRootNodes.add(unionFind.find(i));
        }

        return uniqueRootNodes.size();
    }

    public static UnionFind buildDisjointSet(int[][] isConnected) {
        UnionFind unionFind = new UnionFind(isConnected.length);

        for (int i = 0; i < isConnected.length; i++) {

            for (int j = 0; j < isConnected[0].length; j++) {

                if (i != j && isConnected[i][j] == 1) {
                    unionFind.union(i, j);
                }
            }
        }
        return unionFind;
    }

}
