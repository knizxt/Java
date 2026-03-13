import java.util.*;

// Right-threaded BST - inorder traversal without stack
public class ThreadedBinaryTree {
    static class Node {
        int val; Node left, right; boolean rightThread;
        Node(int v) { val=v; }
    }

    Node root;

    void insert(int key) {
        Node p=null, cur=root;
        while (cur!=null) {
            p=cur;
            if (key<cur.val) cur=cur.left;
            else if (!cur.rightThread) cur=cur.right;
            else break;
        }
        Node n=new Node(key);
        if (p==null) { root=n; }
        else if (key<p.val) { n.rightThread=true; n.right=p; p.left=n; }
        else { n.rightThread=p.rightThread; n.right=p.right; p.right=n; p.rightThread=false; }
    }

    void inorder() {
        // leftmost node
        Node cur=root; while (cur.left!=null) cur=cur.left;
        while (cur!=null) {
            System.out.print(cur.val+" ");
            if (cur.rightThread) cur=cur.right;
            else { cur=cur.right; if (cur!=null) while (cur.left!=null) cur=cur.left; }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ThreadedBinaryTree t=new ThreadedBinaryTree();
        for (int k:new int[]{5,3,7,1,4,6,8}) t.insert(k);
        System.out.print("Threaded inorder: "); t.inorder();
    }
}
