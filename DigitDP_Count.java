import java.util.*;
// Count numbers in [1,N] with digit sum divisible by 3
public class DigitDP_Count {
    static String num;
    static int[][][] memo;

    static long dp(int pos, int rem, boolean tight) {
        if (pos==num.length()) return rem==0 ? 1 : 0;
        if (memo[pos][rem][tight?1:0]!=-1) return memo[pos][rem][tight?1:0];
        int limit = tight ? (num.charAt(pos)-'0') : 9;
        long res=0;
        for (int d=0;d<=limit;d++)
            res += dp(pos+1, (rem+d)%3, tight && d==limit);
        return memo[pos][rem][tight?1:0] = (int)res;
    }

    public static void main(String[] args) {
        num = "100";
        memo = new int[num.length()][3][2];
        for (int[][] a:memo) for (int[] b:a) Arrays.fill(b,-1);
        System.out.println("Count in [1,100] with digit sum divisible by 3: " + (dp(0,0,true)-1));
    }
}
