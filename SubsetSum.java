import java.util.*;

public class SubsetSum {
    static boolean subsetSum(int[] arr, int n, int sum) {
        boolean[][] dp = new boolean[n + 1][sum + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = true;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= sum; j++) {
                dp[i][j] = dp[i-1][j];
                if (arr[i-1] <= j) dp[i][j] |= dp[i-1][j - arr[i-1]];
            }
        return dp[n][sum];
    }

    // Also print one valid subset if it exists
    static void printSubset(int[] arr, int target) {
        int n = arr.length;
        boolean[][] dp = new boolean[n+1][target+1];
        for (int i = 0; i <= n; i++) dp[i][0] = true;
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i-1][j];
                if (arr[i-1] <= j) dp[i][j] |= dp[i-1][j-arr[i-1]];
            }
        if (!dp[n][target]) { System.out.println("No subset with sum " + target); return; }
        List<Integer> chosen = new ArrayList<>();
        int rem = target;
        for (int i = n; i > 0; i--) {
            if (!dp[i-1][rem]) { chosen.add(arr[i-1]); rem -= arr[i-1]; }
        }
        System.out.println("Subset with sum " + target + ": " + chosen);
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        System.out.println("Has subset with sum 9: " + subsetSum(arr, arr.length, 9));   // true
        System.out.println("Has subset with sum 30: " + subsetSum(arr, arr.length, 30)); // false
        printSubset(arr, 9);
    }
}
