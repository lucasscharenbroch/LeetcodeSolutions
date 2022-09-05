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
    // returns the rightmost node of the flattened tree
    private TreeNode solve(TreeNode root) {
        if(root == null) return null; // edge-case
        if(root.left == null && root.right == null) return root; // leaf is reached
        if(root.left == null) return solve(root.right); // just flatten right
        if(root.right == null) { // flatten left, and put it on the right side
            root.right = root.left;
            root.left = null;
            return solve(root.right);
        }
        
        // both sides != null
        
        solve(root.left).right = root.right; // flatten left, and attach right to the end
        TreeNode rightmostNode = solve(root.right); // flatten right (at the end of left)
        root.right = root.left;
        root.left = null;
        
        return rightmostNode;
    }
    
    public void flatten(TreeNode root) {
        solve(root);
    }
}
