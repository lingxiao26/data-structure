import java.util.ArrayList;
import java.util.List;

//@SuppressWarnings("all")
public class Array<E> {
    private E[] data;
    private int size;

    // 初始化一个容量为capacity的数组
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 初始化一个默认容量为10的数组
    public Array() {
        this(10);
    }

    // 获取数组存放了多少个元素
    public int getSize() {
        return size;
    }

    // 获取数组的大小
    public int getCapacity() {
        return data.length;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return size == 0;
    }

    // 在数组索引为index处插入元素e
    public void insert(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed! required index > 0 and index <= size");

        if (size == data.length)
            resize(data.length * 2);

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    // 在数组的头部插入元素e
    public void addFirst(E e) {
        insert(0, e);
    }

    // 在数组的尾部插入元素e
    public void addLast(E e) {
        insert(size, e);
    }

    // 获取索引为index的元素
    public E get(int index) {
        isIndexLegal("get", index);
        return data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    // 判断数组是否包含元素e
    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    // 查找元素e所在的索引
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 查找数组中所有值为e的元素
    public List<Integer> findAll(E e) {
        List<Integer> list = new ArrayList();
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                list.add(i);
        }
        return list;
    }

    // 删除数组中索引为index的元素,并返回该元素
    public E remove(int index) {
        isIndexLegal("remove", index);
        E removeEle = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        if (size == data.length / 2)
            resize(data.length / 2);
        return removeEle;
    }

    // 删除数组中第一个元素,并返回该元素
    public E removeFirst() {
        return remove(0);
    }

    // 删除数组中最后一个元素,并返回该元素
    public E removeLast() {
        return remove(size - 1);
    }

    // 删除数组中值为e的元素
    public void removeElement(E e) {
        int removeEleIndex = find(e);
        if (removeEleIndex != -1)
            remove(removeEleIndex);
    }

    // 删除数组中值为e的所有元素
    public void removeAllElement(E e) {

        int remEleIdx = find(e);
        while (remEleIdx != -1) {
            remove(remEleIdx);
            remEleIdx = find(e);
        }

    }

    // 判断索引是否合法
    private void isIndexLegal(String methodName, int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException(methodName + " failed, required index > 0 and index < size");
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size = %d, capacity = %d\n", size, data.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1)
                sb.append(", ");
        }
        return sb.append("]").toString();
    }
}
