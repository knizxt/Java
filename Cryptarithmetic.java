import java.util.*;
// Solve SEND + MORE = MONEY
public class Cryptarithmetic {
    static char[] letters={'S','E','N','D','M','O','R','Y'};
    static int[] digits=new int[10]; static boolean[] used=new boolean[10];
    static Map<Character,Integer> map=new HashMap<>();

    static int val(String word) { int v=0; for (char c:word.toCharArray()) v=v*10+map.get(c); return v; }

    static boolean solve(int idx) {
        if (idx==letters.length) return val("SEND")+val("MORE")==val("MONEY");
        for (int d=0;d<10;d++) {
            if (used[d]) continue;
            if (d==0&&(letters[idx]=='S'||letters[idx]=='M')) continue;
            map.put(letters[idx],d); used[d]=true;
            if (solve(idx+1)) return true;
            map.remove(letters[idx]); used[d]=false;
        }
        return false;
    }

    public static void main(String[] args) {
        if (solve(0)) System.out.println("SEND="+val("SEND")+", MORE="+val("MORE")+", MONEY="+val("MONEY"));
        else System.out.println("No solution");
    }
}
