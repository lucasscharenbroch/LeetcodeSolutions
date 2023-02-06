class Solution {
public:
    vector<int> majorityElement(vector<int>& nums) {
        unordered_map<int, int> counts;
        vector<int> result;
        
        for(int& num : nums) if(counts[num] <= nums.size() / 3 &&
                                ++counts[num] > nums.size() / 3) result.push_back(num); 
        
        return result;
    }
};
