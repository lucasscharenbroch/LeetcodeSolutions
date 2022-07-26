class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums, 0, nums.length);    
        ArrayList<List<Integer>> triplets = new ArrayList<List<Integer>>();
        
        for(int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if(i != 0 && nums[i] == nums[i-1]) continue; // skip duplicates
            
            int left = i + 1;
            int right = nums.length - 1;
            
            while(left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0) {
                    triplets.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // advance left and right, skipping repeated elements
                    while(++left < nums.length && nums[left] == nums[left - 1]); 
                    while(--right >= 0 && nums[right] == nums[right + 1]);
                } else if(sum > 0) {
                    // advance right, skipping repeated elements
                    while(--right >= 0 && nums[right] == nums[right + 1]);
                } else {
                    // advance left, skipping repeated elements
                    while(++left < nums.length && nums[left] == nums[left - 1]); 
                }
            }
        }
        
        return triplets;
    }
}
