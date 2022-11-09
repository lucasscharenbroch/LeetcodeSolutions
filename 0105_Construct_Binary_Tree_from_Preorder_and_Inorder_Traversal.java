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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder.length == 0) return null;
        
        int headIndex = 0;
        for(int i = 0; i < inorder.length; i++) {
            if(inorder[i] == preorder[0]) {
                headIndex = i;
                break;
            }
        }
        
        TreeNode leftTree = buildTree(
            Arrays.copyOfRange(preorder, 1, headIndex + 1),
            Arrays.copyOfRange(inorder, 0, headIndex)
        );
        
        TreeNode rightTree = buildTree(
            Arrays.copyOfRange(preorder, headIndex + 1, preorder.length),
            Arrays.copyOfRange(inorder, headIndex + 1, inorder.length)
        );
        
        return new TreeNode(inorder[headIndex], leftTree, rightTree);
    }
}
