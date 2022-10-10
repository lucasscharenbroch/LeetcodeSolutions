class Solution {
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        int min = Math.min(nums[start], nums[end]);
        
        while(start < end) {
            if(nums[start] < nums[end]) break; // start -> end is decreasing
            int mid = (start + end) / 2;
            min = Math.min(min, nums[mid]);
            if(nums[start] > nums[mid]) { // there is a break in the first half
                end = mid - 1;
                min = Math.min(min, nums[end]);
            } else {
                start = mid + 1;
                min = Math.min(min, nums[start]);
            }
        }
        
        return min;
    }
}
