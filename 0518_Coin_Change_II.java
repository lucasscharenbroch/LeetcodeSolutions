class Solution {
    public int change(int amount, int[] coins) {
        // numWaysToForm[j] = num ways to form amount j, only using coins 0..=i
        int[] numWaysToForm = new int[amount + 1]; 
        
        numWaysToForm[0] = 1; // one way to form 0 ({})
        
        for(int i = 0; i < coins.length; i++) {
            for(int j = 0; j <= amount; j++) {
                // numWaysToForm[j] contains the number of ways to form j with coins 0..=(i - 1)
                if(j < coins[i]) continue; // coins[i] is too large to form j
                numWaysToForm[j] += numWaysToForm[j - coins[i]]; // add the number of ways to form
                                                                 // j with nums[i]
            }
        }
        
        return numWaysToForm[amount];
    }
}
