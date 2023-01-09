class Solution {
public:
    int maxProfit(int k, vector<int>& prices) {
        k *= 2; // adjust k so it counts buys and sells as transactions.
        vector<int> dp(k + 1, INT_MIN); // dp[j] = max profit on day i after j transactions
                           // At odd indices, stocks are held, and at even indices, stocks are not held.
        
        dp[0] = 0; // 0 profit with 0 transactions
        
        for(int i = 0; i < prices.size(); i++) { // for each day
            for(int j = min(k, i + 1); j > 0; j--) { // for each possible transaction state
                if(j % 2 == 1) dp[j] = max(dp[j], dp[j - 1] - prices[i]); // buy at this day
                else dp[j] = max(dp[j], dp[j - 1] + prices[i]);           // sell at this day
            }
        }
        
        int result = 0;
        for(int i = 0; i <= k; i++) result = max(result, dp[i]);
        return result;
    }
};
