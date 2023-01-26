class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        vector<string> result;
        if(nums.size() == 0) return result;
        int start = nums[0], end = nums[0];
        
        for(int i = 1; i < nums.size(); i++) {
            if(nums[i] == end || nums[i] == end + 1) end = nums[i];
            else {
                char s[100];
                if(start == end) sprintf(s, "%d", start);
                else sprintf(s, "%d->%d", start, end);
                result.push_back(s);
                start = end = nums[i];
            }
        }
        
        char s[100];
        if(start == end) sprintf(s, "%d", start);
        else sprintf(s, "%d->%d", start, end);
        result.push_back(s);
        
        return result;
    }
};
