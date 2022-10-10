class Solution {
    private int linearMin(int[] nums, int start, int end) {
        int min = Integer.MAX_VALUE;
        for(int i = start; i < end; i++) {
            min = Math.min(min, nums[i]);
        }
        
        return min;
    }
    
    public int findMin(int[] nums) {
        int start = 0, end = nums.length - 1;
        int min = Math.min(nums[start], nums[end]);
        
        while(start < end) {
            if(nums[start] < nums[end]) break; // start -> end is decreasing
            else if(nums[start] == nums[end]) return Math.min(min, linearMin(nums, start, end)); // must linear search
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
