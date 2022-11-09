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
    public List<Integer> rightSideView(TreeNode root) {
        // bfs the tree, and add the last element of each breadth to the list.
        ArrayList<Integer> result = new ArrayList<>();
        if(root == null) return result; // zero-length edge-case
        
        LinkedList<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        
        // bfs
        while(!queue.isEmpty()) {
            int n = queue.size();
            while(n-- > 0) {
                TreeNode current = queue.poll();
                if(current.left != null) queue.add(current.left);
                if(current.right != null) queue.add(current.right);
                if(n == 0) result.add(current.val); // add rightmost node's value in every breadth
            }
        }
        
        return result;
    }
}
