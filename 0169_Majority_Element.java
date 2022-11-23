// Hash-Map, O(n)
class Solution {
    public int majorityElement(int[] nums) {
        int targetAmount = nums.length / 2 + 1;
        HashMap<Integer, Integer> counts = new HashMap<>();
        
        for(int num : nums) {
            counts.putIfAbsent(num, 0);
            int newAmount = counts.get(num) + 1;
            counts.put(num, newAmount);
            
            if(counts.get(num) == targetAmount) return num;
        }
        
        return -1; // no majority element
    }
}

// Sorting, O(nlg(n))
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2]; // majority element must be at middle of sorted array
    }
}

// Bit-Counting, O(n)
class Solution {
    public int majorityElement(int[] nums) {
        // take total count of each Ith bit; if the majority
        // of the elements have the Ith bit set, the majority element has the Ith bit set
        
        int[] bitCounts = new int[32];
        
        for(int num : nums) {
            for(int i = 31; i >= 0; i--) {
                bitCounts[i] += (num & 1);
                num >>>= 1;
            }
        }
        
        int result = 0;
        for(int i = 0; i < 32; i++) {
            result <<= 1;
            if(bitCounts[i] > nums.length / 2)
                result |= 1;
        }
        
        return result;
    }
}

// Boyer-Moore Voting Algorithm, O(n)
class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0];
        int score = 0;
        
        for(int num : nums) {
            if(score == 0) {
                candidate = num;
                score = 1;
            } else if(num == candidate) score++;
            else score --;
        }
        
        return candidate;
    }
}
