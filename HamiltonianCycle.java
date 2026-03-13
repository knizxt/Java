import java.util.*;

public class HamiltonianCycle {
    static int N = 5;
    static int[][] graph = {{0,1,0,1,0},{1,0,1,1,1},{0,1,0,0,1},{1,1,0,0,1},{0,1,1,1,0}};
    static int[] path = new int[N];

    static boolean isSafe(int v, int pos) {
        if (graph[path[pos-1]][v] == 0) return false;
        for (int i=0;i<pos;i++) if (path[i]==v) return false;
        return true;
    }

    static boolean solve(int pos) {
        if (pos == N) return graph[path[pos-1]][path[0]] == 1;
        for (int v=1;v<N;v++) {
            if (isSafe(v, pos)) {
                path[pos] = v;
                if (solve(pos+1)) return true;
                path[pos] = -1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Arrays.fill(path, -1); path[0] = 0;
        if (solve(1)) {
            System.out.print("Hamiltonian cycle: ");
            for (int v : path) System.out.print(v + " ");
            System.out.println(path[0]);
        } else System.out.println("No Hamiltonian cycle found.");
    }
}
