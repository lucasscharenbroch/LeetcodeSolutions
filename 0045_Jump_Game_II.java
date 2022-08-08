class Solution {
    public int jump(int[] nums) {
        int current = 0;
        int jumps = 0;
        
        while(current < nums.length - 1) {
            if(nums[current] + current >= nums.length - 1) return ++jumps;    
            
            // find the reachable cell with the farthest reach
            int farthestReacher = 0;
            int maxReach = 0;
            for(int i = current + 1; i <= current + nums[current]; i++) {
                int currentReach = i + nums[i];
                if(currentReach >= maxReach) {
                    maxReach = currentReach;
                    farthestReacher = i;
                }
            }
            
            current = farthestReacher;
            jumps++;
        }
        
        return jumps;
    }
}
