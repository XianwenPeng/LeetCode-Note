package LeetCode.Topics;

import java.util.*;
public class UnionFind {

    /* 261. Graph Valid Tree */
    public boolean validTree(int n, int[][] edges) {
        HashMap<Integer, Integer> graph = new HashMap<>();
        for (int[] edge : edges) {
            int son = find(graph, edge[0]);
            int parent = find(graph, edge[1]);
            if (son == parent)  return false;
            graph.put(son, parent);
        }
        return edges.length == n - 1;
    }
    public int find(HashMap<Integer, Integer> graph, int n) {
        if (!graph.containsKey(n))  graph.put(n, n);
        return graph.get(n) != n ? find(graph, graph.get(n)) : n;
    }
}
