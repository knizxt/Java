import java.util.*;

// Cartesian Tree: min-heap property + BST property by index
public class CartesianTree {
    static class Node {
        int val, idx; Node left, right;
        Node(int v, int i) { val=v; idx=i; }
    }

    static Node build(int[] arr) {
        Deque<Node> stack = new ArrayDeque<>();
        Node last=null;
        for (int i=0;i<arr.length;i++) {
            Node cur = new Node(arr[i], i);
            last=null;
            while (!stack.isEmpty() && stack.peek().val > arr[i]) {
                last = stack.pop();
            }
            if (last!=null) cur.left=last;
            if (!stack.isEmpty()) stack.peek().right=cur;
            stack.push(cur);
        }
        while (stack.size()>1) stack.pop();
        return stack.pop();
    }

    static void inorder(Node n) {
        if (n==null) return; inorder(n.left); System.out.print(n.val+" "); inorder(n.right);
    }

    public static void main(String[] args) {
        int[] arr = {3,2,6,1,9};
        Node root = build(arr);
        System.out.print("Inorder of Cartesian tree: "); inorder(root); System.out.println();
    }
}
