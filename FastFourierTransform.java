import java.util.*;

// Cooley-Tukey FFT - recursive split into even/odd indices
public class FastFourierTransform {

    static void fft(double[] re, double[] im, boolean inverse) {
        int n = re.length;
        // bit-reversal permutation
        for (int i = 1, j = 0; i < n; i++) {
            int bit = n >> 1;
            for (; (j & bit) != 0; bit >>= 1) j ^= bit;
            j ^= bit;
            if (i < j) {
                double t = re[i]; re[i] = re[j]; re[j] = t;
                t = im[i]; im[i] = im[j]; im[j] = t;
            }
        }
        // butterfly operations
        for (int len = 2; len <= n; len <<= 1) {
            double ang = 2 * Math.PI / len * (inverse ? -1 : 1);
            double wr = Math.cos(ang), wi = Math.sin(ang);
            for (int i = 0; i < n; i += len) {
                double cr = 1, ci = 0;
                for (int j = 0; j < len / 2; j++) {
                    double ur = re[i+j], ui = im[i+j];
                    double vr = re[i+j+len/2] * cr - im[i+j+len/2] * ci;
                    double vi = re[i+j+len/2] * ci + im[i+j+len/2] * cr;
                    re[i+j] = ur + vr; im[i+j] = ui + vi;
                    re[i+j+len/2] = ur - vr; im[i+j+len/2] = ui - vi;
                    double ncr = cr * wr - ci * wi;
                    ci = cr * wi + ci * wr;
                    cr = ncr;
                }
            }
        }
        if (inverse) for (int i = 0; i < n; i++) { re[i] /= n; im[i] /= n; }
    }

    // Multiply two polynomials using FFT
    static long[] polyMultiply(long[] a, long[] b) {
        int result_size = a.length + b.length - 1;
        int n = Integer.highestOneBit(result_size - 1) << 1;
        double[] ar = new double[n], ai = new double[n], br = new double[n], bi = new double[n];
        for (int i = 0; i < a.length; i++) ar[i] = a[i];
        for (int i = 0; i < b.length; i++) br[i] = b[i];
        fft(ar, ai, false); fft(br, bi, false);
        double[] cr = new double[n], ci = new double[n];
        for (int i = 0; i < n; i++) { cr[i] = ar[i]*br[i] - ai[i]*bi[i]; ci[i] = ar[i]*bi[i] + ai[i]*br[i]; }
        fft(cr, ci, true);
        long[] result = new long[result_size];
        for (int i = 0; i < result_size; i++) result[i] = Math.round(cr[i]);
        return result;
    }

    public static void main(String[] args) {
        // Multiply (1 + 2x + 3x^2) * (4 + 5x) = 4 + 13x + 22x^2 + 15x^3
        long[] p1 = {1, 2, 3}, p2 = {4, 5};
        long[] product = polyMultiply(p1, p2);
        System.out.println("Polynomial product: " + Arrays.toString(product)); // [4, 13, 22, 15]

        // Demonstrate FFT on signal
        double[] signal = {1, 2, 3, 4, 0, 0, 0, 0};
        double[] imag = new double[8];
        fft(signal, imag, false);
        System.out.print("FFT magnitudes: ");
        for (int i = 0; i < 8; i++) System.out.printf("%.2f ", Math.hypot(signal[i], imag[i]));
        System.out.println();
    }
}
