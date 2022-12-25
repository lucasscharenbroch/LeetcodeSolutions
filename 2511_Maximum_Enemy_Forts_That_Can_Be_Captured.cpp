class Solution {
public:
    int captureForts(vector<int>& forts) {
        int result = 0;
        // count every subarray of 0s that is bordered by one 1 and one -1
        int l = -1; // inclusive left bound of 0-subarray
        for(int i = 0; i < forts.size(); i++) {
            if(forts[i] == 0 && l == -1) l = i; // beginning of 0-subarray
            
            if(forts[i] != 0) { // end of subarray
                if(l != -1 && l != 0 && forts[l - 1] != forts[i]) // if subarray is valid,
                    result = max(result, i - l);
                l = -1;
            }
        }
        return result;
    }
};
