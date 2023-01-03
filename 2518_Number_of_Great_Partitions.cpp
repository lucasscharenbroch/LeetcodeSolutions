class Solution {
    static constexpr int MOD_CONST = 1e9 + 7;
public:
    int countPartitions(vector<int>& nums, int k) {
        // all partitions (that don't form a group that sums to less than K) are great
        // thus, the number of ways to choose form a great partition is: { the number of possible 
        // partitions (2^nums.size()) - the number of ways to partition nums such that one of the
        // two groups has a sum less than k }
        
        // -> find the number of groups that sum to k with knapsack
        
        // this edge-case must be checked in order to assume that 
        // (each partition with a group < k) has a group that (> k) 
        // otherwise, groups will be double-counted in the result
        if(accumulate(nums.begin(), nums.end(), 0L) < k * 2) return 0;
        
        long long dp[1000] = {0}; // dp[j] = number of groups with elements [0..=i] that sum to j
        dp[0] = 1; // one way to form 0-sum (empty set {})
        
        for(int i = 0; i < nums.size(); i++) {
            int n = nums[i];
            for(int j = k - 1 - n; j >= 0; j--) dp[j + n] = (dp[j + n] + dp[j]) % MOD_CONST;
        }
        
        long long numGroupsWithSumLssK = accumulate(dp, dp + k, 0L);
        numGroupsWithSumLssK *= 2;
        numGroupsWithSumLssK %= MOD_CONST;
        
        long long totalPossiblePartitions = 1;
        for(int i = 0; i < nums.size(); i++) {
            totalPossiblePartitions *= 2;
            totalPossiblePartitions %= MOD_CONST;
        }
        
        return (totalPossiblePartitions - numGroupsWithSumLssK + MOD_CONST) % MOD_CONST;
    }
};
