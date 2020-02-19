public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        isIndexLegal("add", index);
        Node prev = getPrevNode(index);
        prev.next = new Node(e, prev.next);
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    // 返回索引为index的节点的数据
    public E get(int index) {
        isIndexLegal("get", index);
        return getCurNode(index).e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        isIndexLegal("set", index);
        getCurNode(index).e = e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (e.equals(cur.e))
                return true;
            cur = cur.next;
        }
        return false;
    }

    public E remove(int index) {
        isIndexLegal("remove", index);
        Node prev = getPrevNode(index);

        Node delNode = prev.next;
        prev.next = delNode.next;

        delNode.next = null;
        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    // 返回索引为index的前一个节点
    private Node getPrevNode(int index) {
        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;
        return prev;
    }

    // 返回索引为index所在的节点
    private Node getCurNode(int index) {
        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        return cur;
    }

    private void isIndexLegal(String methodName, int index) {
        if (methodName.equals("add")) {
            if (index < 0 || index > size)
                throw new IllegalArgumentException(methodName + " failed required index >= 0 and index <= size");
        } else {
            if (index < 0 || index >= size)
                throw new IllegalArgumentException(methodName + " failed required index >= 0 and index < size");
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        // sb.append(String.format("LinkedList: size=%d ", size));
        for (int i = 0; i < size; i++) {
            sb.append(get(i));
            sb.append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }
}
