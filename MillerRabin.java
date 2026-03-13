import java.util.*;

// Deterministic Miller-Rabin primality test for n < 3.3 * 10^24
public class MillerRabin {

    static long mulmod(long a, long b, long m) {
        long res = 0; a %= m;
        while (b > 0) {
            if ((b & 1) == 1) res = (res + a) % m;
            a = (a * 2) % m;
            b >>= 1;
        }
        return res;
    }

    static long powmod(long base, long exp, long mod) {
        long res = 1; base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = mulmod(res, base, mod);
            base = mulmod(base, base, mod);
            exp >>= 1;
        }
        return res;
    }

    static boolean millerRound(long n, long a) {
        long d = n - 1; int r = 0;
        while (d % 2 == 0) { d /= 2; r++; }
        long x = powmod(a, d, n);
        if (x == 1 || x == n - 1) return true;
        for (int i = 0; i < r - 1; i++) {
            x = mulmod(x, x, n);
            if (x == n - 1) return true;
        }
        return false;
    }

    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n == 2 || n == 3 || n == 5 || n == 7) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        // deterministic witnesses for n < 3,215,031,751
        for (long a : new long[]{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37}) {
            if (n == a) return true;
            if (!millerRound(n, a)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long[] tests = {2, 3, 15, 97, 561, 7919, 1_000_003L, 1_000_000_007L};
        for (long n : tests)
            System.out.println(n + " is prime: " + isPrime(n));
    }
}
