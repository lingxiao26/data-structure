class Solution_226 {

    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;

        TreeNode aux = root.left;
        root.left = invertTree(root.right);
        root.right = aux;

        return root;
    }

    public TreeNode invertTree1(TreeNode root) {
        if (root == null)
            return null;

        invertTree(root.left);
        invertTree(root.right);

        TreeNode aux = root.left;
        root.left = root.right;
        root.right = aux;

        return root;
    }
}