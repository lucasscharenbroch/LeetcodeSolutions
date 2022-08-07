class Solution {
    public int firstMissingPositive(int[] nums) {
        // place every number into the "correct position" (nums[i] = i + 1)
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == i + 1 || // nums[i] is in the correct position
               nums[i] > nums.length || nums[i] < 1 || // nums[i] is an invalid number (too big/small)
               nums[nums[i] - 1] == nums[i]) // nums[i] already has a duplicate in the correct position 
                continue;
            // swap nums[i] into the correct position, and handle the number previously in that position
            int temp = nums[nums[i] - 1];
            nums[nums[i] - 1] = nums[i];
            nums[i--] = temp; // decrement i to handle nums[i] again
        }    
        
        // find the first number in an incorrect position
        int i;
        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) break; // there is no (i + 1)
        }
        return i + 1;
    }
}
