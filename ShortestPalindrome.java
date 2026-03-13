import java.util.*;
public class ShortestPalindrome {
    // Use KMP on s + "#" + reverse(s) to find longest palindrome from start
    static String shortestPalindrome(String s) {
        String rev=new StringBuilder(s).reverse().toString();
        String concat=s+"#"+rev;
        int[] kmp=new int[concat.length()];
        for (int i=1;i<concat.length();i++) {
            int j=kmp[i-1];
            while (j>0&&concat.charAt(i)!=concat.charAt(j)) j=kmp[j-1];
            if (concat.charAt(i)==concat.charAt(j)) j++;
            kmp[i]=j;
        }
        return rev.substring(0,rev.length()-kmp[kmp.length-1])+s;
    }
    public static void main(String[] args) {
        System.out.println(shortestPalindrome("aacecaaa")); // "aaacecaaa"
        System.out.println(shortestPalindrome("abcd")); // "dcbabcd"
    }
}
