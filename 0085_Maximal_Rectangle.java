class Solution {
    // recursively expands the rectangle downward (as allowed by 1s in the matrix), and returns the max area found.
    private int findMaxRectangle(char[][] matrix, int r, int startCol, int endCol, int currentHeight) {
        int maxArea = currentHeight * (endCol - startCol);
        if(r == matrix.length) return maxArea;
        
        for(int c = startCol; c < endCol; c++) {
            if(matrix[r][c] == '0') continue;
            
            int start = c;
            while(matrix[r][c] == '1' && ++c < endCol); // advance c until it hits a 0 or endCol
            
            maxArea = Math.max(maxArea, findMaxRectangle(matrix, r + 1, start, c--, currentHeight + 1));
        }
        
        return maxArea;
    }
    
    public int maximalRectangle(char[][] matrix) {
        int maxArea = 0;
        for(int r = 0; r < matrix.length; r++) { // for each row, find the max-area rect that starts in it 
            maxArea = Math.max(maxArea, findMaxRectangle(matrix, r, 0, matrix[0].length, 0));
        }
        return maxArea;
    }
}
