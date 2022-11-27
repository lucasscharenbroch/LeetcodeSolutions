class Solution {
    public int countSubarrays(int[] nums, int k) {
        // first, find the index of k
        int kIndex = -1;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == k) kIndex = i;
        }
        if(kIndex == -1) return -1;
        
        // countsWithBalance[i] stores the amount of subarrays (containing k) that have a net of i elements
        // lower than k (e.g. 1 element higher and 4 elements lower => net 3 elements lower)
        HashMap<Integer, Integer> countsWithBalance = new HashMap<>();
        
        // every "subarray" must be a concatenation of a subarray to the right of kIndex
        // and one to the left of kIndex. Use hash map to do this in linear time.
        
        // populate countsWithBalance to the right
        int numLower = 0;
        int numHigher = 0;
        for(int i = kIndex; i < nums.length; i++) {
            if(nums[i] == k);
            else if(nums[i] < k) numLower++;
            else numHigher++;
            
            int net = numLower - numHigher;
            countsWithBalance.putIfAbsent(net, 0);
            countsWithBalance.put(net, countsWithBalance.get(net) + 1);
        }
        
        // use counts on left to find result
        numLower = 0;
        numHigher = 0;
        int result = 0;
        for(int i = kIndex; i >= 0; i--) {
            if(nums[i] == k);
            else if(nums[i] < k) numLower++;
            else numHigher++;
            
            int net = numLower - numHigher;
            result += countsWithBalance.getOrDefault(-net, 0); // middle median (odd element count)
            result += countsWithBalance.getOrDefault(-net - 1, 0); // left median (even element count)
        }
        
        return result;
    }
}
