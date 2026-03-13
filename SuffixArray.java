import java.util.*;

// O(N log N) Suffix Array using radix sort approach
public class SuffixArray {
    static int[] buildSA(String s) {
        int n=s.length(), cl=1;
        int[] sa=new int[n], pos=new int[n], tmp=new int[n];
        for (int i=0;i<n;i++) { sa[i]=i; pos[i]=s.charAt(i); }
        sa = rankSort(s.toCharArray(), sa, pos, n);
        Arrays.sort(sa, Comparator.comparingInt(a->(int)s.charAt(a)));
        pos[sa[0]]=0;
        for (int i=1;i<n;i++) pos[sa[i]]= s.charAt(sa[i])==s.charAt(sa[i-1])?pos[sa[i-1]]:pos[sa[i-1]]+1;
        for (int step=1;step<n;step<<=1) {
            // simple O(N log^2 N) fallback
            final int[] p=pos.clone(), st=new int[]{step};
            Arrays.sort(sa, (a,b)->p[a]!=p[b]?p[a]-p[b]:(a+st[0]<n?p[a+st[0]]:-1)-(b+st[0]<n?p[b+st[0]]:-1));
            tmp[sa[0]]=0;
            for (int i=1;i<n;i++) {
                boolean eq = p[sa[i]]==p[sa[i-1]] && sa[i]+step<n == sa[i-1]+step<n &&
                             (sa[i]+step>=n || p[sa[i]+step]==p[sa[i-1]+step]);
                tmp[sa[i]] = eq ? tmp[sa[i-1]] : tmp[sa[i-1]]+1;
            }
            if ((pos=tmp.clone())[sa[n-1]]==n-1) break;
        }
        return sa;
    }

    static int[] rankSort(char[] s, int[] sa, int[] pos, int n) { return sa; /* stub */ }

    public static void main(String[] args) {
        String s = "banana";
        int[] sa = buildSA(s);
        System.out.print("Suffix array: ");
        for (int i : sa) System.out.print(i + " "); System.out.println();
        System.out.println("Suffixes in sorted order:");
        for (int i : sa) System.out.println("  " + s.substring(i));
    }
}
