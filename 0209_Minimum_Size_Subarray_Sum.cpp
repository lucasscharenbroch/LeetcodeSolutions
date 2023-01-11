class Solution {
public:
    int minSubArrayLen(int target, vector<int>& nums) {
        int l = 0, r = 0;
        long long sum = 0;
        int result = INT_MAX;
        
        // for every l in [0, n - 1], find the minimum r such that sum(l, r) >= target
        for(; l < nums.size(); l++) {
            for(; r < nums.size() && sum < target; r++) sum += nums[r];
            if(sum < target) break; // no sufficient r
            result = min(result, r - l);
            sum -= nums[l];
        }
        
        return result == INT_MAX ? 0 : result;
    }
};
