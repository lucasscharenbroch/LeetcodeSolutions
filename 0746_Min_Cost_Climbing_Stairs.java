class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int prevCost = 0; // total cost to arrive at step (i - 2)
        int prevPrevCost = 0; // total cost to arrive at step (i - 1)
        int currentCost = 0; // total cost to arrive at step i
        
        for(int i = 2; i <= cost.length; i++) {
            prevPrevCost = prevCost;
            prevCost = currentCost;
            currentCost = Math.min(prevCost + cost[i - 1], prevPrevCost + cost[i - 2]);
        }
        
        return currentCost;
    }
}
