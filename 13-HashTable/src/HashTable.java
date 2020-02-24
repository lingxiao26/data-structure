import java.util.TreeMap;

public class HashTable<K, V> {

    // 当M取如下这些值(素数)的时候, hash函数的值能做到尽量分布均匀
    // 最后一个值1610612741是最接近Integer.MAX_VALUE的一个素数
    private static final int[] capacity = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    // N: 元素个数; M: hashtable的容量;
    // 当N/M >= upperTol时进行扩容;
    private static final int upperTol = 10;
    // 当N/M < lowerTol时进行缩容;
    private static final int lowerTol = 2;
    // hashtable初始容量
    //private static final int initCapacity = 7;
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;
    private int M;  // capacity/大的素数
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

//    public HashTable() {
//        this(initCapacity);
//    }

    public int getSize() {
        return size;
    }

    public int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key))
            map.put(key, value);
        else {
            map.put(key, value);
            size++;
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K, V> map = hashtable[hash(key)];
        V remVal = null;
        if (map.containsKey(key)) {
            remVal = map.remove(key);
            size--;
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return remVal;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key))
            throw new IllegalArgumentException(key + "doesn't exist");
        map.put(key, value);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newTable[i] = new TreeMap<>();
        }

        int oldM = this.M;
        this.M = newM;// newTable在计算hash值时会用到M, 不执行这条语句用的还是扩容以前的M
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                newTable[hash(key)].put(key, map.get(key));
            }
        }

        this.hashtable = newTable;
    }
}
