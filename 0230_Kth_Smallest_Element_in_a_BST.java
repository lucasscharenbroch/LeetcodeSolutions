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
    final int NOT_FOUND = Integer.MAX_VALUE;
    int k;
    
    private int kthSmallest(TreeNode root) {
        if(root == null) return NOT_FOUND;
        
        int s; // temp variable for a possible solution
        
        if((s = kthSmallest(root.left)) != NOT_FOUND) return s;
        if(k-- == 1) return root.val;
        if((s = kthSmallest(root.right)) != NOT_FOUND) return s;
        
        return NOT_FOUND;
    }
    
    public int kthSmallest(TreeNode root, int k) {
        this.k = k;
        return kthSmallest(root);
    }
}
