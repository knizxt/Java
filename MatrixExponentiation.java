import java.util.*;

public class MatrixExponentiation {
    static final long MOD = 1_000_000_007;

    static long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];
        for (int i = 0; i < n; i++)
            for (int k = 0; k < n; k++)
                if (A[i][k] != 0)
                    for (int j = 0; j < n; j++)
                        C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
        return C;
    }

    static long[][] matpow(long[][] M, long p) {
        int n = M.length;
        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) res[i][i] = 1; // identity
        while (p > 0) {
            if ((p & 1) == 1) res = multiply(res, M);
            M = multiply(M, M);
            p >>= 1;
        }
        return res;
    }

    // Fibonacci in O(log n) using matrix exponentiation
    static long fib(long n) {
        if (n <= 1) return n;
        long[][] M = {{1, 1}, {1, 0}};
        return matpow(M, n)[0][1];
    }

    // Example: solve a(n) = 2*a(n-1) + 3*a(n-2), a(0)=0, a(1)=1
    static long solveRecurrence(int n) {
        if (n == 0) return 0; if (n == 1) return 1;
        long[][] M = {{2, 3}, {1, 0}};
        long[][] res = matpow(M, n - 1);
        return res[0][0]; // res * [a(1), a(0)] = res * [1, 0]
    }

    public static void main(String[] args) {
        System.out.println("Fib(10) = " + fib(10));   // 55
        System.out.println("Fib(50) = " + fib(50));
        System.out.println("Fib(1000000) mod 10^9+7 = " + fib(1_000_000));
        System.out.println("Recurrence a(5) = " + solveRecurrence(5)); // 10
    }
}
