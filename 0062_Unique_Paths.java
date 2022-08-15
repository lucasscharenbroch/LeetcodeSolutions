class Solution {
    public int uniquePaths(int m, int n) {
        int[][] pathsPerCell = new int[m][n];
        
        // fill rightmost column with 1s
        for(int r = 0; r < m; r++) {
            pathsPerCell[r][n - 1] = 1;
        }
        
        // fill bottom row with 1s
        for(int c = 0; c < n; c++) {
            pathsPerCell[m - 1][c] = 1;
        }
        
        // populate pathsPerCell by setting each cell to
        // the sum of (the cell below and the cell to the right)
        for(int r = m - 2; r >= 0; r--) {
            for(int c = n - 2; c >= 0; c--) {
                pathsPerCell[r][c] = pathsPerCell[r + 1][c] + pathsPerCell[r][c + 1];
            }
        }
        
        return pathsPerCell[0][0]; // return the # of unique paths starting from (0, 0)
    }
}
