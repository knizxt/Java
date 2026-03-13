import java.util.*;
public class SieveOfEratosthenes {
    static boolean[] sieve(int n) {
        boolean[] prime=new boolean[n+1]; Arrays.fill(prime,true); prime[0]=prime[1]=false;
        for (int p=2;p*p<=n;p++) if (prime[p]) for (int m=p*p;m<=n;m+=p) prime[m]=false;
        return prime;
    }
    public static void main(String[] args) {
        boolean[] primes=sieve(50);
        System.out.print("Primes up to 50: ");
        for (int i=2;i<=50;i++) if (primes[i]) System.out.print(i+" ");
        System.out.println();
    }
}
