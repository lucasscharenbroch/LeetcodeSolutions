// O(n) - one loop
class Solution {
    public boolean canJump(int[] nums) {
        int minDistance = 0;
        
        for(int i = nums.length - 1; i > 0; i--) {
            if(nums[i] >= minDistance) minDistance = 1;
            else minDistance++;
        }
        
        return nums[0] >= minDistance;
    }
}


// O(n) - greedy (recursion)
class Solution {
    public boolean canJump(int[] nums) {
        return canJump(nums, 0);    
    }
    
    private boolean canJump(int[] nums, int start) {
        if(start + nums[start] >= nums.length - 1) return true;    
        if(nums[start] == 0) return false;
        
        // find best jump
        int bestJump = start + 1;
        int farthestReach = 0;
        
        for(int i = start + 1; i <= start + nums[start]; i++) {
            int reach = i + nums[i];
            if(reach > farthestReach) {
                bestJump = i;        
                farthestReach = reach;
            }
        }
        
        return canJump(nums, bestJump);
    }
}
