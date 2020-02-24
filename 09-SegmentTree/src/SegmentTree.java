import java.util.Arrays;

/**
 * 线段树是一棵平衡二叉树
 * @param <E>
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] data, Merger<E> merger) {
        this.merger = merger;
        this.data = Arrays.copyOf(data, data.length);
        // 当data的长度刚好为n(=2^k)的时候, 只需要开辟2n的空间
        // 但是当长度n不为2^k的时候, 需要开辟4n的空间
        tree = (E[]) new Object[data.length * 4];
        buildSegmentTree(0, 0, data.length - 1);
    }


    /**
     * 根据data数组, 构建一颗以treeIndex为根, data区间为[l, r]的线段树(递归实现)
     *
     * @param treeIndex 线段树根节点的索引
     * @param l         需要构建成线段树的data数组的左边界(闭区间)
     * @param r         需要构建成线段树的data数组的右边界(闭区间)
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) { // 递归到底(线段树中的区间长度为1) tree[treeIndex]=data[r]/data[l];
            tree[treeIndex] = data[l];
            return;
        }

        int leftChildIndex = treeIndex * 2 + 1;
        int rightChildIndex = treeIndex * 2 + 2;

        int mid = l + (r - l) / 2;

        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    /**
     * 区间查询
     *
     * @param ql query left  待查询数据区间的左边界
     * @param qr query right 待查询数据区间的右边界
     * @return [ql, qr]区间的数据
     */
    public E query(int ql, int qr) {
        if (ql < 0 || qr > data.length - 1 || ql > qr)
            throw new IllegalArgumentException("ql qr invalid");
        // 每次查询都从线段树的根节点开始, 根节点的索引为0, 存储的区间为[0, data.length-1]
        return query(0, 0, data.length - 1, ql, qr);
    }

    /**
     * @param treeIndex 根节点的索引
     * @param l         线段树中treeIndex索引处存储的数据的区间的左边界
     * @param r         线段树中treeIndex索引处存储的数据的区间的左边界
     * @param ql        用户传入的查询区间的左边界
     * @param qr        用户传入的查询区间的左边界
     * @return 返回[ql, qr]区间的数据
     */
    private E query(int treeIndex, int l, int r, int ql, int qr) {
        // 查询的区间(Query Region)分四种情况

        // 1. 查询的区间刚好跟线段树当前节点存放数据的区间重合
        // [ql, qr] == [l, r]
        if (ql == l && qr == r)
            return tree[treeIndex];

        int leftChildIdx = treeIndex * 2 + 1;
        int rightChildIdx = treeIndex * 2 + 2;

        int mid = l + (r - l) / 2;
        // 2. 查询的区间落在线段树当前节点的左子树存放数据的区间
        //  [l <= [ql, qr] <= mid]
        if (qr <= mid) {
            return query(leftChildIdx, l, mid, ql, qr);
        }
        // 3. 查询的区间落在线段树当前节点的右子树存放数据的区间
        //  [mid+1 <= [ql, qr] <= r]
        else if (ql >= mid + 1) {
            return query(rightChildIdx, mid + 1, r, ql, qr);
        }
        // 4. 查询的区间一部分在线段树当前节点的右子树,一部分在左子树
        //  [ l < ql <= mid < mid+1 <= qr < r]
        else {
            E leftData = query(leftChildIdx, l, mid, ql, mid);
            E rightData = query(rightChildIdx, mid + 1, r, mid + 1, qr);
            return merger.merge(leftData, rightData);
        }
    }

    public void set(int index, E e) {
        if (index < 0 || index > data.length - 1)
            throw new IllegalArgumentException("index invalid");
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            tree[treeIndex] = e;
            return;
        }

        int mid = l + (r - l) / 2;
        int leftChildIdx = treeIndex * 2 + 1;
        int rightChildIdx = treeIndex * 2 + 2;

        if (index >= mid + 1) {
            set(rightChildIdx, mid + 1, r, index, e);
        } else {
            set(leftChildIdx, l, mid, index, e);
        }

        tree[treeIndex] = merger.merge(tree[leftChildIdx], tree[rightChildIdx]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                sb.append(tree[i]);
            else
                sb.append("null");
            if (i != tree.length - 1)
                sb.append(", ");
        }

        return sb.toString();
    }
}
