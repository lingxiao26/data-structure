import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 *
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 */
class Solution_104 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int maxDepth(TreeNode root) {

        if (root == null)
            return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Integer.max(leftDepth, rightDepth) + 1;
    }

    public int maxDepthNR(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        int ldepth = 0;
        int rdepth = 0;

        if (root != null)
            queue.add(root);

        while (!queue.isEmpty()) {
            queue.remove();

            if (root.left != null) {
                queue.add(root.left);
                ldepth ++;
            }

            if (root.right != null) {
                queue.add(root.right);
                rdepth ++;
            }
        }

        return Math.max(ldepth, rdepth);

    }

}