/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private TreeNode invertBinaryTree(TreeNode root) {
        if(root == null) return null;
        
        TreeNode temp = root.left;
        root.left = invertBinaryTree(root.right);
        root.right = invertBinaryTree(temp);
        
        return root;
    }
    
    private boolean sameTree(TreeNode a, TreeNode b) {
        if(a == null || b == null) return a == b;
        return a.val == b.val && sameTree(a.left, b.left) && sameTree(a.right, b.right);
    }
    
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return sameTree(root.left, invertBinaryTree(root.right));
    }
}
