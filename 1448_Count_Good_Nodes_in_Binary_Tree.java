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
    private int goodNodes(TreeNode root, int max) {
        if(root == null) return 0;
        if(root.val >= max)
            return 1 + goodNodes(root.left, root.val) + goodNodes(root.right, root.val);
        else
            return goodNodes(root.left, max) + goodNodes(root.right, max);
    }
    
    public int goodNodes(TreeNode root) {
        // do dfs with running max.
        return goodNodes(root, Integer.MIN_VALUE);
    }
}
