/**
 *  使用动态数组实现一个循环队列
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        // 把该队列想象成由数组围起来的一个圈,当front和tail相差1的时候
        // 如果再往队列中添加元素,tail等于front,队列已满,此时将无法判断队列到底是空的还是满的
        // 所以,在设计循环队列的时候,我们要浪费一个空间,front跟tail相差1就视为队列已满
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("cann't dequeue from a empty queue");

        E deqEle = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);

        return deqEle;
    }

    @Override
    public E getFront() {
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue(cap=%d,size=%d): front [", getCapacity(), size));
        for (int i = 0; i < size; i++) {
            sb.append(data[(front + i) % data.length]);
            if (i != size - 1)
                sb.append(", ");
        }
        sb.append("] tail");
        return sb.toString();
    }
}
