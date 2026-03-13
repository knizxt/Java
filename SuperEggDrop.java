import java.util.*;
public class SuperEggDrop {
    static int superEggDrop(int k, int n) {
        // dp[m][k] = max floors with m moves and k eggs
        int m=0; int[] dp=new int[k+1];
        while (dp[k]<n) {
            m++;
            for (int i=k;i>=1;i--) dp[i]=dp[i-1]+dp[i]+1;
        }
        return m;
    }
    public static void main(String[] args) {
        System.out.println("Min moves (2 eggs,100 floors): " + superEggDrop(2,100));
        System.out.println("Min moves (3 eggs,14 floors): " + superEggDrop(3,14));
    }
}
