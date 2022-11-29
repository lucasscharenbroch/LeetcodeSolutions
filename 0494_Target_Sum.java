// Brute-Force Recursion, O(2^n)
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        return findTargetSumWays(nums, 1, target - nums[0]) + 
               findTargetSumWays(nums, 1, target + nums[0]);
    }
    
    public int findTargetSumWays(int[] nums, int start, int target) {
        if(start == nums.length) return (target == 0) ? 1 : 0;
        return findTargetSumWays(nums, start + 1, target - nums[start]) +
               findTargetSumWays(nums, start + 1, target + nums[start]);
    }
}
