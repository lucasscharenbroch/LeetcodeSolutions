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
    private TreeNode sortedArrayToBST(int[] nums, int start, int end) {
        if(start == end) return null;
        int mid = (start + end) / 2; // set mid = new head
        // recursively make BSTs of both sides 
        return new TreeNode(nums[mid], sortedArrayToBST(nums, start, mid), sortedArrayToBST(nums, mid + 1, end));
    }
    
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }
}
