class Solution {
public:
    int minDeletionSize(vector<string>& strs) {
        int result = 0;
        
        for(int c = 0; c < strs[0].size(); c++) {
            int lastChar = 0;
            for(int r = 0; r < strs.size(); r++) {
                if(strs[r][c] < lastChar) { // this column is not sorted
                    result++;
                    break;
                }
                lastChar = strs[r][c];
            }
        }
        
        return result;
    }
};
