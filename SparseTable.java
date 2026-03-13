import java.util.*;

// Sparse Table for O(1) Range Minimum Query after O(N log N) preprocessing
public class SparseTable {
    int[][] table;
    int[] log2;
    int n;

    SparseTable(int[] arr) {
        n = arr.length;
        int LOG = 1; while ((1<<LOG) <= n) LOG++;
        table = new int[n][LOG];
        log2 = new int[n+1];
        for (int i=2;i<=n;i++) log2[i] = log2[i/2]+1;
        for (int i=0;i<n;i++) table[i][0] = arr[i];
        for (int j=1;(1<<j)<=n;j++)
            for (int i=0;i+(1<<j)-1<n;i++)
                table[i][j] = Math.min(table[i][j-1], table[i+(1<<(j-1))][j-1]);
    }

    int query(int l, int r) {
        int k = log2[r-l+1];
        return Math.min(table[l][k], table[r-(1<<k)+1][k]);
    }

    public static void main(String[] args) {
        int[] arr = {2,4,3,1,6,7,8,9,1,7};
        SparseTable st = new SparseTable(arr);
        System.out.println("RMQ [2,7]: " + st.query(2,7));   // 1
        System.out.println("RMQ [0,4]: " + st.query(0,4));   // 1
        System.out.println("RMQ [5,9]: " + st.query(5,9));   // 1
    }
}
