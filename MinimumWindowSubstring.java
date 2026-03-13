import java.util.*;
public class MinimumWindowSubstring {
    static String minWindow(String s, String t) {
        Map<Character,Integer> need=new HashMap<>(),window=new HashMap<>();
        for (char c:t.toCharArray()) need.merge(c,1,Integer::sum);
        int left=0,minL=Integer.MAX_VALUE,minS=0,valid=0;
        for (int right=0;right<s.length();right++) {
            char c=s.charAt(right); window.merge(c,1,Integer::sum);
            if (need.containsKey(c)&&window.get(c).equals(need.get(c))) valid++;
            while (valid==need.size()) {
                if (right-left+1<minL) { minL=right-left+1; minS=left; }
                char d=s.charAt(left); window.merge(d,-1,Integer::sum);
                if (need.containsKey(d)&&window.get(d)<need.get(d)) valid--;
                left++;
            }
        }
        return minL==Integer.MAX_VALUE?"":s.substring(minS,minS+minL);
    }
    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC","ABC")); // "BANC"
    }
}
