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

// O(2n), linear space
class Solution {
    public int maxProfit(int[] prices) {
        int[] maxProfits = new int[prices.length];
        
        for(int n = 0; n < 2; n++) { // two transactions
            int position = Integer.MIN_VALUE; // the "net bank balance"
            int profit = 0;
            for(int i = 0; i < prices.length; i++) { // iterate through each price
                // do one of the following:
                //  a.) "buy" here, and take the (profit - price) as a position
                //  b.) keep previous "buy" and profit, and keep position.
                position = Math.max(position, maxProfits[i] - prices[i]);
                
                // ensure profit is never reduced by additional trades, and add
                // consider the profit of selling this position (the "best" position from 0...i) 
                // at this price. (update maxProfits accordingly)
                maxProfits[i] = profit = Math.max(profit, position + prices[i]);
            }
        }
        
        return maxProfits[prices.length - 1];
    }
}

// O(n), constant space
class Solution {
    public int maxProfit(int[] prices) {
        
        int maxProfit = 0;
        int maxProfitAfterOneTransaction = 0;
        int positionBeforeTransaction = Integer.MIN_VALUE;
        int positionAfterTransaction = Integer.MIN_VALUE;
        
        for(int i = 0; i < prices.length; i++) {
            positionBeforeTransaction = Math.max(positionBeforeTransaction, -prices[i]);
            maxProfitAfterOneTransaction = Math.max(maxProfitAfterOneTransaction, 
                                                    positionBeforeTransaction + prices[i]);
            positionAfterTransaction = Math.max(positionAfterTransaction,
                                                maxProfitAfterOneTransaction - prices[i]);
            maxProfit = Math.max(maxProfit, positionAfterTransaction + prices[i]);
        }
        
        return maxProfit;
    }
}
