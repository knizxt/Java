import java.util.*;
public class SegmentedSieve {
    static void segmentedSieve(long L, long R) {
        int limit=(int)Math.sqrt(R)+1;
        boolean[] sieve=new boolean[limit+1]; Arrays.fill(sieve,true); sieve[0]=sieve[1]=false;
        List<Integer> primes=new ArrayList<>();
        for (int p=2;p<=limit;p++) { if (sieve[p]) { primes.add(p); for (int j=p*p;j<=limit;j+=p) sieve[j]=false; } }
        boolean[] seg=new boolean[(int)(R-L+1)]; Arrays.fill(seg,true);
        for (int p:primes) for (long j=Math.max((long)p*p,(L+p-1)/p*p);j<=R;j+=p) seg[(int)(j-L)]=false;
        if (L==1) seg[0]=false;
        System.out.println("Primes in ["+L+","+R+"]:");
        for (long i=L;i<=R;i++) if (seg[(int)(i-L)]) System.out.print(i+" ");
        System.out.println();
    }
    public static void main(String[] args) { segmentedSieve(10,50); }
}
