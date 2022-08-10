class Solution {
    public int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int sum = 0;
        
        for(int n : nums) {
            sum = (sum < 0) ? n : sum + n;    
            max = Math.max(sum, max);
        }
        
        return max;
    }
}
