/**
 * 使用动态数组实现一个最大堆
 */
public class MaxHeap<E extends Comparable> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    // 将传入的数组转换为一个最大堆(heapify)
    public MaxHeap(E[] arr) {
        data = new Array<>(arr);
        heapify();
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    // 返回heap中索引为index的父节点的索引
    public int parent(int index) {
        if (index <= 0 || index > data.getSize()-1)
            throw new IllegalArgumentException("required index > 0 and index < data.size");
        return (index - 1) / 2;
    }

    // 返回heap中索引为index的左孩子的索引
    public int leftChild(int index) {
        return index * 2 + 1;
    }

    // 返回heap中索引为index的右孩子的索引
    public int rightChild(int index) {
        return index * 2 + 2;
    }

    // 往堆中添加元素e
    public void add(E e) {
        data.addLast(e);
        siftUp(getSize() - 1);
    }

    // 取出堆中最大的元素
    public E extractMax() {
        E max = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return max;
    }

    // 查看堆中最大的元素
    public E findMax() {
        if (getSize() == 0)
            throw new IllegalArgumentException("cannot find max from a empty heap");
        return data.getFirst();
    }

    // 把索引为k的节点上移,使整个数组满足堆的定义
    private void siftUp(int k) {
        while (k > 0 &&
                data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    // 把索引为k的节点下移,使整个数组满足堆的定义
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() &&
                    data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(k);
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆中最大的元素,并替换成元素e
    public E replace(E e) {
        E max = findMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }

    // 把data转换为一个堆
    private void heapify() {
        // 最后一个非叶子节点
        int last = parent(data.getSize()-1);
        for (int i=last; i>=0; i--) {
            siftDown(i);
        }
    }
}
