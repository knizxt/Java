import java.util.*;

public class PollardRho {

    static long mulmod(long a, long b, long m) {
        long res = 0; a %= m;
        while (b > 0) { if ((b & 1) == 1) res = (res + a) % m; a = 2 * a % m; b >>= 1; }
        return res;
    }

    static long powmod(long b, long e, long m) {
        long r = 1; b %= m;
        while (e > 0) { if ((e & 1) == 1) r = mulmod(r, b, m); b = mulmod(b, b, m); e >>= 1; }
        return r;
    }

    static long gcd(long a, long b) { return b == 0 ? a : gcd(b, a % b); }

    static long f(long x, long c, long n) { return (mulmod(x, x, n) + c) % n; }

    static long pollardRho(long n) {
        if (n % 2 == 0) return 2;
        Random rnd = new Random(42);
        long x = rnd.nextLong() % (n - 2) + 2;
        long y = x;
        long c = rnd.nextLong() % (n - 1) + 1;
        long d = 1;
        while (d == 1) {
            x = f(x, c, n);
            y = f(f(y, c, n), c, n);
            d = gcd(Math.abs(x - y), n);
        }
        return d == n ? -1 : d; // -1 = failure, retry with different c
    }

    static void factorize(long n, List<Long> factors) {
        if (n == 1) return;
        if (isPrime(n)) { factors.add(n); return; }
        long d = -1;
        while (d == -1 || d == n) d = pollardRho(n);
        factorize(d, factors);
        factorize(n / d, factors);
    }

    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n < 4) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        long d = n - 1; int r = 0;
        while (d % 2 == 0) { d /= 2; r++; }
        for (long a : new long[]{2, 3, 5, 7, 11, 13}) {
            if (a >= n) continue;
            long x = powmod(a, d, n);
            if (x == 1 || x == n - 1) continue;
            boolean composite = true;
            for (int i = 0; i < r - 1; i++) { x = mulmod(x, x, n); if (x == n - 1) { composite = false; break; } }
            if (composite) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        long[] nums = {91, 1024, 600851475143L, 9999999999971L};
        for (long n : nums) {
            List<Long> factors = new ArrayList<>();
            factorize(n, factors);
            Collections.sort(factors);
            System.out.println("Factors of " + n + ": " + factors);
        }
    }
}
