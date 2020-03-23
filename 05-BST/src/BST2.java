@SuppressWarnings("all")
public class BST2 {

    private class Node {
        public Comparable e;
        public Node left, right;

        public Node(Comparable e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST2() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Comparable e) {
        root = add(root, e);
    }

    // 向以node为根的BST中添加节点, 返回添加节点后新的根
    private Node add(Node node, Comparable e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    public boolean contains(Comparable e) {
        return contains(root, e);
    }

    private boolean contains(Node node, Comparable e) {
        if (node == null)
            return false;

        if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 返回bst中最小的元素
    public Comparable min() {
        if (size == 0)
            throw new IllegalArgumentException("bst is empty");
        return min(root).e;
    }

    private Node min(Node node) {
        if (node.left == null)
            return node;

        return min(node.left);
    }

    public Comparable max() {
        if (size == 0)
            throw new IllegalArgumentException("bst is empty");
        return max(root).e;
    }

    private Node max(Node node) {
        if (node.right == null)
            return node;

        return max(node.right);
    }

    // remove minimum element from bst
    public Comparable removeMin() {
        Comparable e = min();
        removeMin(root);
        // root = removeMin(root);
        return e;
    }

    // remove minimum node from bst, return new root of bst.
    private Node removeMin(Node node) {

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public Comparable removeMax() {
        Comparable e = max();

        removeMax(root);

        return e;
    }

    // remove maximum node from bst, return new root of bst.
    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax(node.right);

        return node;
    }

    public void remove(Comparable e) {
        if (contains(e))
            remove(root, e);
    }

    // 删除元素e所在的节点, 返回删除节点后新的根节点
    private Node remove(Node node, Comparable e) {
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { // e == node.e
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = min(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    // 返回比元素e小的元素中最大的那个(e不一定存在bst中)
    public Comparable floor(Comparable e) {
        if (size == 0)
            throw new IllegalArgumentException("bst is empty");
        return floor(root, e).e;
    }


    // 返回比元素e小的元素中最大的那个元素所在的节点
    private Node floor(Node node, Comparable e) {

        if (node == null)
            return null;

        if (e.compareTo(node.e) < 0)
            return floor(node.left, e);
        if (e.compareTo(node.e) > 0) {
            Node ret = floor(node.right, e);
            if (ret != null) {
                return ret;
            } else
                return node;
        }

        return node;
    }

    // 返回比元素e大的元素中最小的那个(e不一定存在bst中)
    public Comparable ceil(Comparable e) {
        if (size == 0)
            throw new IllegalArgumentException("bst is empty");
        return ceil(root, e).e;
    }

    // 返回比元素e大的元素中最小的那个元素所在的节点(e不一定存在bst中)
    private Node ceil(Node node, Comparable e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) > 0)
            return ceil(node.right, e);
        else if (e.compareTo(node.e) < 0) {
            Node ret = ceil(node.left, e);
            if ( ret != null)
                return ret;
            else
                return node;
        }

        return node;
    }

    // 返回比元素e大的元素中最小的那个(e一定存在bst中)
    // 后继节点, 即e所在节点右子树中最小的那个
    public Comparable successor(Comparable e) {
        Node node = find(e);
        if (node == null)
            throw new IllegalArgumentException("e is not exist.");
        return min(node.right).e;
    }

    // 返回比元素e小的元素中最大的那个(e一定存在bst中)
    // 前驱节点, 即e所在节点左子树中最大的那个
    public Comparable precursor(Comparable e) {
        Node node = find(e);
        if (node == null)
            throw new IllegalArgumentException("e is not exist.");
        return max(node.left).e;
    }

    // 返回元素e所在的节点
    private Node find(Comparable e) {
        return find(root, e);
    }

    private Node find(Node node, Comparable e) {
        if (node == null)
            return null;

        if (e.compareTo(node.e) == 0)
            return node;
        else if (e.compareTo(node.e) < 0)
            return find(node.left, e);
        else
            return find(node.right, e);

    }

}
