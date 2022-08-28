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
    private List<TreeNode> generateTrees(List<Integer> nums) {
        List<TreeNode> result = new ArrayList<>();
        if(nums.size() == 0) {
            result.add(null); // (null will be attached to a tree when this call returns)
            return result;
        }
        if(nums.size() == 1) {
            result.add(new TreeNode(nums.get(0)));
            return result;
        } else {
            for(int i = 0; i < nums.size(); i++) { // for each possible head node
                int iValue = nums.remove(i); // remove value at i
                
                List<TreeNode> uniqueLeftTrees = generateTrees(nums.subList(0, i));
                List<TreeNode> uniqueRightTrees = generateTrees(nums.subList(i, nums.size()));
                
                for(TreeNode uniqueLeft : uniqueLeftTrees) {
                    for(TreeNode uniqueRight : uniqueRightTrees) { 
                        result.add(new TreeNode(iValue, uniqueLeft, uniqueRight));
                    }
                }
                    
                nums.add(i, iValue); // re-insert node at i
            }
        }
        return result;
    }
    
    public List<TreeNode> generateTrees(int n) {
        ArrayList<Integer> nAsList = new ArrayList<>(n);
        for(int i = 1; i <= n; i++) nAsList.add(i);
        return generateTrees(nAsList);
    }
}
