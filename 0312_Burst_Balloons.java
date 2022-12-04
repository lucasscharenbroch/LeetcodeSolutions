// Top-down Memoization, O(n^3)
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

// Bottom-up DP, O(n^3)
class Solution {
    public int maxCoins(int[] nums) {
        int[][] dp = new int[nums.length][nums.length]; // dp[i][j] = max score on range [i, j]- assume
                                                        // that this range is the first popped.
        
        // if we find the best score for all ranges of length n, 
        // we can use that to find all ranges of (n + 1)
        
        // get ranges of length 1 (score for popping any given balloon first)
        for(int i = 0; i < nums.length; i++) {
            dp[i][i] = ((i == 0) ? 1 : nums[i - 1]) *              // balloon (i - 1) or 1
                       nums[i] *                                   // balloon i
                       ((i == nums.length - 1) ? 1 : nums[i + 1]); // ballon to (i + 1) or 1
        }
        
        for(int n = 2; n <= nums.length; n++) {
            for(int i = 0; i + n <= nums.length; i++) {
                // find max score of the window [i, i + n - 1]
                int score = 0;
                int s = i, e = i + n - 1; // start and end
                
                int leftOuter = (s == 0) ? 1 : nums[s - 1]; // nums[s - 1] or 1
                int rightOuter = (e == nums.length - 1) ? 1 : nums[e + 1]; // nums[e + 1] or 1
                
                for(int k = s; k <= e; k++) { // consider every possible last balloon to pop in the range
                    int scoreWithKAsFirst = nums[k] * leftOuter * rightOuter;
                    if(k == s) scoreWithKAsFirst += dp[s + 1][e];
                    else if(k == e) scoreWithKAsFirst += dp[s][e - 1];
                    else scoreWithKAsFirst += dp[s][k - 1] + dp[k + 1][e];
                    score = Math.max(score, scoreWithKAsFirst);
                }
                    
                dp[s][e] = score;
            }
        }
        
        return dp[0][nums.length - 1];
    }
}
