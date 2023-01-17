class Solution {
public:
    int maximumWealth(vector<vector<int>>& accounts) {
        int result = 0;
        for(vector<int>& person : accounts) 
            result = max(result, accumulate(person.begin(), person.end(), 0));
        return result;
    }
};
