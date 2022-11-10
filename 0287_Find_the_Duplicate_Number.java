class Solution {
    public int findDuplicate(int[] nums) {
        // treat element values as pointers to other indices, find the beginning
        // of the cycle with Floyd's cycle-finding algorithm.
        
        int slow = 0, fast = 0;
        
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while(slow != fast);
        
        int slow2 = 0;
        
        do {
            slow = nums[slow];
            slow2 = nums[slow2];
        } while(slow != slow2);
        
        return slow2;
    }
}
