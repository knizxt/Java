import java.util.*;
// Interval DP
public class MinimumCostToCutStick {
    static int minCost(int n, int[] cuts) {
        List<Integer> c=new ArrayList<>();
        c.add(0); for (int x:cuts) c.add(x); c.add(n);
        Collections.sort(c);
        int m=c.size();
        int[][] dp=new int[m][m];
        for (int len=2;len<m;len++) for (int i=0;i+len<m;i++) {
            int j=i+len; dp[i][j]=Integer.MAX_VALUE;
            for (int k=i+1;k<j;k++)
                dp[i][j]=Math.min(dp[i][j], dp[i][k]+dp[k][j]+c.get(j)-c.get(i));
        }
        return dp[0][m-1];
    }
    public static void main(String[] args) {
        System.out.println("Min cost: " + minCost(7, new int[]{1,3,4,5})); // 16
    }
}
