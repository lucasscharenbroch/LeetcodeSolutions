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
    public List<List<Integer>> levelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
        ArrayList<List<Integer>> result = new ArrayList<>();
        
        TreeNode newLevel = new TreeNode(); // this node signifies that a new level has started
        
        if(root == null) return result;
        
        queue.add(root);
        queue.add(newLevel);
        
        while(queue.peek() != null) {
            ArrayList<Integer> level = new ArrayList<>();
            while(queue.peek() != newLevel) {
                TreeNode first = queue.remove();
                level.add(first.val);
                if(first.left != null) queue.add(first.left);
                if(first.right != null) queue.add(first.right);
            }
            result.add(level);
            
            // queue[0] is newLevel.
            queue.remove(); // remove it
            if(queue.peek() == null) break; // if there are no nodes in the next level, break
            queue.add(newLevel); // add newLevel after the new level of nodes
        }
        
        return result;
    }
}
