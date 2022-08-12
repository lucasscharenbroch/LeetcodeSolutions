class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        int top = 0, left = 0, bottom = n, right = n;
        int r, c; // keeps track of current row and col
        int i = 1;
        int nsquared = n * n;
        
        while(i <= nsquared) {
            c = left;
            r = top;
            
            // right 
            while(c < right) matrix[r][c++] = i++;
            c--; r++; top++;
            
            // down
            while(i <= nsquared && r < bottom) matrix[r++][c] = i++;
            r--; c--; right--;
                
            // left
            while(i <= nsquared && c >= left) matrix[r][c--] = i++; 
            c++; bottom--; r--;
            
            // up
            while(i <= nsquared && r >= top) matrix[r--][c] = i++;
            r++; left++; c++;
        }
        
        return matrix;
    }
}
