class Solution {
public:
    int numSquares(int n) {
        vector<int> dp(n + 1); // dp[i] = solution for n = i
        
        for(int i = 1; i <= n; i++) {
            dp[i] = INT_MAX;
            
            for(int j = 1; i >= j * j; j++) {
                dp[i] = min(dp[i], 1 + dp[i - (j * j)]);
            }
        }
        
        return dp[n];
    }
};
