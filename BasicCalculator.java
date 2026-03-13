import java.util.*;
public class BasicCalculator {
    static int calculate(String s) {
        Deque<Integer> stack=new ArrayDeque<>(); int num=0,res=0,sign=1;
        for (int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            if (Character.isDigit(c)) num=num*10+(c-'0');
            else if (c=='+') { res+=sign*num; num=0; sign=1; }
            else if (c=='-') { res+=sign*num; num=0; sign=-1; }
            else if (c=='(') { stack.push(res); stack.push(sign); res=0; sign=1; }
            else if (c==')') { res+=sign*num; num=0; res*=stack.pop(); res+=stack.pop(); }
        }
        return res+sign*num;
    }
    public static void main(String[] args) {
        System.out.println(calculate("1 + 1")); // 2
        System.out.println(calculate(" 2-1 + 2 ")); // 3
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)")); // 23
    }
}
