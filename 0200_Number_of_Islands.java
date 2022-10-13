class Solution {
    private void dfs(char[][] grid, int r, int c) {
        if(r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) return; // out-of-bounds
        if(grid[r][c] == '0') return; // water reached
        
        // (r, c) is part of this island
        grid[r][c] = '0'; // set this cell to water so it isn't counted again.
        dfs(grid, r + 1, c);
        dfs(grid, r - 1, c);
        dfs(grid, r, c + 1);
        dfs(grid, r, c - 1);
    }
    
    public int numIslands(char[][] grid) {
        int count = 0;
        
        for(int r = 0; r < grid.length; r++) {
            for(int c= 0; c < grid[r].length; c++) {
                if(grid[r][c] == '1') {
                    count++;
                    dfs(grid, r, c);
                }
            }
        }
        
        return count;
    }
}
