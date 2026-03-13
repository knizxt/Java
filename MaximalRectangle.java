import java.util.*;
public class MaximalRectangle {
    static int largestRect(int[] heights) {
        Deque<Integer> st=new ArrayDeque<>(); int max=0;
        for (int i=0;i<=heights.length;i++) {
            int h=(i==heights.length)?0:heights[i];
            while (!st.isEmpty()&&h<heights[st.peek()]) {
                int top=st.pop();
                int w=st.isEmpty()?i:i-st.peek()-1;
                max=Math.max(max,heights[top]*w);
            }
            st.push(i);
        }
        return max;
    }
    static int maximalRectangle(char[][] matrix) {
        if (matrix.length==0) return 0;
        int[] h=new int[matrix[0].length], max=new int[]{0};
        for (char[] row:matrix) {
            for (int j=0;j<h.length;j++) h[j] = row[j]=='0'?0:h[j]+1;
            max[0]=Math.max(max[0], largestRect(h));
        }
        return max[0];
    }
    public static void main(String[] args) {
        char[][] mat={{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println("Maximal rectangle: " + maximalRectangle(mat)); // 6
    }
}
