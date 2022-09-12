// O(n^2)
class Solution {
    private int maxProfitWithSingleTrade(int[] prices, int start, int end) {
        int max = 0;
        int buyIndex = start;
        
        for(int i = start + 1; i < end; i++) {
            if(prices[i] < prices[buyIndex]) {
                buyIndex = i;
                continue;
            }
            
            max = Math.max(max, prices[i] - prices[buyIndex]);
        }
        
        return max;
    }
    
    public int maxProfit(int[] prices) {
        int max = 0;
        
        // try partitioning prices at each index
        for(int p = 0; p < prices.length; p++) {
            max = Math.max(max, maxProfitWithSingleTrade(prices, 0, p) +
                                maxProfitWithSingleTrade(prices, p, prices.length));
        }
        
        return max;
    }
}
