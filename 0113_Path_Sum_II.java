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
    private void findPaths(TreeNode root, int targetSum, int numVisited) {
        if(root == null) return; // empty tree edge-case
        
        visited[numVisited++] = root.val;
        targetSum -= root.val;
        
        if(root.left == null && root.right == null) { // root is a leaf
            if(targetSum == 0) { // targetSum is reached
                // add visited[0 : numVisited] to paths
                ArrayList<Integer> path = new ArrayList<>(numVisited);
                for(int v = 0; v < numVisited; v++) path.add(visited[v]);
                paths.add(path);
            }
        } else {
            findPaths(root.left, targetSum, numVisited);
            findPaths(root.right, targetSum, numVisited);
        }
    }
    
    private final int MAX_NODES = 5000;
    private int[] visited = new int[MAX_NODES];
    private ArrayList<List<Integer>> paths = new ArrayList<>();
    
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        findPaths(root, targetSum, 0);
        return paths;
    }
}
