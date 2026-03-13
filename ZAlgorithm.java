import java.util.*;
public class ZAlgorithm {
    static int[] buildZ(String s) {
        int n=s.length(); int[] z=new int[n]; int l=0,r=0;
        for (int i=1;i<n;i++) {
            if (i<r) z[i]=Math.min(r-i,z[i-l]);
            while (i+z[i]<n && s.charAt(z[i])==s.charAt(i+z[i])) z[i]++;
            if (i+z[i]>r) { l=i; r=i+z[i]; }
        }
        return z;
    }
    static void search(String txt, String pat) {
        String s=pat+"$"+txt; int m=pat.length();
        int[] z=buildZ(s);
        for (int i=m+1;i<s.length();i++) if (z[i]==m) System.out.println("Match at "+(i-m-1));
    }
    public static void main(String[] args) { search("aabxaaabxaabxaaabx","aabx"); }
}
