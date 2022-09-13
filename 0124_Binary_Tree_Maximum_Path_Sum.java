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
    private int max(Integer... args) {
        int max = Integer.MIN_VALUE;
        
        for(Integer arg : args) {
            max = Math.max(arg, max);
        }
        
        return max;
    }
    
    private int maxPathSum(TreeNode root, boolean canSplit) {
        if(root == null) return 0;
        
        int leftNoSplit = maxPathSum(root.left, false);
        int rightNoSplit = maxPathSum(root.right, false);
        
        if(!canSplit) {
            int bestChild = max(leftNoSplit, rightNoSplit); 
            return max(root.val, root.val + bestChild, bestChild);
        }
        
        int leftSplit = maxPathSum(root.left, true);
        int rightSplit = maxPathSum(root.right, true);
        
        int splitHere = root.val + leftNoSplit + rightNoSplit;
        int dontSplitHere = max(leftSplit, rightSplit);
        dontSplitHere += max(0, root.val);
        
        return max(root.val, splitHere, dontSplitHere);
    }
    
    public int maxPathSum(TreeNode root) {
        return maxPathSum(root, true);
    }
}
