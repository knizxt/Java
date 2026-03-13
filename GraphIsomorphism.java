import java.util.*;

// Basic graph isomorphism check using degree sequence + canonical form heuristic
public class GraphIsomorphism {
    static boolean isomorphic(int[][] g1, int[][] g2, int n) {
        int[] deg1=new int[n], deg2=new int[n];
        for (int i=0;i<n;i++) for (int j=0;j<n;j++) { deg1[i]+=g1[i][j]; deg2[i]+=g2[i][j]; }
        Arrays.sort(deg1); Arrays.sort(deg2);
        if (!Arrays.equals(deg1, deg2)) return false;
        // attempt all permutations (only feasible for small n)
        int[] perm=new int[n]; for (int i=0;i<n;i++) perm[i]=i;
        return tryPerms(g1, g2, perm, 0, n);
    }

    static boolean tryPerms(int[][] g1, int[][] g2, int[] p, int start, int n) {
        if (start==n) { // check mapping
            for (int i=0;i<n;i++) for (int j=0;j<n;j++) if (g1[i][j]!=g2[p[i]][p[j]]) return false;
            return true;
        }
        for (int i=start;i<n;i++) {
            int t=p[start]; p[start]=p[i]; p[i]=t;
            if (tryPerms(g1,g2,p,start+1,n)) return true;
            t=p[start]; p[start]=p[i]; p[i]=t;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] g1={{0,1,0},{1,0,1},{0,1,0}};
        int[][] g2={{0,1,1},{1,0,0},{1,0,0}};
        System.out.println("Isomorphic: " + isomorphic(g1, g2, 3));
    }
}
