import java.util.*;

public class Kruskal {
    static int[] parent, rank;

    static int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        int pa = find(a), pb = find(b);
        if (pa == pb) return false;
        if (rank[pa] < rank[pb]) { int t = pa; pa = pb; pb = t; }
        parent[pb] = pa;
        if (rank[pa] == rank[pb]) rank[pa]++;
        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{0,1,10},{0,2,6},{0,3,5},{1,3,15},{2,3,4}};
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        parent = new int[n]; rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
        int total = 0;
        System.out.println("MST edges:");
        for (int[] e : edges) {
            if (union(e[0], e[1])) {
                System.out.println(e[0] + " - " + e[1] + " : " + e[2]);
                total += e[2];
            }
        }
        System.out.println("Total weight: " + total);
    }
}
