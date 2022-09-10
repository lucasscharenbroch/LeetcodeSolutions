class Solution {
    public int maxProfit(int[] prices) {
        int startIndex = 0;
        int max = 0;
        
        for(int i = 1; i < prices.length; i++) {
            if(prices[i] < prices[startIndex])
                startIndex = i;
            else
                max = Math.max(prices[i] - prices[startIndex], max);
        }
        
        return max;
    }
}
