import java.util.*;
public class UnboundedKnapsack {
    public static void main(String[] args) {
        int[] wt = {1, 3, 4, 5}, val = {10, 40, 50, 70};
        int W = 8;
        int[] dp = new int[W+1];
        for (int w=1;w<=W;w++)
            for (int i=0;i<wt.length;i++)
                if (wt[i] <= w) dp[w] = Math.max(dp[w], dp[w-wt[i]] + val[i]);
        System.out.println("Max value (unbounded): " + dp[W]);
    }
}
