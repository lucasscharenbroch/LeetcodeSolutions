class Solution {
    public int removeElement(int[] nums, int val) {
        int numRemoved = 0;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == val) {
                numRemoved++;
                continue;
            }
            nums[i - numRemoved] = nums[i];
        }
        
        return nums.length - numRemoved;
    }
}
