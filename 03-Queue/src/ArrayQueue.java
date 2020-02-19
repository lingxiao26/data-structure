public class ArrayQueue<E> implements Queue<E> {

    private Array<E> array;

    public ArrayQueue(int capacity) {
        array = new Array<>(capacity);
    }

    public ArrayQueue() {
        array = new Array<>();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    /**
     * 将元素e添加到队列的队尾, 将数组中的最后一个元素作为队尾
     */
    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    /**
     * 出队,获取队列中队首的元素
     */
    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    /**
     * 获取队首元素
     */
    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Queue: front <== [");
        for (int i=0; i<array.getSize(); i++) {
            sb.append(array.get(i));
            if (i != array.getSize()-1)
                sb.append(", ");
        }
        sb.append("] <== tail");
        return sb.toString();
    }
}
