class Solution {
    // given: nums is a sorted array
    private List<List<Integer>> permuteUnique(List<Integer> nums) {
        ArrayList<List<Integer>> result = new ArrayList<>();
        
        // base case
        if(nums.size() == 0) {
            result.add(new ArrayList<Integer>());
            return result;
        }
        
        // for each unique starting number (nums[i]) , add all permutations starting with that number
        for(int i = 0; i < nums.size(); i++) {
            if(i != 0 && nums.get(i) == nums.get(i - 1)) continue; // skip duplicates
            
            List<Integer> rest = (List<Integer>) ((ArrayList<Integer>) nums).clone();
            rest.remove(i);
            
            for(List<Integer> permutation : permuteUnique(rest)) {
                permutation.add(0, nums.get(i));
                result.add(permutation);
            }
        }
        
        return result;
    }
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        // sort nums
        Arrays.sort(nums, 0, nums.length);
        
        // convert nums to an ArrayList<Integer>
        ArrayList<Integer> numsAsArrList = new ArrayList<>();
        for(int n : nums) numsAsArrList.add(n);
        
        return permuteUnique(numsAsArrList);
    }
}
