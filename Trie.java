import java.util.*;

public class Trie {
    static class Node {
        Node[] ch = new Node[26];
        boolean end;
    }

    Node root = new Node();

    void insert(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int i = c-'a';
            if (cur.ch[i] == null) cur.ch[i] = new Node();
            cur = cur.ch[i];
        }
        cur.end = true;
    }

    boolean search(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            int i = c-'a';
            if (cur.ch[i] == null) return false;
            cur = cur.ch[i];
        }
        return cur.end;
    }

    boolean startsWith(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            int i = c-'a';
            if (cur.ch[i] == null) return false;
            cur = cur.ch[i];
        }
        return true;
    }

    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("apple"); t.insert("app"); t.insert("banana");
        System.out.println(t.search("apple"));    // true
        System.out.println(t.search("app"));      // true
        System.out.println(t.search("appl"));     // false
        System.out.println(t.startsWith("ban"));  // true
    }
}
