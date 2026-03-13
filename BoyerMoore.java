import java.util.*;
public class BoyerMoore {
    static void search(String txt, String pat) {
        int n=txt.length(), m=pat.length();
        int[] bad=new int[256]; Arrays.fill(bad,-1);
        for (int i=0;i<m;i++) bad[pat.charAt(i)]=i;
        int s=0;
        while (s<=n-m) {
            int j=m-1;
            while (j>=0&&pat.charAt(j)==txt.charAt(s+j)) j--;
            if (j<0) { System.out.println("Match at "+s); s+=(s+m<n)?(m-bad[txt.charAt(s+m)]):1; }
            else s+=Math.max(1,j-bad[txt.charAt(s+j)]);
        }
    }
    public static void main(String[] args) { search("ABAAABCD","ABC"); }
}
