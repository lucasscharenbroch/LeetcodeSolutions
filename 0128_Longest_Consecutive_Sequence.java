// hash-map with bounds
class Solution {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        HashMap<Integer, int[]> sequences = new HashMap<>();
        
        for(int num : nums) {
            if(sequences.containsKey(num)) continue;
            
            int high = num;
            int low = num;
            
            if(sequences.containsKey(num - 1)) {
                low = sequences.get(num - 1)[0];
            }
            if(sequences.containsKey(num + 1)) {
                high = sequences.get(num + 1)[1];
            }
                
            sequences.put(num, new int[2]);
            // update sequence bounds (only outer bounds matter)
            sequences.get(high)[0] = low;
            sequences.get(low)[1] = high;
            longest = Math.max(longest, 1 + high - low);
        }
        
        return longest;
    }
}

// hash-set iteration
class Solution {
    public int longestConsecutive(int[] nums) {
        HashSet<Integer> numSet = new HashSet<>();
        
        for(int num : nums) numSet.add(num);
        
        int result = 0;
        
        for(int num : nums) {
            if(numSet.contains(num - 1)) continue;
            
            int current = 0;
            while(numSet.contains(num)) {
                current++;
                num++;
            }
            result = Math.max(result, current);
        }
        
        return result;
    }
}
