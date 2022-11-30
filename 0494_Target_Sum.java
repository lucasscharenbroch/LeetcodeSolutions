// Brute-Force Recursion, O(2^n)
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWays(nums, 1, target - nums[0]) + 
               findTargetSumWays(nums, 1, target + nums[0]);
    }
    
    public int findTargetSumWays(int[] nums, int start, int target) {
        if(start == nums.length) return (target == 0) ? 1 : 0;
        return findTargetSumWays(nums, start + 1, target - nums[start]) +
               findTargetSumWays(nums, start + 1, target + nums[start]);
    }
}

// DP, O(40,001 * n) = O(n)
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        final int MAX_ELEMENT = 1000;
        final int MAX_NUMS_LENGTH = nums.length;
        final int MAX_SUM = MAX_ELEMENT * MAX_NUMS_LENGTH;
        
        int[][] dp = new int[nums.length + 1][MAX_SUM * 2 + 1]; // dp[i][j] stores the number of ways
                                                                // to sum to j using nums[0..i]
        // dp is shifted down MAX_SUM (dp[0] ~> -20,000, dp[MAX_SUM] ~> 0, dp[MAX_SUM * 2 + 1] ~> 20,000)
        
        dp[0][MAX_SUM] = 1; // one way to sum to 0.
        
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < MAX_SUM * 2 + 1; j++) {
                if(dp[i][j] != 0) {
                    dp[i + 1][j + nums[i]] += dp[i][j]; // +
                    dp[i + 1][j - nums[i]] += dp[i][j]; // -
                }
            }
        }
        
        return dp[nums.length][target + MAX_SUM];
    }
}
