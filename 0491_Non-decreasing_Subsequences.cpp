class Solution {
public:
    void solve(vector<int>& nums, int i, vector<int> ss, set<vector<int>>& result) {
        if(ss.size() >= 2) result.insert(ss);
        
        if(i == nums.size()) return;
        
        solve(nums, i + 1, ss, result); // skip nums[i]
        
        if(ss.size() == 0 || nums[i] >= ss[ss.size() - 1]) { // nums[i] can be added
            ss.push_back(nums[i]); // include nums[i]
            solve(nums, i + 1, ss, result);
        }
    }
    
    vector<vector<int>> findSubsequences(vector<int>& nums) {
        set<vector<int>> result;
        
        solve(nums, 0, {}, result);
        
        return vector<vector<int>> {result.begin(), result.end()};
    }
};
