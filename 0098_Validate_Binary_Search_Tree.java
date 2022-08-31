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

// traversal with lambda functions for validation
class Solution {
    private boolean isValidBST(TreeNode root, IntPredicate tester) {
        if(root == null) return true;
        if(!tester.test(root.val)) return false;
        
        return isValidBST(root.left, val -> (val < root.val && tester.test(val))) &&
               isValidBST(root.right, val -> (val > root.val && tester.test(val)));
        
    }
    
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, val -> true);
    }
}

// traversal with min and max parameters for validation
class Solution {
    private boolean isValidBST(TreeNode root, long min, long max) {
        if(root == null) return true;
        if(root.val < min || root.val > max) return false;
        
        return isValidBST(root.left, min, (long) root.val - 1) &&
               isValidBST(root.right, (long) root.val + 1, max);
    }
    
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
}
