class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        ArrayList<List<Integer>> quadruplets = new ArrayList<List<Integer>>();    
        Arrays.sort(nums, 0, nums.length);
        
        for(int i = 0; i < nums.length - 3; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) continue; // skip duplicates
            for(int j = i + 1; j < nums.length - 2; j++) {
                if(j != i + 1 && nums[j] == nums[j - 1]) continue; // skip duplicates
                
                int left = j + 1;
                int right = nums.length - 1;
                
                while(left < right) {
                    // convert to long to prevent overflow
                    long sum = (long) nums[i] + (long) nums[j] + 
                               (long) nums[left] + (long) nums[right];    
                    
                    if(sum == (long) target) {
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // advance left and right
                        while(++left < nums.length && nums[left] == nums[left - 1]);
                        while(--right >= 0 && nums[right] == nums[right + 1]);
                    } else if(sum > target) {
                        // advance right
                        while(--right >= 0 && nums[right] == nums[right + 1]);
                    } else {
                        // advance left
                        while(++left < nums.length && nums[left] == nums[left - 1]);
                    }
                }
            }
        }
        return quadruplets;
    }
}
