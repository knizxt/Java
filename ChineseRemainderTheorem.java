import java.util.*;

public class ChineseRemainderTheorem {

    static long[] extGCD(long a, long b) {
        if (b == 0) return new long[]{a, 1, 0};
        long[] d = extGCD(b, a % b);
        return new long[]{d[0], d[2], d[1] - (a / b) * d[2]};
    }

    // Solve: x = r[i] (mod m[i]) for all i
    // Requires all m[i] to be pairwise coprime
    static long crt(long[] r, long[] m) {
        long M = 1;
        for (long mi : m) M *= mi;
        long x = 0;
        for (int i = 0; i < r.length; i++) {
            long Mi = M / m[i];
            long[] gcd = extGCD(Mi, m[i]);
            x = (x + r[i] * Mi % M * ((gcd[1] % M + M) % M)) % M;
        }
        return (x % M + M) % M;
    }

    public static void main(String[] args) {
        // x = 2 (mod 3), x = 3 (mod 5), x = 2 (mod 7) => x = 23
        long[] r = {2, 3, 2};
        long[] m = {3, 5, 7};
        System.out.println("CRT solution: " + crt(r, m)); // 23
        System.out.println("Verify: 23 mod 3=" + (23%3) + ", 23 mod 5=" + (23%5) + ", 23 mod 7=" + (23%7));

        // Another example: x = 1 (mod 2), x = 2 (mod 3), x = 3 (mod 5)
        System.out.println("CRT(1,2|2,3|3,5) = " + crt(new long[]{1,2,3}, new long[]{2,3,5})); // 23
    }
}
