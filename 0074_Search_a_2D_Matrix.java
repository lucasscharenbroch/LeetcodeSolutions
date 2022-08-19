class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // binary search for the row containing target
        int top = 0;
        int bottom = matrix.length;
        while(top < bottom - 1) {
            int mid = (top + bottom ) / 2;
            if(matrix[mid][0] == target) return true;
            else if(matrix[mid][0] > target) bottom = mid;
            else top = mid;
        }
        
        if(top >= matrix.length) return false;
        
        // binary search for column containing target
        int left = 0;
        int right = matrix[top].length;
        while(left < right) {
            int mid = (left + right) / 2;
            if(matrix[top][mid] == target) return true;
            else if(matrix[top][mid] > target) right = mid;
            else left = mid + 1;
        }
        
        return left < matrix[top].length && matrix[top][left] == target;
    }
}
