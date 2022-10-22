// dp, O(n^2)
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length]; // dp[i] = length of LIS including nums[i] as the last element
        int maxLength = 1;
        
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], 1 + dp[j]);
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        
        return maxLength;
    }
}

// binary search, O(nlg(n))
class Solution {
    // finds the smallest i, such that nums[i] >= target 
    private int binarySearch(ArrayList<Integer> nums, int target) {
        int left = 0;
        int right = nums.size() - 1;
        
        while(left < right) {
            int mid = (left + right) / 2;
            if(nums.get(mid) < target) {
                left = mid + 1;`
            } else {
                right = mid;
            }
        }
        
        return left;
    }
    
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> currentLIS = new ArrayList<>();
        
        for(int num : nums) {
            if(currentLIS.isEmpty()) {
                currentLIS.add(num);
                continue;
            }
            
            int lastElement = currentLIS.get(currentLIS.size() - 1);
            if(lastElement < num) {
                currentLIS.add(num);
                continue;
            }
            
            // find the smallest element <= num, and swap its value with num
            int insertionIndex = binarySearch(currentLIS, num);
            currentLIS.set(insertionIndex, num);
        }
        
        return currentLIS.size();
    }
}
