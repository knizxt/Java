import java.util.*;
import java.math.BigInteger;

// Karatsuba fast multiplication: O(n^1.585) vs standard O(n^2)
public class KaratsubaMultiplication {

    static BigInteger karatsuba(BigInteger x, BigInteger y) {
        int n = Math.max(x.bitLength(), y.bitLength());
        if (n <= 128) return x.multiply(y); // base case: use built-in for small numbers

        int half = n / 2;
        BigInteger high1 = x.shiftRight(half);
        BigInteger low1  = x.subtract(high1.shiftLeft(half));
        BigInteger high2 = y.shiftRight(half);
        BigInteger low2  = y.subtract(high2.shiftLeft(half));

        // Three recursive calls instead of four
        BigInteger z2 = karatsuba(high1, high2);
        BigInteger z0 = karatsuba(low1, low2);
        BigInteger z1 = karatsuba(low1.add(high1), low2.add(high2)).subtract(z2).subtract(z0);

        return z2.shiftLeft(2 * half).add(z1.shiftLeft(half)).add(z0);
    }

    public static void main(String[] args) {
        BigInteger a = new BigInteger("12345678901234567890");
        BigInteger b = new BigInteger("98765432109876543210");

        BigInteger karatsuba_result = karatsuba(a, b);
        BigInteger standard_result  = a.multiply(b);

        System.out.println("Standard:  " + standard_result);
        System.out.println("Karatsuba: " + karatsuba_result);
        System.out.println("Match: " + karatsuba_result.equals(standard_result));
    }
}
