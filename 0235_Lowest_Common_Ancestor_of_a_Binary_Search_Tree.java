/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// recursive
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == p || root == q) return root;
        if(p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if(p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}

// iterative
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val) { // swap (p and q) so p has the smallest value
            TreeNode temp = p;
            p = q;
            q = temp;
        }
        
        while(root != p && root != q) {
            if(p.val < root.val && root.val < q.val) break; // found LCA
            else if(root.val < p.val) { // p and q are both larger than root
                root = root.right;
            } else root = root.left; // p and q are both smaller than root
        }
        
        return root;
    }
}
