import javafx.util.Pair;

import java.util.*;

/**
 * 给定一个二叉树，返回其按层次遍历的节点值。 （即逐层地，从左到右访问所有节点）。
 * <p>
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * <p>
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * <p>
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class Solution_102 {

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        List<List<Integer>> lists = new LinkedList<>();

        if (root != null)
            queue.add(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            TreeNode node = queue.peek().getKey();
            int level = queue.peek().getValue();
            queue.remove();

            if (level == lists.size())
                lists.add(new LinkedList<>());

            lists.get(level).add(node.val);


            if (node.left != null)
                queue.add(new Pair<>(node.left, level + 1));

            if (node.right != null)
                queue.add(new Pair<>(node.right, level + 1));

        }

        for (int i=1; i<lists.size(); i+=2) {
            Collections.reverse(lists.get(i));
            lists.get(i).get(lists.get(i).size() - 1);
        }

        return lists;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        Solution_102 sol = new Solution_102();
        List<List<Integer>> lists = sol.levelOrder(root);
        for (List list : lists) {
            System.out.println(list);
        }

        

    }
}
