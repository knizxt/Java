import java.util.*;
public class Permutations {
    static void permute(int[] arr, int start) {
        if (start==arr.length) { System.out.println(Arrays.toString(arr)); return; }
        for (int i=start;i<arr.length;i++) {
            int t=arr[start]; arr[start]=arr[i]; arr[i]=t;
            permute(arr,start+1);
            t=arr[start]; arr[start]=arr[i]; arr[i]=t;
        }
    }
    public static void main(String[] args) {
        permute(new int[]{1,2,3},0);
    }
}
