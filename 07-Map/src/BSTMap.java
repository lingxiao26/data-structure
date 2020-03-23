/**
 * 基于二分搜索树实现的映射
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
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

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向以root为根的BST中插入元素(key, value)
    // 返回插入新节点后的BST
    private Node add(Node root, K key, V value) {
        if (root == null) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(root.key) < 0) {
            // 往左子树中添加
            root.left = add(root.left, key, value);
        } else if (key.compareTo(root.key) > 0) {
            // 往右子树中添加
            root.right = add(root.right, key, value);
        } else { // root.key = key
            root.value = value;
        }

        return root;
    }

    @Override
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

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node == null)
            throw new IllegalArgumentException("set failed, " + key + " not exist");
        node.value = value;
        
    }
}
