// Two Binary-Searches, O(lg(n) + lg(m))
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

// Single-Binary-Search, O(lg(n * m))
class Solution {
    interface IntegerArray {
        int get(int index);
    }
    
    private boolean binarySearch(IntegerArray arr, int size, int target) {
        int left = 0, right = size - 1;
        
        while(left <= right) {
            if(left == right) return arr.get(left) == target;
            
            int mid = (left + right) / 2;
            int midValue = arr.get(mid);
            
            if(midValue == target) return true;
            else if(midValue < target) left = mid + 1;
            else right = mid - 1;
        }
        
        return false;
    }
    
    public boolean searchMatrix(int[][] matrix, int target) {
        // treat the matrix as an 1d integer array
        int m = matrix.length;
        int n = matrix[0].length;
        return binarySearch((i) -> matrix[i / n][i % n], n * m, target);
    }
}
