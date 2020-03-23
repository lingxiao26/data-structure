import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 */
class Solution_113 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {

        List<List<Integer>> res = new ArrayList<>();

        if (root == null)
            return res;

        if (root.val == sum && root.left == null && root.right == null) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            res.add(list);
            return res;
        }

        List<List<Integer>> leftL = pathSum(root.left, sum - root.val);
        for ( int i = 0; i < leftL.size(); i ++ ) {
            leftL.get(i).add(root.val);
            res.add(leftL.get(i));
        }

        List<List<Integer>> rightL = pathSum(root.right, sum - root.val);
        for ( int i = 0; i < rightL.size(); i ++ ) {
            rightL.get(i).add(root.val);
            res.add(rightL.get(i));
        }

        for ( int i = 0; i < res.size(); i ++ ) {
            Collections.reverse(res.get(i));
        }

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(3);

        Solution_113 sol = new Solution_113();
        List<List<Integer>> lists = sol.pathSum(root, 1);
        for (List list : lists) {
            System.out.println(list);
        }

//        [1,-2,3]
//        1

    }
}
