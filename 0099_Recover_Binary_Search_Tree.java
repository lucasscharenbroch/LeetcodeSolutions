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

// recursive min and max finding
class Solution {
    private TreeNode maxNode(TreeNode root) {
        if(root == null) return null;
        
        TreeNode leftMax = maxNode(root.left);
        TreeNode rightMax = maxNode(root.right);
        
        if((leftMax == null || root.val > leftMax.val) && 
           (rightMax == null || root.val  > rightMax.val)) { // both sides are smaller or null
            return root;
        } else { // one side has a larger value, return that node
            if(leftMax == null) return rightMax;
            if(rightMax == null) return leftMax;
            if(leftMax.val > rightMax.val) return leftMax;
            else return rightMax;
        }
    }
    
    private TreeNode minNode(TreeNode root) {
        if(root == null) return null;
        
        TreeNode leftMin = minNode(root.left);
        TreeNode rightMin = minNode(root.right);
        
        if((leftMin == null || root.val < leftMin.val) && 
           (rightMin == null || root.val  < rightMin.val)) { // both sides are larger or null
            return root;
        } else { // one side has a smaller value, return that node
            if(leftMin == null) return rightMin;
            if(rightMin == null) return leftMin;
            if(leftMin.val < rightMin.val) return leftMin;
            else return rightMin;
        }
    }
    
    private void swapValues(TreeNode a, TreeNode b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
    
    public void recoverTree(TreeNode root) {
        if(root == null) return;
        
        TreeNode leftMax = maxNode(root.left);
        TreeNode rightMin = minNode(root.right);
        
        if(leftMax != null && rightMin != null && leftMax.val > root.val && rightMin.val < root.val) {
            swapValues(leftMax, rightMin); // leftMax and rightMin were swapped
            return;
        } else if(leftMax != null && leftMax.val > root.val) {
            // leftMax and root were swapped.
            swapValues(leftMax, root);
            return;
        } else if(rightMin != null && rightMin.val < root.val) {
            // rightMin and root were swapped
            swapValues(rightMin, root);
            return;
        } else {
            // root must be correct, recursively find the incorrect node
            recoverTree(root.left);
            recoverTree(root.right);
        }
    }
}
