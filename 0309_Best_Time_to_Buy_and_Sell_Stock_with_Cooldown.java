class Solution {
    public int maxProfit(int[] prices) {
        // for simplicity, treat "buy stock s" as "subtract s's price from profit"
        
        int b = 0;          // b = max profit @ i while being able to buy @ i
        int s = -prices[0]; // s = max profit @ i while being able to sell @ i
        int c = 0;          // c = max profit @ i while being in cooldown @ i
        
        for(int i = 1; i < prices.length; i++) {
            int b1 = Math.max(b, c);
            int s1 = Math.max(b - prices[i], s);
            int c1 = s + prices[i];
            
            b = b1;
            s = s1;
            c = c1;
        }
        
        return Math.max(b, c);
    }
}
