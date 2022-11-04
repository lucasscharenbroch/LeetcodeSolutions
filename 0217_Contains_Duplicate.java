class Solution {
    public boolean containsDuplicate(int[] nums) {
        HashMap<Integer, Boolean> seen = new HashMap<>();
        
        for(int num : nums) {
            if(seen.get(num) != null) return true;
            seen.put(num, true);
        } 
        
        return false;
    }
}
