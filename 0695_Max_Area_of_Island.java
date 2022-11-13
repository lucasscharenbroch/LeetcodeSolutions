class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int maxArea = 0;
        for(int r = 0; r < grid.length; r++) {
            for(int c = 0; c < grid[0].length; c++) {
                maxArea = Math.max(maxArea, dfs(grid, r, c));
            }
        }
        
        return maxArea;
    }
    
    // returns the area island @ (r, c), and sets all 1s to 0s to they won't be re-counted.
    int dfs(int[][] grid, int r, int c) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length || grid[r][c] == 0) return 0;
        grid[r][c] = 0;
        return 1 + dfs(grid, r + 1, c) + dfs(grid, r - 1, c) + dfs(grid, r, c + 1) + dfs(grid, r, c - 1);
    }
}
