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
    private final long MOD_CONST = 1_000_000_000 + 7;
    
    public int maxProduct(TreeNode root) {
        ArrayList<Long> subtreeSums = new ArrayList<>();
        long totalSum = getSubtreeSums(root, subtreeSums);
        
        long maxProduct = Integer.MIN_VALUE;
        
        for(long sum : subtreeSums) {
            maxProduct = Math.max(maxProduct, sum * (totalSum - sum));
        }
        
        return (int) (maxProduct % MOD_CONST);
    }
    
    private long getSubtreeSums(TreeNode root, ArrayList<Long> sums) {
        if(root == null) return 0;
        
        long currentSum = root.val + getSubtreeSums(root.left, sums) + getSubtreeSums(root.right, sums);
        sums.add(currentSum);
        return currentSum;
    }
}
