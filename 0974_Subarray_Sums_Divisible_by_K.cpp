class Solution {
public:
    int subarraysDivByK(vector<int>& nums, int k) {
        int result = 0;
        int prefixSum = 0;
        vector<int> prefixModCount(k, 0); // [i] => # prefixSums where (prefixSum % k == i)
        
        for(int i = 0; i < nums.size(); i++) {
            prefixSum += nums[i];
            // make prefixSum positive (for mod)
            prefixSum = prefixSum >= 0 ? prefixSum : prefixSum + k * (-prefixSum / k + 1);
            result += prefixModCount[prefixSum % k]++;
        }
        
        return result + prefixModCount[0]; // add one-length subarrays to result
    }
};
