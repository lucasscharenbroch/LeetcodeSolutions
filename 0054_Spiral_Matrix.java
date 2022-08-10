class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        ArrayList<Integer> result = new ArrayList<>(matrix.length * matrix[0].length);
        
        int top = 0, left = 0, bottom = matrix.length, right = matrix[0].length;
        
        while(top < bottom && left < right) {
            int y = top, x = left - 1;    
            // right
            while(x + 1 < right) result.add(matrix[y][++x]);
            top++;
            
            // down
            while(y + 1 < bottom) result.add(matrix[++y][x]);
            right--;
            
            // left 
            while(x - 1 >= left && top != bottom && left != right) result.add(matrix[y][--x]);
            bottom--;
            
            // up
            while(y - 1 >= top && top != bottom && left != right) result.add(matrix[--y][x]);
            left++;
        }
        
        return result;
    }
}
