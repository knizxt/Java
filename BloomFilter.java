import java.util.*;

public class BloomFilter {
    long[] bitset;
    int bitsetSize, numHashFunctions;

    BloomFilter(int expectedItems, double falsePositiveRate) {
        bitsetSize = (int)(-expectedItems * Math.log(falsePositiveRate) / (Math.log(2)*Math.log(2)));
        numHashFunctions = (int)(((double)bitsetSize/expectedItems)*Math.log(2));
        bitset = new long[(bitsetSize+63)/64];
    }

    void add(String item) {
        for (int i=0;i<numHashFunctions;i++) {
            int h = Math.abs((item.hashCode()^(i*1000003)) % bitsetSize);
            bitset[h/64] |= (1L<<(h%64));
        }
    }

    boolean mightContain(String item) {
        for (int i=0;i<numHashFunctions;i++) {
            int h = Math.abs((item.hashCode()^(i*1000003)) % bitsetSize);
            if ((bitset[h/64]&(1L<<(h%64)))==0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        BloomFilter bf = new BloomFilter(100, 0.01);
        bf.add("hello"); bf.add("world"); bf.add("java");
        System.out.println("hello present: " + bf.mightContain("hello")); // true
        System.out.println("python present: " + bf.mightContain("python")); // probably false
    }
}
