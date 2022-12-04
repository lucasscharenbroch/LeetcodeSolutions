// Top-Down Memoization, O(n^3)
class Solution {
    public int maxCoins(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];
        return solve(0, nums.length - 1, memo, nums);
    }
    
    // given a range, assuming that is the first range to be burst, returns the max score.
    public int solve(int start, int end, int[][] memo, int[] nums) {
        if(start > end) return 0; // zero-length ranges => 0
        if(memo[start][end] != 0) return memo[start][end];
        
        int leftWall = (start == 0) ? 1 : nums[start - 1]; // balloon val immediately to the left of this range
        int rightWall = (end == nums.length - 1) ? 1 : nums[end + 1]; // balloon val to the right of the range
        
        int score = 0;
        if(start == end) {
            score = nums[start] * leftWall * rightWall;
        } else { // try every candidate for the last balloon to burst (within this range that is burst first)
            for(int k = start; k <= end; k++) { // choose some index, k, to be the last popped in this range.
                int scoreWithK = solve(start, k - 1, memo, nums) + 
                    nums[k] * leftWall * rightWall + 
                    solve(k + 1, end, memo, nums);
                score = Math.max(score, scoreWithK);
            }
        }
            
        memo[start][end] = score;
        return score;
    }
}
