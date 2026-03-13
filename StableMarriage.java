import java.util.*;

// Gale-Shapley algorithm
public class StableMarriage {
    public static int[] stableMatch(int[][] mPref, int[][] wPref, int n) {
        int[] wPartner = new int[n], mNext = new int[n];
        Arrays.fill(wPartner, -1);
        int free = n;
        // precompute women's pref rank
        int[][] rank = new int[n][n];
        for (int w=0;w<n;w++) for (int i=0;i<n;i++) rank[w][wPref[w][i]] = i;
        Queue<Integer> q = new LinkedList<>();
        for (int i=0;i<n;i++) q.add(i);
        while (!q.isEmpty()) {
            int m = q.poll();
            int w = mPref[m][mNext[m]++];
            if (wPartner[w] == -1) { wPartner[w] = m; }
            else {
                int m2 = wPartner[w];
                if (rank[w][m] < rank[w][m2]) { wPartner[w] = m; q.add(m2); }
                else q.add(m);
            }
        }
        return wPartner;
    }

    public static void main(String[] args) {
        int[][] mPref = {{1,0,2},{0,1,2},{0,1,2}};
        int[][] wPref = {{0,1,2},{0,1,2},{0,1,2}};
        int[] match = stableMatch(mPref, wPref, 3);
        for (int w=0;w<3;w++) System.out.println("Woman "+w+" matched with man "+match[w]);
    }
}
