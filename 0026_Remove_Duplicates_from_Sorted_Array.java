class Solution {
    public int removeDuplicates(int[] nums) {
        int numRemoved = 0;    
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == nums[i - 1]) {
                numRemoved++;
                continue;
            }
            nums[i - numRemoved] = nums[i];
        }
        
        return nums.length - numRemoved;
    }
}
