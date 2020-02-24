import java.util.ArrayList;

/**
 * 基于AVLTree实现的映射
 */
//@SuppressWarnings("all")
public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 获取node节点的高度
    private int getHeight(Node node) {
        if (node == null)
            return 0;
        return node.height;
    }

    // 获取node节点的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null)
            return 0;
        return getHeight(node.left) - getHeight(node.right);
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

    public void add(K key, V value) {
        root = add(root, key, value);
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

        // 1. 更新高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        // 2. 计算平衡因子
        int balanceFactor = getBalanceFactor(node);

//        if (Math.abs(balanceFator) > 1)
//            System.out.println("Balance Fator: " + balanceFator);

        // 3. 维持平衡
        // 有四种情况:
        //      1->(LL) 插入的节点在不平衡节点(node)的左侧的左侧
        //          对node进行右旋转
        //      2->(RR) 插入的节点在不平衡节点(node)的右侧的右侧
        //          对node进行左旋转
        //      3->(LR) 插入的节点在不平衡节点(node)的左侧(node.left)的右侧
        //          先对node.left左旋转,然后再对node进行右旋转
        //      4->(RL) 插入的节点在不平衡节点(node)的右侧(node.right)的左侧
        //          先对node.right右旋转,然后再对node进行左旋转
        // 第一种情况(LL):
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        // 第二种情况(RR):
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        // 第三种情况(LR) : (LR)-->left rotate(LL)-->right rotate
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // 第四种情况(RL) : (RL)-->right rotate(RR)-->left rotate
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // 对节点node进行右旋转, 返回旋转后新的根节点
    private Node rightRotate(Node node) {
        // 1. init
        Node x = node.left;
        Node t = x.right;

        // 2. rotate
        x.right = node;
        node.left = t;

        // 3. update height(先更新node的高度, 再更新x的高度)
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        // 4. 返回新的根节点
        return x;
    }

    // 对节点node进行左旋转, 返回旋转后新的根节点
    private Node leftRotate(Node node) {
        // 1. init
        Node x = node.right;
        Node t = x.left;

        // 2. rotate
        x.left = node;
        node.right = t;

        // 3. update height(先更新node的高度, 再更新x的高度)
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        // 4. 返回新的根节点
        return x;
    }

    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    // 删除根为node, 键为key所在的节点
    // 返回删除节点后新的BST的根
    private Node remove(Node node, K key) {
        if (node == null)
            return null;

        Node retNode;

        if (key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 左右子树都不为空
                // 找到待删除节点的前驱节点(precursor)或者后继节点(successor)顶替该节点,这里选择后继节点
                // precursor: 左子树最大的节点; successor: 右子树最小的节点
                Node successor = minimum(node.right);
                // 下面两句代码的顺序不能颠倒, 不然就不是BST了(没想通是什么原因)
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = null;
                node.right = null;
                retNode = successor;
            }
        }

        if (retNode == null)
            return null;

        // 1. 更新高度
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));
        // 2. 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 3. 维持平衡
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        // 第二种情况(RR):
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        // 第三种情况(LR) : (LR)-->left rotate(LL)-->right rotate
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        // 第四种情况(RL) : (RL)-->right rotate(RR)-->left rotate
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;

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

    // 判断是否是二分搜索树(根据中序遍历元素是否有序来判断)
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);

        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0)
                return false;
        }

        return true;
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null)
            return;

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 判断树是否平衡
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 判断以node为根的树是否平衡
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;

        return isBalanced(node.left) && isBalanced(node.right);
    }
}
