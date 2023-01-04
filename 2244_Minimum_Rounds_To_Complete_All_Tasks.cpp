class Solution {
public:
    int minimumRounds(vector<int>& tasks) {
        unordered_map<int, int> taskCounts;
        
        for(int& task : tasks) {
            taskCounts[task]++;
        }
        
        int result = 0;
        
        for(pair<const int, int>& keyValPair: taskCounts) {
            int count = keyValPair.second;
            if(count == 1) return -1; // no way to perform a single task (can't be divided into 2 or 3)
            if(count % 2 == 1) result++, count -= 3; // [remove 3] to make count even
            if(count / 6 != 0) result += count / 6 * 2, count %= 6; // remove as many 3s as possible
            result += count / 2; // use 2s to complete the rest
        }
        
        return result;
    }
};
