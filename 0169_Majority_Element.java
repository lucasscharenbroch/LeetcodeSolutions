class Solution {
    public int majorityElement(int[] nums) {
        // maintain a hash map of counts, and a candidate
        // this candidate is guaranteed to be the majority element once the end of the array is reached
        
        HashMap<Integer, Integer> counts = new HashMap<>();
        
        int majorityElementSoFar = nums[0]; // candidate for majority element
        
        for(int num : nums) {
            counts.putIfAbsent(num, 0);
            counts.put(num, counts.get(num) + 1);
            if(counts.get(num) > counts.get(majorityElementSoFar)) majorityElementSoFar = num;
        }
        
        return majorityElementSoFar;
    }
}
