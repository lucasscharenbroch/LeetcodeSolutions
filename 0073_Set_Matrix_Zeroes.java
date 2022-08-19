class Solution {
    public void setZeroes(int[][] matrix) {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        
        boolean[] rowIsZeroed = new boolean[numRows];
        boolean[] colIsZeroed = new boolean[numCols];
        
        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numCols; c++) {
                if(matrix[r][c] == 0) {
                    rowIsZeroed[r] = true;
                    colIsZeroed[c] = true;
                }
            }
        }
        
        for(int r = 0; r < numRows; r++) {
            for(int c = 0; c < numCols; c++) {
                if(rowIsZeroed[r] || colIsZeroed[c]) matrix[r][c] = 0;
            }
        }
    }
}
