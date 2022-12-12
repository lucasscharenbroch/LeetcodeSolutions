class Solution {
public:
    int longestSquareStreak(vector<int>& nums) {
        unordered_set<int> seen;
        for(int& num : nums) seen.insert(num);
        
        int result = 0;
        
        for(int& num : nums) {
            int streak = 0; // streak starting at num
            for(long long c = num; c < INT_MAX && seen.find(c) != seen.end(); c *= c) streak++;
            result = max(result, streak);
        }
        
        return result >= 2 ? result : -1;
    }
};
