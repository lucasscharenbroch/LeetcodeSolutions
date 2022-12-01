class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int[][] memo = new int[matrix.length][matrix[0].length]; // memo[i][j] = longest path starting @ (i, j)
        
        int longestPath = 0;
        
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length; j++) {
                longestPath = Math.max(longestPath, findLongestPath(matrix, i, j, memo));
            }
        }
        
        return longestPath;
    }
    
    final int[][] offsets = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    // recursively explores all possible increasing paths
    private int findLongestPath(int[][] matrix, int i, int j, int[][] memo) {
        if(memo[i][j] != 0) return memo[i][j];
        
        int longest = 1;
        for(int[] offset : offsets) {
            int i1 = i + offset[0];
            int j1 = j + offset[1];
            if(i1 < 0 || i1 >= matrix.length || j1 < 0 || j1 >= matrix[i].length) continue; // ensure in-bounds
            
            if(matrix[i1][j1] > matrix[i][j]) {
                longest = Math.max(longest, 1 + findLongestPath(matrix, i1, j1, memo));   
            }
        }
        
        memo[i][j] = longest;
        return longest;
    }
}
