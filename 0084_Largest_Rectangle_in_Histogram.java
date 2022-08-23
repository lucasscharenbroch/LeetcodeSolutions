// recursive solution (best case: O(nlgn), worst case: O(n^2)
class Solution {
    private int solve(int[] heights, int start, int end) {
        if(end <= start) return 0; // base case (0-length array)
        
        // find minimum value and its index
        int minVal = heights[start];
        int minIndex = start;
        
        for(int i = start + 1; i < end; i++) {
            if(heights[i] < minVal) {
                minVal = heights[i];
                minIndex = i;
            }
        }
        
        // the solution is one of the following:
        // 1.) it includes minIndex (therefore spans the entire array)
        // 2.) it does not include min index
        //     a) it is to the left of min index 
        //     b.) it is to the right
        // recursively find 2a, and 2b, then return the max of 1, 2a, and 2b
        return Math.max(minVal * (end - start), Math.max(solve(heights, start, minIndex),
                                                         solve(heights, minIndex + 1, end)));
    }
    
    public int largestRectangleArea(int[] heights) {
        return solve(heights, 0, heights.length);
    }
}
