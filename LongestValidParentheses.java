import java.util.*;
public class LongestValidParentheses {
    static int longestValid(String s) {
        Deque<Integer> stack=new ArrayDeque<>(); stack.push(-1); int max=0;
        for (int i=0;i<s.length();i++) {
            if (s.charAt(i)=='(') stack.push(i);
            else {
                stack.pop();
                if (stack.isEmpty()) stack.push(i);
                else max=Math.max(max,i-stack.peek());
            }
        }
        return max;
    }
    public static void main(String[] args) {
        System.out.println(longestValid("(()")); // 2
        System.out.println(longestValid(")()())")); // 4
    }
}
