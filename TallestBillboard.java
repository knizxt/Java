import java.util.*;
// dp[d] = max sum of shorter side when difference between two sides is d
public class TallestBillboard {
    static int tallestBillboard(int[] rods) {
        Map<Integer,Integer> dp=new HashMap<>(); dp.put(0,0);
        for (int r:rods) {
            Map<Integer,Integer> cur=new HashMap<>(dp);
            for (Map.Entry<Integer,Integer> e:cur.entrySet()) {
                int d=e.getKey(), h=e.getValue();
                dp.merge(d+r, h, Math::max);
                dp.merge(Math.abs(d-r), Math.min(h,h+d-r<0?h:h)+Math.max(0,r-d), Math::max);
            }
        }
        return dp.getOrDefault(0,0);
    }
    public static void main(String[] args) {
        System.out.println("Tallest billboard: " + tallestBillboard(new int[]{1,2,3,6})); // 6
    }
}
