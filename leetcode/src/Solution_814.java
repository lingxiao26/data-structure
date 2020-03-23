public class Solution_814 {
    public TreeNode pruneTree(TreeNode root) {
        if ( root == null )
            return null;

        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);

        if ( root.left == null && root.right == null ) {
            if (root.val == 0)
                return null;
            else
                return root;
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(0);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(1);

        Solution_814 sol = new Solution_814();
        sol.pruneTree(root);
    }
}
