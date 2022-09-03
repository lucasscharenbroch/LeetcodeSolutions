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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if(root == null) return result;
        
        LinkedList<Integer> level = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        queue.add(null); // null signifies a new level
        
        for(int i = 0; i < queue.size(); i++) {
            if(queue.get(i) == null) {
                result.add(0, level);
                level = new LinkedList<>();
                if(i != queue.size() - 1) queue.add(null);
            } else {
                level.add(queue.get(i).val);
                if(queue.get(i).left != null) queue.add(queue.get(i).left);
                if(queue.get(i).right != null) queue.add(queue.get(i).right);
            }
        }
        
        return result;
    }
}
