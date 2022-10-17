class Solution {
    private int robRange(int[] nums, int start, int end) {
        if(end - start == 1) return nums[start];
        if(end - start == 2) return Math.max(nums[start], nums[start + 1]);
        
        int prevPrevMax = nums[start];
        int prevMax = Math.max(nums[start], nums[start + 1]);
        
        for(int i = start + 2; i < end; i++) {
            int currentMax = Math.max(prevMax, prevPrevMax + nums[i]);
            
            prevPrevMax = prevMax;
            prevMax = currentMax;
        }
        
        return prevMax;
    }
    
    public int rob(int[] nums) {
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        
        return Math.max(robRange(nums, 0, nums.length - 1),
                        robRange(nums, 1, nums.length));
    }
}
