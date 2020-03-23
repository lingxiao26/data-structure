import java.util.ArrayList;
import java.util.List;

class Solution_257 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    // 给定一个二叉树，返回所有从根节点到叶子节点的路径。
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root == null)
            return list;

        // 递归结束条件
        if (root.left == null && root.right == null){
            list.add(root.val + "");
            return list;
        }

        List<String> leftS = binaryTreePaths(root.left);
        for (int i=0; i<leftS.size(); i++)
            list.add(root.val + "->" + leftS.get(i));

        List<String> rightS = binaryTreePaths(root.right);
        for (int i=0; i<rightS.size(); i++)
            list.add(root.val + "->" + rightS.get(i));

        return list;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);

        Solution_257 sol = new Solution_257();
        List<String> list = sol.binaryTreePaths(root);
        System.out.println(list);
    }
}