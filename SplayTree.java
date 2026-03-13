import java.util.*;

// Splay Tree - self-adjusting BST
public class SplayTree {
    static class Node { int key; Node left,right; Node(int k){key=k;} }
    Node root;

    Node rotateRight(Node x) { Node y=x.left; x.left=y.right; y.right=x; return y; }
    Node rotateLeft(Node x) { Node y=x.right; x.right=y.left; y.left=x; return y; }

    Node splay(Node root, int key) {
        if (root==null||root.key==key) return root;
        if (key<root.key) {
            if (root.left==null) return root;
            if (key<root.left.key) { root.left.left=splay(root.left.left,key); root=rotateRight(root); }
            else if (key>root.left.key) { root.left.right=splay(root.left.right,key); if (root.left.right!=null) root.left=rotateLeft(root.left); }
            return root.left==null?root:rotateRight(root);
        } else {
            if (root.right==null) return root;
            if (key>root.right.key) { root.right.right=splay(root.right.right,key); root=rotateLeft(root); }
            else if (key<root.right.key) { root.right.left=splay(root.right.left,key); if (root.right.left!=null) root.right=rotateRight(root.right); }
            return root.right==null?root:rotateLeft(root);
        }
    }

    void insert(int key) {
        if (root==null) { root=new Node(key); return; }
        root=splay(root,key);
        if (root.key==key) return;
        Node n=new Node(key);
        if (key<root.key) { n.right=root; n.left=root.left; root.left=null; }
        else { n.left=root; n.right=root.right; root.right=null; }
        root=n;
    }

    void inorder(Node n) { if (n==null) return; inorder(n.left); System.out.print(n.key+" "); inorder(n.right); }

    public static void main(String[] args) {
        SplayTree st=new SplayTree();
        for (int k:new int[]{100,50,200,40,60}) st.insert(k);
        System.out.print("Inorder: "); st.inorder(st.root); System.out.println();
    }
}
