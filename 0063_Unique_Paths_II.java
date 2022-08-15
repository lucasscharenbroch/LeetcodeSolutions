class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // swap each 1 with 0 and each 0 with 1 (so 1s can be used for a sum)
        for(int r = 0; r < obstacleGrid.length; r++) {
            for(int c = 0; c < obstacleGrid[0].length; c++) {
                if(obstacleGrid[r][c] == 0) obstacleGrid[r][c] = 1;
                else obstacleGrid[r][c] = 0;
            }
        }
        
        // iterate over obstacle grid, setting obstacleGrid[r][c] to the # unique paths @ (r, c)
        for(int r = obstacleGrid.length - 1; r >= 0; r--) {
            for(int c = obstacleGrid[0].length - 1; c >= 0; c--) {
                if(obstacleGrid[r][c] == 0) continue;
                else if(r == obstacleGrid.length - 1 && c == obstacleGrid[0].length - 1) continue;
                else if(r == obstacleGrid.length - 1) 
                    obstacleGrid[r][c] = obstacleGrid[r][c + 1] == 0 ? 0 : 1;
                else if(c == obstacleGrid[0].length - 1)
                    obstacleGrid[r][c] = obstacleGrid[r + 1][c] == 0 ? 0 : 1;
                else obstacleGrid[r][c] = obstacleGrid[r + 1][c] + obstacleGrid[r][c + 1];
            } 
        }
        
        return obstacleGrid[0][0];
    }
}
