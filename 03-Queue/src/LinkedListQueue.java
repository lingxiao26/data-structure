/**
 * 使用链表来实现一个队列,在常规的链表结构中添加一个尾指针指向最后一个节点
 * 在链表的头部添加和删除一个节点的时间复杂度都是O(1)
 * 在链表的尾部,因为维护了一个尾结点,所以
 *      添加一个节点的时间复杂度是O(1)
 *      删除的时候,因为要找到它的前一个节点,所以时间复杂度仍然是O(n)
 *
 * 所以,把链表的 头部==>队首, 尾部==>队尾
 */
public class LinkedListQueue<E> implements Queue<E> {

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

    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
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

    @Override
    public void enqueue(E e) {
        if (size == 0) {
            head = new Node(e, null);
            tail = head;
        } else {
            tail.next = new Node(e, null);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0)
            throw new IllegalArgumentException("cannot dequeue from a empty queue");

        Node delNode = head;
        head = head.next;
        delNode.next = null;
        size--;
        // 如果出队之后队列为空,此时tail依然指向delNode,要把tail指向null
        if (head == null)
            tail = null;

        return delNode.e;
    }

    @Override
    public E getFront() {
        if (head == null)
            throw new IllegalArgumentException("cannot get from a empty queue");
        return head.e;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front [");
        Node cur = head;
        while (cur != null) {
            sb.append(cur.e+"<-");
            cur = cur.next;
        }
        sb.append("null] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();
        for (int i=0; i<5; i++) {
            queue.enqueue(i+1);
            System.out.println(queue);
        }
        queue.dequeue();
        System.out.println(queue);
        queue.dequeue();
        System.out.println(queue);
    }
}
