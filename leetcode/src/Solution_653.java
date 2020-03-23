import java.util.*;

/**
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 *
 * 案例 1:
 *
 * 输入:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 *
 * 输出: True
 */

public class Solution_653 {
    public boolean findTarget(TreeNode root, int k) {
        Queue<TreeNode> queue = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        if ( root != null )
            queue.add(root);

        while ( !queue.isEmpty() ) {
            TreeNode node = queue.remove();
            list.add(node.val);
            if ( node.left != null )
                queue.add(node.left);
            if ( node.right != null )
                queue.add(node.right);
        }

        for ( int e : list ) {
            if ( set.contains(k-e) )
                return true;
            set.add(e);
        }

        return false;
    }
}
