class Solution {
    private List<List<Integer>> subsetsWithDup(int[] nums, int i) {
        ArrayList<List<Integer>> results = new ArrayList<>();
        results.add(new ArrayList<>()); // add the empty set
        
        if(i == nums.length) return results; // zero-length base-case
        
        // for each unique number in nums, add each set that includes it (and any of the subsequent numbers)
        while(i < nums.length) {
            // set next to the next unequal index (or nums.length)
            int next = i + 1;
            while(next < nums.length && nums[next] == nums[i]) next++;
            
            // recursively get all subsets of the subsequent numbers, add nums[i] to them, 
            // then add them to results.
            List<List<Integer>> subresults = subsetsWithDup(nums, i + 1);
            for(List<Integer> subresult : subresults) {
                subresult.add(nums[i]);
                results.add(subresult);
            }
            
            i = next;
        }
        
        return results;
    }
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        return subsetsWithDup(nums, 0);
    }
}
