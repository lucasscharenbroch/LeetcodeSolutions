class Solution {
    public int singleNumber(int[] nums) {
        HashMap<Integer, Integer> counts = new HashMap<>();
        
        for(int num : nums) {
            counts.put(num, counts.get(num) == null ? 1 : counts.get(num) + 1);
        }
        
        for(int key : counts.keySet()) {
            if(counts.get(key) == 1) return key;
        }
        
        return -1; // this line should never be reached
    }
}
