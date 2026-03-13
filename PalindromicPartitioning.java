import java.util.*;
public class PalindromicPartitioning {
    static boolean isPalin(String s, int i, int j) {
        while (i<j) if (s.charAt(i++)!=s.charAt(j--)) return false; return true;
    }
    static int minCuts(String s) {
        int n=s.length();
        int[] dp=new int[n]; Arrays.fill(dp,Integer.MAX_VALUE);
        for (int i=0;i<n;i++) {
            if (isPalin(s,0,i)) { dp[i]=0; continue; }
            for (int j=1;j<=i;j++) if (isPalin(s,j,i)) dp[i]=Math.min(dp[i],dp[j-1]+1);
        }
        return dp[n-1];
    }
    public static void main(String[] args) {
        System.out.println("Min cuts for 'aab': " + minCuts("aab"));
        System.out.println("Min cuts for 'abcba': " + minCuts("abcba"));
    }
}
