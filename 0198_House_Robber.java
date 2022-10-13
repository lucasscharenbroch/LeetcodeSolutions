class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        int lastLastMax = nums[0];                // max of robbing the range [0..i-2]
        int lastMax = Math.max(nums[0], nums[1]); // max of robbing the range [0..i-1]
        
        for(int i = 2; i < nums.length; i++) {
            int currentMax = Math.max(lastLastMax + nums[i], lastMax);
            
            lastLastMax = lastMax;
            lastMax = currentMax;
        }
        
        return lastMax;
    }
}
