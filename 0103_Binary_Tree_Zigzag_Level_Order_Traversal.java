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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        ArrayList<List<Integer>> result = new ArrayList<>();
        boolean isReversed = false;
        
        if(root ==  null) return result;
        
        queue.add(root);
        queue.add(null); // null signifies the end of a level
        
        LinkedList<Integer> level = new LinkedList<>();
        
        while(queue.size() > 1) {
            TreeNode front = queue.remove(0);
            if(front == null) { // level is finished
                result.add(level);
                level = new LinkedList();
                isReversed = !isReversed;
                queue.add(null);
            }  else {
                if(front.left != null) queue.add(front.left);
                if(front.right != null) queue.add(front.right);
                if(isReversed) level.add(0, front.val);
                else level.add(front.val);
            }
        }
        result.add(level);
        
        return result;
    }
}
