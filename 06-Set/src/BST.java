import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
    }

    public BST(E[] arr) {
        for (int i=0; i<arr.length; i++) {
            add(arr[i]);
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    // 在以root为根节点的BST上添加一个节点
    private Node add(Node root, E e) {
        if (root == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(root.e) < 0)
            root.left = add(root.left, e);
        else if (e.compareTo(root.e) > 0)
            root.right = add(root.right, e);

        return root;
    }

    public boolean contains(E e) {
        return contains(root, e);
    }

    // 查询BST中是否包含元素e
    private boolean contains(Node root, E e) {
        if (root == null)
            return false;
        if (root.e.equals(e))
            return true;
        else if (root.e.compareTo(e) > 0)
            return contains(root.left, e);
        else
            return contains(root.right, e);
    }

    // 前序遍历
    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历以root为根节点的BST
    private void preOrder(Node root) {
        if (root == null)
            return;
        System.out.println(root.e);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 前序遍历(非递归)
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node cur = stack.pop();

            System.out.println(cur.e);
            if (cur.right != null)
                stack.push(cur.right);
            if (cur.left != null)
                stack.push(cur.left);
        }
    }

    // 层序遍历
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.e);

            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    public int depth() {
        return depth(root);
    }

    private int depth(Node root) {
        if (root == null)
            return 0;
        int ldepth = depth(root.left);
        int rdepth = depth(root.right);
        return ldepth > rdepth ? ldepth + 1 : rdepth + 1;
    }

    // 删除BST中最小值所在的节点,并返回最小值
    public E removeMin() {
        E min = minimum();
        root = removeMin(root);
        return min;
    }

    // 删除以root为根最小值所在的节点
    // 返回删除节点后新的二分搜索树的根(递归实现)
    private Node removeMin(Node root) {
        // 当递归到节点的左子树为空,递归结束
        if (root.left == null) {
            Node rightNode = root.right;
            root.right = null;
            size--;
            return rightNode;
        }

        root.left = removeMin(root.left);
        return root;
    }

    // 删除BST中最大值所在的节点,并返回最大值
    public E removeMax() {
        E max = maxmum();
        root = removeMax(root);
        return max;
    }

    // 删除BST中以root为根的最大值所在的节点
    // 返回删除节点后新的BST的根(递归实现)
    private Node removeMax(Node root) {
        // 递归结束条件: root的右子树为空
        if (root.right == null) {
            // 当右子树为空时,该逻辑仍然成立
            Node leftNode = root.left;
            root.left = null;
            size--;
            return leftNode;
        }

        root.right = removeMax(root.right);
        return root;
    }

    // 寻找BST中的最小值(递归)
    public E minimum() {
        if (size == 0)
            return null;
        return minimum(root).e;
    }

    // 返回以root为根节点的最小值所在的节点(递归)
    private Node minimum(Node root) {
        if (root.left == null)
            return root;
        return minimum(root.left);
    }

    // 寻找BST中的最大值(递归)
    public E maxmum() {
        if (size == 0)
            return null;
        return maxmum(root).e;
    }

    // 返回以root为根节点的最大值所在的节点(递归)
    private Node maxmum(Node root) {
        if (root.right == null)
            return root;
        return maxmum(root.right);
    }

    // 寻找BST中的最小值(非递归)
    public E minimumNR() {
        if (size == 0)
            return null;
        Node cur = root;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur.e;
    }

    // 寻找BST中的最大值(非递归)
    public E maxmumNR() {
        if (size == 0)
            return null;
        Node cur = root;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur.e;
    }

    // 从二分搜索树中删除值为e的节点
    public void remove(E e) {
        root = remove(root, e);
    }

    // 删除以root为根的BST中值为e的节点(递归实现)
    // 返回删除节点后新的BST的根
    private Node remove(Node root, E e) {
        if (root == null)
            return null;

        if (e.compareTo(root.e) < 0) { // 从左子树删除
            root.left = remove(root.left, e);
            return root;
        } else if (e.compareTo(root.e) > 0) {// 从右子树删除
            root.right = remove(root.right, e);
            return root;
        } else { // e == root.e
            // 1. 左子树为空
            if (root.left == null) {
                Node rightNode = root.right;
                root.right = null;
                size--;
                return rightNode;
            }
            // 2. 右子树为空
            if (root.right == null) {
                Node leftNode = root.left;
                root.left = null;
                size--;
                return leftNode;
            }
            // 3. 左右子树都不为空
            // 3.1 找到比待删除节点大的最小节点,即待删除节点右子树的最小节点(后继节点);也可以找前驱结点
            // 3.2 用这个节点顶替但删除节点的位置
            Node successor = minimum(root.right);
            successor.right = removeMin(root.right);
            successor.left = root.left;
            root.left = null;
            root.right = null;
            return successor;
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        generateBSTString(root, 0, sb);
        return sb.toString();
    }

    // 生成root为根,深度为depth的描述二叉树的字符串
    private void generateBSTString(Node root, int depth, StringBuilder sb) {
        if (root == null) {
            sb.append(generateDepthString(depth) + "null\n");
            return;
        }

        sb.append(generateDepthString(depth)+root.e+"\n");
        generateBSTString(root.left, depth+1, sb);
        generateBSTString(root.right, depth+1, sb);
    }

    private String generateDepthString(int depth) {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<depth; i++)
            sb.append("--");
        return sb.toString();
    }
}
