class Solution {
    private List<List<Integer>> permute(int[] nums, int start) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        
        // base case
        if(start == nums.length) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        for(int i = start; i < nums.length; i++) {
            // place nums[i] into the starting index (swap nums[i] into nums[start])
            int temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;
            
            // add each permutation that starts with nums[0] (prev. nums[i])
            for(List<Integer> permutation : permute(nums, start + 1)) {
                permutation.add(0, nums[start]); // insert nums[start] at the beginning of the result
                result.add(permutation); // add the result
            }
            
            //revert swap
            temp = nums[i];
            nums[i] = nums[start];
            nums[start] = temp;
        }
        
        return result;
    }
    
    public List<List<Integer>> permute(int[] nums) {
        return permute(nums, 0);
    }
}
