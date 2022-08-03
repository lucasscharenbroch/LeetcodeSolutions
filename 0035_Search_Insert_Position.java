class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0; // inclusive
        int right = nums.length; // exclusive
        
        while(left < right) {
            int mid = (left + right ) / 2;
            if(nums[mid] >= target) right = mid;
            else left = mid + 1;
        }
        
        return left;
    }
}
