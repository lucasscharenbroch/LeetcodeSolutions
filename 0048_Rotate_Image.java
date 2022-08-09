class Solution {
    private void rotateCorrespondingCells(int[][] matrix, int r0, int c0, int n) {
        
        //find destination for matrix[r0][c0] 
        int r = c0;
        int c = n - r0 - 1;
        
        for(int i = 0; i < 3; i++) {
            // swap matrix[r0][c0] and matrix[r][c]
            int temp = matrix[r0][c0];
            matrix[r0][c0] = matrix[r][c];
            matrix[r][c] = temp;
            
            // update r and c to the new matrix[r0][c0]'s destination
            temp = r;
            r = c;
            c = n - temp - 1;
        }
    }
    
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        
        // rotate each layer
        for(int r = 0; r < n / 2; r++) {
            for(int c = r; c < n - r - 1; c++) {
                rotateCorrespondingCells(matrix, r, c, n);
            }
        }
    }
}
