class Solution {
public:
    int maximumCount(vector<int>& nums) {
        int numPos = 0, numNeg = 0;
        
        for(int& num : nums) {
            if(num == 0) continue;
            else if(num < 0) numNeg++;
            else numPos++;
        }
        
        return max(numPos, numNeg);
    }
};
