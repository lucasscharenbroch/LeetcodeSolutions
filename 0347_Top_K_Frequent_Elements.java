class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        final int MAX = 10000;
        int[] counts = new int[MAX * 2]; // count[i] = occurances of the element (i - MAX)
        
        for(int n : nums) {
            counts[n + MAX]++;
        }
        
        // binary search the answer
        // where "the answer" is the number of occurances of the Kth most frequent element
        int low = 1;
        int high = nums.length;
        
        outer:
        while(low <= high) {
            int mid = (low + high) / 2;
            
            int j = 0;
            for(int i = 0; i < counts.length; i++) {
                if(counts[i] >= mid) {
                    if(j == result.length) { // found too many elements
                        low = mid + 1;
                        continue outer;
                    }
                    result[j++] = i - MAX;
                }
            }
            
            if(j != result.length) high = mid - 1; // found too few elements
            else break; // found just enough elements
        }
        return result;
    }
}
