class Solution {
public:
    vector<int> runningSum(vector<int>& nums) {
        vector<int> result;
        int sum = 0;
        
        for(int& num : nums) {
            result.push_back(sum += num);
        }
        
        return result;
    }
};
