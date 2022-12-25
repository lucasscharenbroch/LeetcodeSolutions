class Solution {
public:
    vector<int> answerQueries(vector<int>& nums, vector<int>& queries) {
        vector<int> queriesCopy = queries;
        sort(queriesCopy.begin(), queriesCopy.end(), greater<int>()); // sort queries in decreasing order
        unordered_map<int, int> answers; // answers to queries
        
        sort(nums.begin(), nums.end(), greater<int>()); // sort nums in decreasing order
        int sum = accumulate(nums.begin(), nums.end(), 0);
        int i = 0; // position in nums
        for(int& q : queriesCopy) {
            while(sum > q) sum -= nums[i++];
            answers[q] = nums.size() - i;
        }
        
        vector<int> result;
        for(int& q : queries) {
            result.push_back(answers[q]);
        }
        return result;
    }
};
