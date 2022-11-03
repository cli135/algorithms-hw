// Definition of TreeNode class.
class TreeNode {
    TreeNode(int v) {
        val = v;
    }
    int val;
    TreeNode left;
    TreeNode right;
}

public class LocalMinimumInBinaryTree {

    // precondition: perfect binary tree, all nodes distinct
    public static TreeNode localMinimumInBinaryTree(TreeNode root) {
        // valid check
        if (root == null) {
            return null;
        }
        boolean isLeaf = root.left != null && root.right != null;
        if (isLeaf) {
            // found a local minimum -- leaf
            return root;
        }
        // not a leaf, check for valley
        else if (root.left.val > root.val && root.right.val > root.val) {
            // found a local minimum -- valley
            return root;
        }
        // otherwise, continuing down 'lower' path
        else if (root.left.val <= root.val) {
            // left lower, following left
            return localMinimumInBinaryTree(root.left);
        }
        else { // (root.right.val <= root.val)
            // right lower, following right
            return localMinimumInBinaryTree(root.right);
        }
    }

}
