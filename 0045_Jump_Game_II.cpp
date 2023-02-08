class Solution {
public:
    int jump(vector<int>& nums) {
        constexpr int MAX_LEN = 10'000;
        int dp[MAX_LEN]; // dp[i] holds the minimum number of steps to reach the last element
        
        dp[nums.size() - 1] = 0;
        
        for(int i = nums.size() - 2; i >= 0; i--) {
            // calculate dp[i]
            int best_jump = MAX_LEN + 1; // minimum number of moves to get to last elem after one jump
            for(int j = i + 1; j < nums.size() && j <= i + nums[i]; j++) 
                best_jump = min(best_jump, dp[j]);
            dp[i] = best_jump + 1;
        }
        
        return dp[0];
    }
};
