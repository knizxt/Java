import java.util.*;

public class LRUCache {
    int cap;
    Map<Integer,Integer> map = new LinkedHashMap<>();

    LRUCache(int capacity) { this.cap = capacity; }

    int get(int key) {
        if (!map.containsKey(key)) return -1;
        int val = map.remove(key); map.put(key, val); return val;
    }

    void put(int key, int value) {
        if (map.containsKey(key)) map.remove(key);
        else if (map.size()==cap) map.remove(map.keySet().iterator().next());
        map.put(key, value);
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1); cache.put(2, 2);
        System.out.println(cache.get(1));    // 1
        cache.put(3, 3);                     // evicts key 2
        System.out.println(cache.get(2));    // -1 (not found)
        cache.put(4, 4);                     // evicts key 1
        System.out.println(cache.get(1));    // -1
        System.out.println(cache.get(3));    // 3
        System.out.println(cache.get(4));    // 4
    }
}
