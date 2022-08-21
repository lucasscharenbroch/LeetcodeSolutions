class Solution {
    private List<List<Integer>> subsets(int[] nums, int i) {
        ArrayList<List<Integer>> sets = new ArrayList<>();
        sets.add(new ArrayList<Integer>()); // add the empty set
        
        for(; i < nums.length; i++) { // for each number, add all subsets that begin with it
            List<List<Integer>> subsubsets = subsets(nums, i + 1);
            for(List<Integer> subsubset : subsubsets) {
                subsubset.add(0, nums[i]);
                sets.add(subsubset);
            }
        }
        
        return sets;
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        return subsets(nums, 0);
    }
}
