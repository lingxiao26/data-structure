import com.sun.deploy.util.BlackList;

/**
 * 基于二分搜索树实现的映射
 */
public class RBTree<K extends Comparable<K>, V> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            color = RED;
        }
    }

    private Node root;
    private int size;

    public RBTree() {
        root = null;
        size = 0;
    }

    private boolean isRed(Node node) {
        if (node == null)
            return BLACK;
        return node.color;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 返回根为root, 键为key所在的节点
    private Node getNode(Node root, K key) {
        if (root == null)
            return null;

        if (root.key.equals(key))
            return root;
        else if (root.key.compareTo(key) > 0)
            return getNode(root.left, key);
        else
            return getNode(root.right, key);
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    //   node                     x
    //  /   \     左旋转         /  \
    // T1   x   --------->   node   T3
    //     / \              /   \
    //    T2 T3            T1   T2
    private Node leftRotate(Node node) {
        Node x = node.right;

        // rotate
        node.right = x.left;
        x.left = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private Node rightRotate(Node node) {
        Node x = node.left;

        node.left = x.right;
        x.right = node;

        x.color = node.color;
        node.color = RED;

        return x;
    }

    private void flipColors(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        root.color = BLACK; // 最终根节点的颜色为黑色
    }

    // 向以root为根的BST中插入元素(key, value)
    // 返回插入新节点后的BST
    private Node add(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            // 往左子树中添加
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            // 往右子树中添加
            node.right = add(node.right, key, value);
        } else { // root.key = key
            node.value = value;
        }

        if (isRed(node.right) && !isRed(node.left))
            node = leftRotate(node);
        if (isRed(node.left) && isRed(node.left.left))
            node = rightRotate(node);
        if (isRed(node.left) && isRed(node.right))
            flipColors(node);

        return node;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // 删除根为root, 键为key所在的节点
    // 返回删除节点后新的BST的根
    private Node remove(Node root, K key) {
        if (root == null)
            return null;

        if (key.compareTo(root.key) < 0) {
            root.left = remove(root.left, key);
            return root;
        } else if (key.compareTo(root.key) > 0) {
            root.right = remove(root.right, key);
            return root;
        } else {
            if (root.left == null) {
                Node rightNode = root.right;
                root.right = null;
                size--;
                return rightNode;
            }

            if (root.right == null) {
                Node leftNode = root.left;
                root.left = null;
                size--;
                return leftNode;
            }

            // 左右子树都不为空
            // 找到待删除节点的前驱节点(precursor)或者后继节点(successor)顶替该节点,这里选择后继节点
            // precursor: 左子树最大的节点; successor: 右子树最小的节点
            Node successor = minimum(root.right);
            successor.left = root.left;
            successor.right = removeMin(root.right);
            root.left = null;
            root.right = null;
            return successor;
        }
    }

    // 返回root为根的BST中最小值所在的节点
    private Node minimum(Node root) {
        if (root == null)
            return null;
        if (root.left == null)
            return root;
        return minimum(root.left);
    }

    // 删除以root为根的最小值所在的节点
    // 返回删除节点后新的BST的根
    private Node removeMin(Node root) {
        if (root.left == null) {
            Node rightNode = root.right;
            root.right = null;
            size--;
            return rightNode;
        }

        root.left = removeMin(root.left);
        return root;
    }

    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException("set failed, " + key + " not exist");
        node.value = value;
    }
}
