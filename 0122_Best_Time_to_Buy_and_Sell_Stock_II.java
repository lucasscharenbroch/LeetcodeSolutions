class Solution {
    public int maxProfit(int[] prices) {
        int heldPrice = 0;
        boolean holding = false;
        int total = 0;
        
        for(int i = 0; i < prices.length - 1; i++) {
            if(holding && prices[i] > prices[i + 1]) { // local max found: sell
                holding = false;
                total += prices[i] - heldPrice;
            } else if(!holding && prices[i] < prices[i + 1]) { // local min: buy
                holding = true;
                heldPrice = prices[i];
            }
        }
        
        if(holding) {
            total += Math.max(0, prices[prices.length - 1] - heldPrice);
        }
        
        return total;
    }
}
