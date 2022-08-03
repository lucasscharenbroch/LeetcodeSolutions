class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums.length == 0) return new int[]{-1, -1}; // edgecase
        
        // binary search for first occurence of target
        int start = 0; // inclusive
        int end = nums.length; // exclusive
        while(start < end) {
            int mid = (start + end) / 2;    
            if(nums[mid] >= target) end = mid;
            else start = mid + 1;
        }
        if(end < 0 || end >= nums.length || nums[end] != target) // cannot find target
            return new int[]{-1, -1};
        int[] solution = {end, 0};
        
        // binary search for last occurence of target
        start = -1; // exclusive
        end = nums.length - 1; // inclusive
        while(start < end) {
            int mid = (start + end + 1) / 2;
            if(nums[mid] <= target) start = mid;
            else end = mid - 1;
        }
        solution[1] = start;
        
        return solution;
    }
}
