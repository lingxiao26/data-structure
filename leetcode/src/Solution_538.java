import com.sun.media.sound.RIFFInvalidDataException;

/**
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，
 * 使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 例如：
 * 输入: 原始二叉搜索树:
 *               5
 *             /   \
 *            2     13
 *
 * 输出: 转换为累加树:
 *              18
 *             /   \
 *           20     13
 */
public class Solution_538 {
    private int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        inOrder(root);
        return root;
    }
    //
    private void inOrder(TreeNode node) {

        if ( node == null )
            return;

        inOrder(node.right);
        node.val += sum;
        sum = node.val;
        inOrder(node.left);

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.right = new TreeNode(13);
        Solution_538 sol = new Solution_538();
        TreeNode ret = sol.convertBST(root);
    }
}
