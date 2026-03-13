import java.util.*;
// classic 0/1 knapsack - take it or leave it
public class Knapsack01 {
    public static void main(String[] args) {
        int[] wt = {1, 3, 4, 5}, val = {1, 4, 5, 7};
        int W = 7, n = wt.length;
        int[][] dp = new int[n+1][W+1];
        for (int i=1;i<=n;i++)
            for (int w=0;w<=W;w++) {
                dp[i][w] = dp[i-1][w];
                if (wt[i-1] <= w) dp[i][w] = Math.max(dp[i][w], dp[i-1][w-wt[i-1]] + val[i-1]);
            }
        System.out.println("Max value: " + dp[n][W]);
    }
}
