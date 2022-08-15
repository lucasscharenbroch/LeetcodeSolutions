class Solution {
    public int minPathSum(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;
        
        // iterate from bottom-right to top-left, setting each cell to its minimum path sum
        for(int r = n - 1; r >= 0; r--) {
            for(int c = m - 1; c >= 0; c--) {
                if(r == n - 1 && c == m - 1) continue; // skip bottom-right cell
                else if(r == n - 1) grid[r][c] += grid[r][c + 1];
                else if(c == m - 1) grid[r][c] += grid[r + 1][c];
                else grid[r][c] += Math.min(grid[r][c + 1], grid[r + 1][c]);
            }
        }
        
        return grid[0][0]; // return the min path sum starting at (0, 0)
    }
}
