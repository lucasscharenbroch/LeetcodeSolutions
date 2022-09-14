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
    private int maxSum = Integer.MIN_VALUE;
    
    private int findMax(TreeNode root) {
        if(root == null) return 0; 
        
        // "splitting" => using root & both children in path
        // "not splitting" => using root and only one child in path
        // there can only be one split per path, so only the noSplitMax is returned
        
        int leftMax = findMax(root.left);
        int rightMax = findMax(root.right);
        int noSplitMax = Math.max(root.val, root.val + Math.max(leftMax, rightMax));
        int splitMax = leftMax + root.val + rightMax;
        
        maxSum = Math.max(maxSum, Math.max(noSplitMax, splitMax));
        
        return noSplitMax;
    }
    
    public int maxPathSum(TreeNode root) {
        findMax(root);
        return maxSum;
    }
}
