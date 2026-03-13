import java.util.*;
// O(N log N) patience sorting approach
public class LIS {
    static int lisLength(int[] arr) {
        List<Integer> tails = new ArrayList<>();
        for (int x : arr) {
            int lo=0, hi=tails.size();
            while (lo<hi) { int mid=(lo+hi)/2; if (tails.get(mid)<x) lo=mid+1; else hi=mid; }
            if (lo==tails.size()) tails.add(x); else tails.set(lo, x);
        }
        return tails.size();
    }
    public static void main(String[] args) {
        int[] arr = {10,9,2,5,3,7,101,18};
        System.out.println("LIS length: " + lisLength(arr));
    }
}
