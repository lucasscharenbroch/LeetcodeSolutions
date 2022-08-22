class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) return true;
            if(left == mid) return nums[right] == target; // 2-length subarray
            
            // move left and right inwards until (nums[left, right, or mid] is not equal to the rest) 
            while(left < mid && right > mid && 
                  nums[left] == nums[mid] && nums[right] == nums[mid]) {
                left++;    
                right--;
            }
            
            if(mid == left || mid == right) continue; // get a new mid
            
            // assume !(nums[left] == nums[right] == nums[mid])
            if(nums[left] < nums[mid] || nums[right] < nums[mid]) { // left -> mid is increasing
                if(nums[left] <= target && target < nums[mid]) right = mid;
                else left = mid + 1;
            } else { // mid -> right is increasing
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid;
            }
        }
        
        return false;
    }
}
