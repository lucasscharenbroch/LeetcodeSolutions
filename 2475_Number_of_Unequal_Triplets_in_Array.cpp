// Brute-Force O(n^3)
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        int total = 0;
        
        for(int i = 0; i < nums.size(); i++) {
            for(int j = 0; j < i; j++) {
                for(int k = 0; k < j; k++) {
                    if(nums[i] == nums[j] || nums[j] == nums[k] || nums[i] == nums[k]) continue; // matching vals
                    total++;
                }
            }
        }
        
        return total;
    }
};
