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
    private TreeNode buildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe) {
        if(ie - is <= 0) return null;
        
        // find the index of postorder[pe - 1] in inorder[is : ie]
        int headIndex = is;
        while(headIndex < ie) {
            if(inorder[headIndex] == postorder[pe - 1]) break;
            headIndex++;
        }
        
        return new TreeNode( 
            postorder[pe - 1],
            buildTree(inorder, is, headIndex, postorder, ps, ps + headIndex - is),
            buildTree(inorder, headIndex + 1, ie, postorder, ps + headIndex - is, pe - 1)
        );
    }
    
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }
}
