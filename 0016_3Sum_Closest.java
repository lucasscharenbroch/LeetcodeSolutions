class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums, 0, nums.length);    
        int closest = nums[0] + nums[1] + nums[2];
        
        for(int i = 0; i < nums.length; i++) {
            if(i != 0 && nums[i] == nums[i-1]) continue; // skip duplicates
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // update closest
                closest = (Math.abs(target - closest) < Math.abs(target - sum)) ? closest : sum;
                
                if(sum == target) {
                    return target;
                } else if(sum > target) {
                    // advance right, skipping repeated elements
                    while(--right >= 0 && nums[right] == nums[right + 1]);
                } else {
                    // advance left, skipping repeated elements
                    while(++left < nums.length && nums[left] == nums[left - 1]); 
                }
            }
        }
        
        return closest;
    }
}
