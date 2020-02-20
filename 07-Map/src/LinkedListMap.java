/**
 * 基于链表实现的映射
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key) {
            this(key, null, null);
        }

        public Node() {
            this(null, null, null);
        }

        @Override
        public String toString() {
            return key.toString() + " : " + value.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // 返回键为key所在的节点
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (key.equals(cur.key))
                return cur;
            cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    /**
     * 往映射中添加键值对
     * 1. 如果key不存在, 把键值对添加到链表的头结点
     * 2. 如果key存在, 覆盖key所在的节点的value值
     *
     * @param key   键
     * @param value 值
     */
    @Override
    public void add(K key, V value) {
        Node node = getNode(key);
        if (node != null) { // key存在
            node.value = value;
        } else { // key不存在
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        }
    }

    @Override
    public V remove(K key) {
        Node prev = dummyHead;

        while (prev.next != null) {
            if (key.equals(prev.next.key)) {
                break;
            }
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            size--;
            return delNode.value;
        }

        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
        return node != null ? node.value : null;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node != null)
            node.value = value;
        else
            throw new IllegalArgumentException("set failed, " + key + " is not exist");
    }
}
