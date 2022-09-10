class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        // iterate through the triangle, bottom-to-top, setting each element
        // to its respective path sum
        for(int r = triangle.size() - 2; r >= 0; r--) {
            for(int c = 0; c < r + 1; c++) {
                int currentPathSum = triangle.get(r).get(c) +     // the path sum must include the element itself
                        Math.min(triangle.get(r + 1).get(c),      // and either triangle[r + 1][c]
                                 triangle.get(r + 1).get(c + 1)); // or         triangle[r + 1][c + 1]
                triangle.get(r).set(c, currentPathSum);
            }
        }
        
        return triangle.get(0).get(0);
    }
}
