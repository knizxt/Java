import java.util.*;
// dp[t][e] = max floors testable with t trials and e eggs
public class EggDropping {
    static int minTrials(int n, int k) {
        // binary search / DP approach
        int t=0; long trials=0;
        while (trials < n) {
            t++;
            long cur=0, c=1;
            for (int i=1;i<=Math.min(t,k);i++) { c=c*(t-i+1)/i; cur+=c; if (cur>=n) break; }
            trials=cur;
        }
        return t;
    }
    public static void main(String[] args) {
        System.out.println("Min trials for 100 floors, 2 eggs: " + minTrials(100, 2));
        System.out.println("Min trials for 36 floors, 2 eggs: " + minTrials(36, 2));
    }
}
