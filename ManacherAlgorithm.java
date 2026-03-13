import java.util.*;
public class ManacherAlgorithm {
    static String longestPalindrome(String s) {
        String t="#"+String.join("#",s.split("")+"")+"#";
        int n=t.length(); int[] p=new int[n]; int c=0,r=0;
        for (int i=0;i<n;i++) {
            int mirror=2*c-i;
            if (i<r) p[i]=Math.min(r-i,p[mirror]);
            while (i+p[i]+1<n&&i-p[i]-1>=0&&t.charAt(i+p[i]+1)==t.charAt(i-p[i]-1)) p[i]++;
            if (i+p[i]>r) { c=i; r=i+p[i]; }
        }
        int maxLen=0,center=0;
        for (int i=0;i<n;i++) if (p[i]>maxLen) { maxLen=p[i]; center=i; }
        return s.substring((center-maxLen)/2,(center+maxLen)/2);
    }
    public static void main(String[] args) {
        System.out.println(longestPalindrome("babad")); // bab or aba
        System.out.println(longestPalindrome("cbbd")); // bb
    }
}
