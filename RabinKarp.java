import java.util.*;
public class RabinKarp {
    static final int BASE=256, MOD=101;
    static void search(String txt, String pat) {
        int n=txt.length(), m=pat.length(), h=1;
        for (int i=0;i<m-1;i++) h=(h*BASE)%MOD;
        int pH=0, tH=0;
        for (int i=0;i<m;i++) { pH=(BASE*pH+pat.charAt(i))%MOD; tH=(BASE*tH+txt.charAt(i))%MOD; }
        for (int i=0;i<=n-m;i++) {
            if (pH==tH && txt.substring(i,i+m).equals(pat)) System.out.println("Match at index "+i);
            if (i<n-m) { tH=(BASE*(tH-txt.charAt(i)*h)+txt.charAt(i+m))%MOD; if (tH<0) tH+=MOD; }
        }
    }
    public static void main(String[] args) { search("GEEKS FOR GEEKS","GEEK"); }
}
