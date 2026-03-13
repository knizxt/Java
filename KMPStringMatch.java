import java.util.*;
public class KMPStringMatch {
    static int[] buildLPS(String pat) {
        int n=pat.length(); int[] lps=new int[n]; int len=0; int i=1;
        while (i<n) {
            if (pat.charAt(i)==pat.charAt(len)) { lps[i++]=++len; }
            else if (len!=0) len=lps[len-1];
            else lps[i++]=0;
        }
        return lps;
    }
    static void kmpSearch(String txt, String pat) {
        int n=txt.length(), m=pat.length(); int[] lps=buildLPS(pat);
        int i=0, j=0;
        while (i<n) {
            if (txt.charAt(i)==pat.charAt(j)) { i++; j++; }
            if (j==m) { System.out.println("Pattern at index "+(i-j)); j=lps[j-1]; }
            else if (i<n&&txt.charAt(i)!=pat.charAt(j)) {
                if (j!=0) j=lps[j-1]; else i++;
            }
        }
    }
    public static void main(String[] args) { kmpSearch("AABAACAADAABAABA","AABA"); }
}
