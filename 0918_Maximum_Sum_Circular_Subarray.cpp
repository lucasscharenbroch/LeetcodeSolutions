class Solution {
public:
    int maxSubarraySumCircular(vector<int>& nums) {
        // for non-circular subarrays, use Kadane's algorithm to find the largest subarray.
        // for circular subarrays, find a window not to include (the smallest subarray).
        
        int smallestSubarray = 0;
        int largestSubarray = 0;
        
        // use Kadane's algorithm to find the smallest subarray and the largest subarray
        int smallSum = 0, largeSum = 0;
        int maxElem = INT_MIN;
        for(int i = 0; i < nums.size(); i++) {
            smallSum = min(nums[i], nums[i] + smallSum);
            largeSum = max(nums[i], nums[i] + largeSum);
            
            smallestSubarray = min(smallestSubarray, smallSum);
            largestSubarray = max(largestSubarray, largeSum);
            maxElem = max(maxElem, nums[i]);
        }
        
        int largestCircularSubarray = accumulate(nums.begin(), nums.end(), 0) - smallestSubarray;
        int result = max(largestSubarray, largestCircularSubarray);
        return (result == 0) ? maxElem : result; // ensure that the subarray is nonempty
    }
};
